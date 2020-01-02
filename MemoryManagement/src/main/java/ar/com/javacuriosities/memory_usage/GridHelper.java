package ar.com.javacuriosities.memory_usage;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GridHelper {

    public static void updateTable(JTable jTable, ResultSet resultset) {
        DefaultTableModel tableModel = new DefaultTableModel() {
            private static final long serialVersionUID = 2555106340981571738L;

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) {
                    return Boolean.class;
                } else {
                    return super.getColumnClass(columnIndex);
                }
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        try {
            tableModel.addColumn("Cliente");
            tableModel.addColumn("Simbolo");
            tableModel.addColumn("Moneda");
            tableModel.addColumn("Es Compra");
            tableModel.addColumn("Volumen");
            tableModel.addColumn("Precio");
            tableModel.addColumn("Total");

            int columnCount = resultset.getMetaData().getColumnCount();

            JDBCStringPool pool = new JDBCStringPool();

            while (resultset.next()) {
                Object[] data = new Object[columnCount];

                /*
                 * Tres formas de administrar nuestra memoria
                 */
                processDataWastingMemory(resultset, data);
                // processDataUsingInternalCache(rs, pool, data);
                // processDataUsingPermGenCache(rs, data);

                tableModel.addRow(data);
            }

            pool.clear();
            pool = null;

            resultset.close();
            jTable.setModel(tableModel);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    private static void processDataUsingPermGenCache(ResultSet resultset,
                                                     Object[] data) throws SQLException {
        data[0] = resultset.getString(1).intern();
        data[1] = resultset.getString(2).intern();
        data[2] = resultset.getString(3).intern();
        data[3] = resultset.getBoolean(4);
        data[4] = resultset.getDouble(5);
        data[5] = resultset.getDouble(6);
        data[6] = resultset.getDouble(7);
    }

    private static void processDataUsingInternalCache(ResultSet resultset,
                                                      JDBCStringPool pool, Object[] data) throws SQLException {
        data[0] = pool.getCanonicalVersion(resultset.getString(1));
        data[1] = pool.getCanonicalVersion(resultset.getString(2));
        data[2] = pool.getCanonicalVersion(resultset.getString(3));
        data[3] = resultset.getBoolean(4);
        data[4] = resultset.getDouble(5);
        data[5] = resultset.getDouble(6);
        data[6] = resultset.getDouble(7);
    }

    private static void processDataWastingMemory(ResultSet resultset,
                                                 Object[] data) throws SQLException {
        data[0] = resultset.getString(1);
        data[1] = resultset.getString(2);
        data[2] = resultset.getString(3);
        data[3] = resultset.getBoolean(4);
        data[4] = resultset.getDouble(5);
        data[5] = resultset.getDouble(6);
        data[6] = resultset.getDouble(7);
    }
}
