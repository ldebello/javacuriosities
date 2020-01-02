package ar.com.javacuriosities.garbage_collector;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

public class GCMXBean {
    public static void dumpGCInformation() {
        try {
            System.out.println("Garbage Collector Information");

            /*
             * Leemos información desde el GC
             */
            List<GarbageCollectorMXBean> gcmbeans = ManagementFactory
                    .getGarbageCollectorMXBeans();
            for (GarbageCollectorMXBean gcmbean : gcmbeans) {
                System.out.println("Name: " + gcmbean.getName());
                System.out.println("Collection count: "
                        + gcmbean.getCollectionCount());
                System.out.println("Collection time: "
                        + gcmbean.getCollectionTime());
                System.out.println("Memory Pools: ");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        dumpGCInformation();

        /*
         * Creamos algunos objetos
         */
        for (int index = 0; index < 1000000; index++) {
            String data = "Cadena " + index;
        }

        System.out.println();
        dumpGCInformation();

        // Imprimimos el tiempo total
        System.out.println();
        System.out.println("Tiempo total: "
                + (System.currentTimeMillis() - startTime));
    }
}