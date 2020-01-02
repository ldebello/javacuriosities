package ar.com.javacuriosities.heap_dump;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.management.MBeanServer;
import javax.swing.JButton;
import javax.swing.JFrame;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class HeapDump extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6734742303449133269L;
	private final JButton btnFillMemory;
	private final JButton btnGenerateHeapDumpJmap;
	private final JButton btnClose;
	private final JButton btnGenerateHeapDumpMXBean;

	private final List<String> information = new ArrayList<String>();

	/*
	 * Obtengo el MXBean usando reflection porque sino no puede ser accedido por
	 * restricciones
	 */
	private static volatile Object hotspotMBean;
	private static final String HOTSPOT_BEAN_NAME = "com.sun.management:type=HotSpotDiagnostic";

	public HeapDump() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		getContentPane().setLayout(
				new FormLayout(new ColumnSpec[] {
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC,
						FormFactory.RELATED_GAP_COLSPEC,
						ColumnSpec.decode("max(100dlu;default):grow"),
						FormFactory.RELATED_GAP_COLSPEC,
						FormFactory.DEFAULT_COLSPEC, }, new RowSpec[] {
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC,
						FormFactory.RELATED_GAP_ROWSPEC,
						RowSpec.decode("default:grow"),
						FormFactory.RELATED_GAP_ROWSPEC,
						FormFactory.DEFAULT_ROWSPEC, }));

		btnFillMemory = new JButton("Llenar Memoria");
		btnFillMemory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < 500000; i++) {
					information.add("Informacion " + i);
				}
			}
		});
		getContentPane().add(btnFillMemory, "2, 2");

		btnGenerateHeapDumpJmap = new JButton("Generar Heap Dump (Jmap)");
		btnGenerateHeapDumpJmap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					String name = ManagementFactory.getRuntimeMXBean()
							.getName();
					String pid = name.substring(0, name.indexOf("@"));
					String[] cmd = { "jmap", "-dump:file=DumpJmap.bin", pid };
					Process p = Runtime.getRuntime().exec(cmd);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		getContentPane().add(btnGenerateHeapDumpJmap, "4, 2");

		btnClose = new JButton("Salir");
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		btnGenerateHeapDumpMXBean = new JButton("Generar Heap Dump (MX Bean)");
		btnGenerateHeapDumpMXBean.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Inicializamos el Diagnostic MXBean
				initHotspotMBean();
				try {
					Class clazz = Class
							.forName("com.sun.management.HotSpotDiagnosticMXBean");
					Method m = clazz.getMethod("dumpHeap", String.class,
							boolean.class);
					m.invoke(hotspotMBean, "DumpMXBean.bin", true);
				} catch (RuntimeException re) {
					throw re;
				} catch (Exception exp) {
					throw new RuntimeException(exp);
				}
			}

			private void initHotspotMBean() {
				if (hotspotMBean == null) {
					synchronized (HeapDump.class) {
						if (hotspotMBean == null) {
							hotspotMBean = getHotspotMBean();
						}
					}
				}
			}

			private Object getHotspotMBean() {
				try {
					Class clazz = Class
							.forName("com.sun.management.HotSpotDiagnosticMXBean");
					MBeanServer server = ManagementFactory
							.getPlatformMBeanServer();
					Object bean = ManagementFactory.newPlatformMXBeanProxy(
							server, HOTSPOT_BEAN_NAME, clazz);
					return bean;
				} catch (RuntimeException re) {
					throw re;
				} catch (Exception exp) {
					throw new RuntimeException(exp);
				}
			}
		});
		getContentPane().add(btnGenerateHeapDumpMXBean, "6, 2");
		getContentPane().add(btnClose, "8, 2, right, default");

		pack();
		setVisible(true);
	}

}
