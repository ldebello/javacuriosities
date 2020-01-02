package ar.com.javacuriosities.memory_usage;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

public class ResultsetHandler {

    private static final ResultsetHandler instance = new ResultsetHandler();

    private final Map<ResultsetKey, ResultSet> resulsets = new HashMap<ResultsetKey, ResultSet>();

    private ResultsetHandler() {
    }

    public static synchronized ResultsetHandler getSingleInstance() {
        return instance;
    }

    public ResultSet getResulset(ResultsetKey key) {
        return resulsets.get(key);
    }

    public void addResulset(ResultsetKey key, ResultSet resultset) {
        resulsets.put(key, resultset);
    }
}
