package com.github.johantiden.chess.util;

import java.util.ArrayList;
import java.util.List;

public class JTLists {
    public static <T> List<List<T>> split(List<T> list, int chunkSize) {

        int numChunks = list.size() / chunkSize;
        List<List<T>> split = new ArrayList<>(numChunks);

        for (int i = 0; i < numChunks; i++) {
            split.add(list.subList(i*chunkSize, (i+1)*chunkSize));
        }

        return split;
    }
}
