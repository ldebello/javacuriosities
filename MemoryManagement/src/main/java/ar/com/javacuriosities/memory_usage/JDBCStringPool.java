package ar.com.javacuriosities.memory_usage;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class JDBCStringPool {
    private ConcurrentMap<String, String> pool = new ConcurrentHashMap<String, String>(1000);

    public String getCanonicalVersion(String data) {
        if (data != null) {
            if (pool.size() > 10000) {
                pool.clear();
            }
            String canonical = pool.putIfAbsent(data, data);
            return (canonical == null) ? data : canonical;
        }
        return null;
    }

    public void clear() {
        pool.clear();
        pool = null;
    }
}
