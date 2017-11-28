package ar.com.javacuriosities.threads;

/**
 * Asi cuando usamos variables asociadas a un Thread debemos notar que si un Thread crea otro, ese no puede acceder
 * al valor de sus variables {@link ThreadLocal} ya que es uno nuevo, si quisieramos inicializar los {@link ThreadLocal} que
 * corresponden al nuevo Thread en base a los valores ya inicializados para su Thread "padre" debemos utilizar {@link InheritableThreadLocal}
 */
public class Lesson16InheritedThreadLocal {

    private static ThreadLocal<Integer> counterLocal = new ThreadLocal<>();
    private static ThreadLocal<Integer> counterInherited = new InheritableThreadLocal<>();
    private static ThreadLocal<Integer> counterInheritedWithInitialValue = InheritableThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) {
        counterLocal.set(100);
        counterInherited.set(200);
        counterInheritedWithInitialValue.set(300);

        Thread thread = new Thread(new CounterPrinter());

        thread.start();
    }

    private static class CounterPrinter implements Runnable {

        @Override
        public void run() {
            // Se imprime null, dado que esta instancia es del tipo ThreadLocal
            System.out.println(counterLocal.get());
            // Se imprime 200 ya que es el valor seteado antes de crear el Thread que ejecuta este Runnable
            System.out.println(counterInherited.get());
            // Se imprime 0 ya que este ThreadLocal ya venia inicializado por medio del supplier que se usa en el constructor
            System.out.println(counterInheritedWithInitialValue.get());
        }
    }
}