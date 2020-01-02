package ar.com.javacuriosities.memory_usage;

import java.io.FileWriter;
import java.sql.ResultSet;

public class FileExporter {

    public void export() {
        ResultsetKey key = new ResultsetKey(0, new String("Transactions"));

        ResultSet resultset = ResultsetHandler.getSingleInstance().getResulset(
                key);

        if (resultset == null) {
            resultset = QueryExecutor.getData();

            ResultsetHandler.getSingleInstance().addResulset(key, resultset);
        }

        try {
            FileWriter writer = new FileWriter("Export.txt");

            while (resultset.next()) {
                writer.append(resultset.getString(1));
                writer.append(",");
                writer.append(resultset.getString(2));
                writer.append(",");
                writer.append(resultset.getString(3));
                writer.append(",");
                writer.append(Boolean.valueOf(resultset.getBoolean(4))
                        .toString());
                writer.append(",");
                writer.append(new Double(resultset.getDouble(5)).toString());
                writer.append(",");
                writer.append(new Double(resultset.getDouble(6)).toString());
                writer.append(",");
                writer.append(new Double(resultset.getDouble(7)).toString());
                writer.append(",");
                writer.append(System.getProperty("line.separator"));
            }

            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
