package br.com.projetofinal.core;

import java.util.HashMap;
import java.util.Map;

abstract public class Counter {

    public static Map<String, Integer> tell(String[] keywordsSplitting, String[] textSplitting) {
        Map<String, Integer> mapping = new HashMap<>();
        for (String kws : keywordsSplitting) {
            for (String ts : textSplitting) {
                if (ts.toLowerCase().equalsIgnoreCase(kws.toLowerCase())) {
                    if (mapping.containsKey(kws)) {
                        Integer key = mapping.get(kws);
                        mapping.put(kws, (key + 1));
                    } else {
                        mapping.put(kws, 1);
                    }
                } else {
                    if (!mapping.containsKey(kws)) {
                        mapping.put(kws, 0);
                    }
                }

            }
        }
        return mapping;
    }
}
