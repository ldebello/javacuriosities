package ar.com.javacuriosities.concurrency.management;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/*
 * En Java 1.5 se agregaron nuevas funcionalidades incluidas en el paquete java.lang.management.
 *
 * Se incluyeron distintos MXBean que ayudan a obtener información de los diferentes recursos.
 *  
 * Platform resource            Corresponding MXBean        Number available
 * Compilation                  CompilationMXBean           0 or 1 
 * Garbage collection system    GarbageCollectorMXBean      At least 1 
 * Memory                       MemoryMXBean                Exactly 1 Memory
 * managers                     MemoryManagerMXBean         At least 1 
 * Threading                    ThreadMXBean                Exactly 1
 * Operating system             OperatingSystemMXBean       Exactly 1 
 * Runtime system               RuntimeMXBean               Exactly 1 
 * Class loading system         ClassLoadingMXBean          Exactly 1 
 * Memory resources             MemoryPoolMXBean            At least 1
 *
 * 
 * Introducción:
 * 
 * Antes de Java 1.5 la única manera de hacer benchmark era usando lo que se conoce como "wall clock time"
 * 
 * long startTime = System.currentTimeMillis(); 
 * // Tareas
 * endTime = System.currentTimeMillis() - startTime;
 * 
 * En Java 1.5 además del método currentTimeMillis() se agrega el método nanoTime(), que ofrece 
 * el tiempo en nanosegundos
 *
 * long startTime = System.nanoTime(); 
 * // Tareas 
 * endTime = System.nanoTime() - startTime;
 *
 * Sin embargo estos dos métodos no son de mucha utilidad si nosotros queremos conocer estos
 * datos a nivel de Threads, por eso se incluyo ThreadMXBean el cual provee los siguientes datos
 * 
 * "User time": Es el tiempo consumido por la aplicación en correr nuestro código.
 * "System time": Es el tiempo consumido por el SO en nombre de nuestra aplicación (Ejemplo I/O)
 * 
 * "CPU time": Es la suma de User Time + System Time. Es el tiempo total usado por nuestra aplicación.
 * 
 * Lo bueno es que podemos hacer cosas como lo siguiente
 * 
 * ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean(); 
 * long cpuTime = threadMXBean.getCurrentThreadCpuTime();
 * 
 * Con las líneas anteriores obtenemos el cpuTime para el hilo actual, obteniendo el startCpuTime y el 
 * endCpuTime podemos ver cuanto tiempo es consumido por nuestro Thread
 */
public class Main {

    public static void main(String[] args) {
        // Creamos 4 threads los cuales imprimen el tiempo de CPU usado por cada uno
        Thread measure1 = new ThreadMeasure("Counter-1", 1000);
        Thread measure2 = new ThreadMeasure("Counter-2", 1000);
        Thread measure3 = new ThreadMeasure("Counter-3", 1000);
        Thread measure4 = new ThreadMeasure("Counter-4", 1000);

        // Iniciamos los 4 threads
        measure1.start();
        measure2.start();
        measure3.start();
        measure4.start();
    }
    
    private static final class ThreadMeasure extends Thread {

        // Variable que controla el maximo numero de ciclos
        private int maxCounter;
       
        public ThreadMeasure(String name, int maxCounter) {
            super(name);
            this.maxCounter = maxCounter;
        }

        /*
         * Sobrescribimos el método run() y como primer línea
         * pedimos el ThreadMXBean, luego pedimos el cpuTime y antes de terminar el 
         * método run() volvemos a pedir el cpuTime para calcular la diferencia
         */
        @Override
        public void run() {
            // Pedimos el ThreadMXBean
            ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
            
            // Pedimos el cpuTime inicial
            long cpuTime = threadMXBean.getCurrentThreadCpuTime();
            
            /* Creamos una variable la cual vamos a ir modificando en el ciclo
             * este código esta puesto porque si ponemos un ciclo vació el compilador
             * se da cuenta de esto y lo remueve para incrementar la performance
             */
            long total = 0;
            for (int contador = 0; contador <= (maxCounter * maxCounter * maxCounter); contador++) {
                total += contador;
                total *= 10;
            }
            
            System.out.println(getName() + " -> " + total);
            
            // Calculamos la diferencia entre el final y el inicial
            cpuTime = threadMXBean.getCurrentThreadCpuTime() - cpuTime;
            
            // Imprimimos cuando tiempo consumió este hilo
            System.out.println("Thread: " + getName() + ": cpuTime = " + cpuTime);
        }
    }
}