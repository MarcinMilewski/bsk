package com.my.core.cryptography.railfence.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RailFenceUtil {
    public static List<Integer> upDownList(int depth, int length) {
        List<Integer> upDownList = Lists.newArrayList();
        for (int i = 0; i < length; i += 2 * depth- 2) {
            upDownList.addAll(range(1, depth));
            upDownList.addAll(range(depth - 1, 2));
        }
        return truncate(upDownList, length);
    }

    public static List<Integer> truncate(List<Integer> upDownList, int length) {
        return upDownList.subList(0, length);
    }

    public static List<Integer> range(int from, int to) {
        if (from > to) {
            return IntStream.rangeClosed(to, from).boxed().map(i -> from - i + to - 1).collect(Collectors.toList());
        } else {

        }
        return IntStream.rangeClosed(from, to).boxed().collect(Collectors.toList());
    }

}
