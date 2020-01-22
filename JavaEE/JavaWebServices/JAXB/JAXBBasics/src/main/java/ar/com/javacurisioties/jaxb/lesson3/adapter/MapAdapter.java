package ar.com.javacurisioties.jaxb.lesson3.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapAdapter extends XmlAdapter<MapAdapter.AdaptedMap, Map<Integer, String>> {

    @Override
    public Map<Integer, String> unmarshal(AdaptedMap adaptedMap) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (Entry entry : adaptedMap.entry) {
            map.put(entry.key, entry.value);
        }
        return map;
    }

    @Override
    public AdaptedMap marshal(Map<Integer, String> map) throws Exception {
        AdaptedMap adaptedMap = new AdaptedMap();
        for (Map.Entry<Integer, String> mapEntry : map.entrySet()) {
            Entry entry = new Entry();
            entry.key = mapEntry.getKey();
            entry.value = mapEntry.getValue();
            adaptedMap.entry.add(entry);
        }
        return adaptedMap;
    }

    public static class AdaptedMap {
        public List<Entry> entry = new ArrayList<Entry>();
    }

    public static class Entry {
        public Integer key;
        public String value;
    }
}