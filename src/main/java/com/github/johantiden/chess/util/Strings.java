package com.github.johantiden.chess.util;

import java.util.Comparator;
import java.util.function.Function;

public class Strings {
    public static Comparator<String> comparatorIgnoreThe(Comparator<String> innerComparator) {
        return Comparator.comparing(extractNameWithoutThe(), innerComparator);
    }

    public static Function<String, String> extractNameWithoutThe() {
        return name -> name.startsWith("The ") ? name.substring(4) : name;
    }
}
