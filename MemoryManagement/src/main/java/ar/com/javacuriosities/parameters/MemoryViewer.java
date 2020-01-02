package ar.com.javacuriosities.parameters;

import ar.com.javacuriosities.helper.MemoryUnit;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;

public class MemoryViewer {

    public static void main(String[] args) {
        System.out.println(MemoryViewer.checkMemory());
        System.out.println(MemoryViewer.checkMemoryPool());
    }

    public static String checkMemory() {
        StringBuilder sb = new StringBuilder("Memory:");
        sb.append(System.getProperty("line.separator"));

        MemoryMXBean mmbean = ManagementFactory.getMemoryMXBean();
        MemoryUsage hmu = mmbean.getHeapMemoryUsage();
        sb.append("[HeapMemoryUsage:");
        sb.append(" Used=" + MemoryUnit.BYTES.toMegaBytes(hmu.getUsed()));
        sb.append(" Committed="
                + MemoryUnit.BYTES.toMegaBytes(hmu.getCommitted()));
        sb.append("]");

        MemoryUsage nhmu = mmbean.getNonHeapMemoryUsage();
        sb.append("[NonHeapMemoryUsage:");
        sb.append(" Used=" + MemoryUnit.BYTES.toMegaBytes(nhmu.getUsed()));
        sb.append(" Committed="
                + MemoryUnit.BYTES.toMegaBytes(nhmu.getCommitted()));
        sb.append("]");
        sb.append(System.getProperty("line.separator"));
        return sb.toString();
    }

    public static String checkMemoryPool() {
        StringBuilder sb = new StringBuilder("MemoryPool:");
        sb.append(System.getProperty("line.separator"));

        List<MemoryPoolMXBean> pools = ManagementFactory.getMemoryPoolMXBeans();

        for (MemoryPoolMXBean p : pools) {
            sb.append("[" + p.getName() + ":");
            MemoryUsage u = p.getUsage();
            sb.append(" Used=" + MemoryUnit.BYTES.toMegaBytes(u.getUsed()));
            sb.append(" Committed="
                    + MemoryUnit.BYTES.toMegaBytes(u.getCommitted()));
            sb.append(" Type=" + p.getType().toString());
            sb.append("]");
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }
}
