package ar.com.javacuriosities.xml.rpc.server.handlers;

import java.util.HashMap;
import java.util.Map;

public class Students {

    private static final String ERROR = "E";
    private static final String SUCCESS = "S";

    public Map<String, String> save(Map<String, String> student) {
        Map<String, String> result = new HashMap<>();
        try {
            result.put("name", student.get("name"));
            result.put("code", SUCCESS);
            result.put("description", "Student save");
            return result;
        } catch (Exception e) {
            result.put("code", ERROR);
            result.put("description", e.getMessage());
            return result;
        }
    }
}