package ar.com.javacuriosities.concurrency.atomic_reference;

import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * La clase AtomicReference nos permite asignar un valor siempre utilizando la tecnica CAS (Campare And Swap) lo que quiere
 * decir que solo se hara el cambio si el valor original es conocido, sin embargo nos deja expuesto al problema de ABA.
 *
 * ABA:
 * Estado Inicial: x = 0
 * Thread1: Lee x // obtiene x = 0
 * Thread2: x = 1
 * Thread3: x = 0
 * Thread1: read x // vuelve a obtener x = 0, lo cual hace parecer que nada cambio y el algoritmo CAS funciona sin problemas.
 *
 * Para resolver este problema surge la clase AtomicStampedReference la cual nos brinda un stamp(version) del cambio por lo cual ademas
 * del valor podemos comparar que sea la misma version.
 *
 * El siguiente ejemplo compara el problema ABA con AtomicReference y AtomicStampedReferenceAtomicMarkableReference
 */
public class Main {

    private static final Person initialPerson = new Person("Cosme Fulanito");
    private static final Person intermediatePerson = new Person("Pablo Marmol");
    private static final Person newPerson = new Person("Pedro Picapiedra");

    private static final AtomicReference atomicReference = new AtomicReference(initialPerson);
    private static final AtomicStampedReference atomicStampedReference = new AtomicStampedReference(initialPerson, 1);

    public static void main(String[] args) throws InterruptedException {
        // AtomicReference
        Thread atomicReferenceThread1 = new Thread(() -> {
            atomicReference.compareAndSet(initialPerson, intermediatePerson);
            atomicReference.compareAndSet(intermediatePerson, initialPerson);
        });

        Thread atomicReferenceThread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2); // atomicReferenceThread1 finished
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AtomicReference: " + atomicReference.compareAndSet(initialPerson, newPerson));
        });

        atomicReferenceThread1.start();
        atomicReferenceThread2.start();
        atomicReferenceThread1.join();
        atomicReferenceThread2.join();

        // AtomicStampedReference
        Thread atomicStampedReferenceThread1 = new Thread(() -> {
            try {
                // Let atomicStampedReferenceThread2 get the stamp first, causing the expected timestamp to be inconsistent
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            atomicStampedReference.compareAndSet(initialPerson, intermediatePerson, atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);
            atomicStampedReference.compareAndSet(intermediatePerson, initialPerson, atomicStampedReference.getStamp(),
                    atomicStampedReference.getStamp() + 1);
        });

        Thread atomicStampedReferenceThread2 = new Thread(() -> {
            int stamp = atomicStampedReference.getStamp();
            try {
                TimeUnit.SECONDS.sleep(2); //Thread atomicStampedReferenceThread1 is executed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("AtomicStampedReference: " + atomicStampedReference.compareAndSet(initialPerson, newPerson, stamp, stamp + 1));
        });
        atomicStampedReferenceThread1.start();
        atomicStampedReferenceThread2.start();
    }

    private static class Person {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Person person = (Person) o;
            return Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }

}
