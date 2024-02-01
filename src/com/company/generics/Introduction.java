package com.company.generics;

import java.util.Arrays;
import java.util.List;

public class Introduction {
    public static void practice() {
        List<Integer> integers = Arrays.asList(1, 2, 3);
        int sum = 0;
        for(int i: integers) {
            sum += i;
        }
        assert sum == 6;
    }
}
