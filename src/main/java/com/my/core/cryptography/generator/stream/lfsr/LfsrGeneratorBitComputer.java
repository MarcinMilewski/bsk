package com.my.core.cryptography.generator.stream.lfsr;

import java.util.BitSet;
import java.util.List;

public class LfsrGeneratorBitComputer {

    public boolean compute(List<Integer> order, BitSet generatorState) {
        boolean acc = generatorState.get(order.get(0));
        for (int i = 1; i < order.size(); i++) {
            acc = acc ^ generatorState.get(order.get(i));
        }
        return acc;
    }


}
