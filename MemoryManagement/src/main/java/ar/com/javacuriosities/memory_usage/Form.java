package ar.com.javacuriosities.memory_usage;

import ar.com.javacuriosities.parameters.MemoryViewer;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Form extends JFrame {
    /**
     *
     */
    private static final long serialVersionUID = -6734742303449133269L;

    private final JTable jTable;
    private final JButton btnShowData;
    private final JButton btnExport;
    private final JButton btnClose;
    private final JButton btnMemoryInformation;

    public Form() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(
                new FormLayout(new ColumnSpec[]{
                        FormFactory.RELATED_GAP_COLSPEC,
                        ColumnSpec.decode("default:grow"),
                        FormFactory.RELATED_GAP_COLSPEC,
                        FormFactory.DEFAULT_COLSPEC,
                        FormFactory.RELATED_GAP_COLSPEC,
                        ColumnSpec.decode("max(100dlu;default):grow"),
                        FormFactory.RELATED_GAP_COLSPEC,
                        FormFactory.DEFAULT_COLSPEC,}, new RowSpec[]{
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,
                        FormFactory.RELATED_GAP_ROWSPEC,
                        RowSpec.decode("default:grow"),
                        FormFactory.RELATED_GAP_ROWSPEC,
                        FormFactory.DEFAULT_ROWSPEC,}));

        btnShowData = new JButton("Ver Datos");
        btnShowData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet rs = QueryExecutor.getData();
                GridHelper.updateTable(jTable, rs);
            }
        });
        getContentPane().add(btnShowData, "2, 2");

        btnExport = new JButton("Exportar Datos");
        btnExport.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FileExporter exporter = new FileExporter();
                exporter.export();
            }
        });
        getContentPane().add(btnExport, "4, 2");

        btnClose = new JButton("Salir");
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        getContentPane().add(btnClose, "6, 2, right, default");

        jTable = new JTable();
        JScrollPane jScrollPane = new JScrollPane(jTable);
        getContentPane().add(jScrollPane, "2, 4, 5, 1");
        jScrollPane.setPreferredSize(new Dimension(400, 250));

        btnMemoryInformation = new JButton("Informacion Memoria");
        btnMemoryInformation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(MemoryViewer.checkMemory());
                System.out.println(MemoryViewer.checkMemoryPool());
            }
        });
        getContentPane().add(btnMemoryInformation, "2, 6");

        pack();
        setVisible(true);
    }

}
