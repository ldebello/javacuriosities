package ar.com.javacuriosities.threads;

/**
 * Para entender False Sharing primero debemos entender la relación entre los CPU - Core - Registers - Cache.
 *
 * _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
 * |               CPU 1               |               CPU 2               |
 * |     Thread 1    |     Thread 2    |     Thread 3    |     Thread 4    |
 * |      Core 1     |      Core 2     |      Core 3     |      Core 4     |
 * |  CPU Registers  |  CPU Registers  |  CPU Registers  |  CPU Registers  |
 * |     L1 Cache    |     L1 Cache    |     L1 Cache    |     L1 Cache    |
 * |     L2 Cache    |     L2 Cache    |     L2 Cache    |     L2 Cache    |
 * |                                 L3 Cache                              |
 * |                                   RAM                                 |
 * _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _
 *
 * False Sharing sucede cuando dos threads ejecutándose en distintos CPUs escriben a variables distintas que resultan
 * estar alojadas en la misma CPU Cache Line.
 * Cuando el primer thread modifica la variable la linea entera debe ser invalida en otras Caches, eso quiere decir que
 * la linea se debe recargar desde la memoria principal incluso aunque no necesite el contenido de esa variable
 *
 * Cache Lines:
 * Cuando leemos data desde L1, L2, L3, RAM no se lee un solo byte sino que se lee una linea entera su tamaño siempre es
 * potencia de 2 usualmente entre 32-256 bytes (El tamaño mas común es 64 bytes), es por esto que una cache line puede
 * contener el contenido de multiples variables.
 * * Si el mismo CPU necesita acceder a variables distintas dentro de la misma linea esto es una ventaja.
 * * Si distintos CPU necesitan leer/escribir en distintas variables dentro de la misma linea es cuando false sharing sucede.
 *
 * Cache Line Invalidation:
 * Generalmente cuando un CPU escribe a una memory address en una cache line es porque estamos escribiendo el contenido
 * de una variable, lo que hace que esa linea sea marcada como dirty, entonces esa linea debe ser sincronizada con otros
 * CPUs (Cache Coherence - MESI es un protocolo que sirve para esto) y esas lineas se vuelven invalidas y deben ser recargadas.
 *
 * Impacto de False Sharing:
 * El impacto es a nivel de performance ya que se gasta mas tiempo en volver a cargar las cache line en memoria aunque no
 * se necesita el contenido de otras variables.
 *
 * Solución de False Sharing:
 * Este problema es difícil de detectar y de reproducir pero si nos topamos con esto generalmente debe revisar nuestro
 * código y las estructuras de datos que estamos utilizando.
 * * Código: Revisar los patrones de acceso a las variables accedidas por los distintos threads
 * * Estructuras de Datos: Dado que en Java no podemos manejar el memory layout de forma directa, hay algunos cambios
 * que podemos hacer para alinear nuestra variables para asegurarnos que no están en la misma linea.
 *
 * El siguiente ejemplo demuestra este problema, igual dado que depende de la cantidad de cores, cache size tenemos que
 * jugar un poco con el código para reproducirlo.
 *
 * Paso 1: Correr el código como esta y revisar el tiempo que lleva (Al menos 3 veces)
 * Paso 2: Cambiar la variables counter2 para que use su propio counter (Volver a correr 3 veces)
 * Paso 3: Si hasta ahora no vimos diferencia podemos intentar des-comentar de la variable counter3 hasta counter8 para
 * intentar que nuestra variables estén en distintas lineas.
 */
public class Lesson23FalseSharing {

    public static void main(String[] args) {
        Counter counter1 = new Counter();
//        Counter counter3 = new Counter();
//        Counter counter4 = new Counter();
//        Counter counter5 = new Counter();
//        Counter counter6 = new Counter();
//        Counter counter7 = new Counter();
//        Counter counter8 = new Counter();
        Counter counter2 = counter1; // new Counter();

        long iterations = 1_000_000_000;

        Thread thread1 = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            for (long i = 0; i < iterations; i++) {
                counter1.count1++;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("total time: " + (endTime - startTime));
        });
        Thread thread2 = new Thread(() -> {
            long startTime = System.currentTimeMillis();
            for (long i = 0; i < iterations; i++) {
                counter2.count2++;
            }
            long endTime = System.currentTimeMillis();
            System.out.println("total time: " + (endTime - startTime));
        });

        thread1.start();
        thread2.start();
    }

    /**
     * Este es otro ejemplo que muestra la diferencia entre el uso volatile o no, aunque sin
     * usar volatile obtenemos mejor performance, dependiendo si realmente estamos o no usando la misma
     * variable podríamos obtener errores por datos cacheados y no refrescados
     * @param args
     */
    public static void main2(String[] args) {
        try {
            FalseSharing.checkVolatile();
            FalseSharing.checkWithoutVolatile();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Counter {
        public volatile long count1 = 0;
        public volatile long count2 = 0;
    }


    public static class FalseSharing extends Thread {

        private static final DataHolder dh = new DataHolder();
        private static final DataHolderLocal dhLocal = new DataHolderLocal();

        private static long loops;
        public FalseSharing(Runnable r) {
            super(r);
        }

        public static void checkVolatile() throws InterruptedException {
            loops = 100000000;
            FalseSharing[] tests = new FalseSharing[4];

            tests[0] = new FalseSharing(() -> {
                for (long i = 0; i < loops; i++) {
                    dh.l1 += i;
                }
            });

            tests[1] = new FalseSharing(() -> {
                for (long i = 0; i < loops; i++) {
                    dh.l2 += i;
                }
            });

            tests[2] = new FalseSharing(() -> {
                for (long i = 0; i < loops; i++) {
                    dh.l1 += i;
                }
            });

            tests[3] = new FalseSharing(() -> {
                for (long i = 0; i < loops; i++) {
                    dh.l2 += i;
                }
            });

            long then = System.currentTimeMillis();

            for (FalseSharing ct : tests) {
                ct.start();
            }

            for (FalseSharing ct : tests) {
                ct.join();
            }

            long now = System.currentTimeMillis();
            System.out.println("Duration: " + (now - then) + " ms");
        }

        public static void checkWithoutVolatile() throws InterruptedException {
            loops = 1000000;
            FalseSharing[] tests = new FalseSharing[4];

            tests[0] = new FalseSharing(() -> {
                for (long i = 0; i < loops; i++) {
                    dhLocal.l1 += i;
                }
            });

            tests[1] = new FalseSharing(() -> {
                for (long i = 0; i < loops; i++) {
                    dhLocal.l2 += i;
                }
            });

            tests[2] = new FalseSharing(() -> {
                for (long i = 0; i < loops; i++) {
                    dhLocal.l1 += i;
                }
            });

            tests[3] = new FalseSharing(() -> {
                for (long i = 0; i < loops; i++) {
                    dhLocal.l2 += i;
                }
            });

            long then = System.currentTimeMillis();

            for (FalseSharing ct : tests) {
                ct.start();
            }

            for (FalseSharing ct : tests) {
                ct.join();
            }

            long now = System.currentTimeMillis();
            System.out.println("Duration: " + (now - then) + " ms");
        }

        private static class DataHolderLocal {
            private long l1 = 0;
            private long l2 = 0;
            private final long l3 = 0;
            private final long l4 = 0;
        }

        private static class DataHolder {
            private final long l3 = 0;
            private final long l4 = 0;
            private volatile long l1 = 0;
            private volatile long l2 = 0;
        }
    }
}
