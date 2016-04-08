package com.my.core.cryptography.generator.stream.lfsr;

import com.google.common.collect.Lists;
import com.my.core.cryptography.generator.stream.util.BinaryUtils;

import java.util.BitSet;
import java.util.List;

public class StatefulLfsrGenerator {
    private BitSet polynomial;
    private BitSet state;
    private List<Integer> additionOrder;
    private LfsrGeneratorBitComputer lfsrGeneratorBitComputer;

    public StatefulLfsrGenerator(BitSet polynomial, BitSet state) {
        this.polynomial = polynomial;
        this.state = state;
        additionOrder = getAdditionOrder(polynomial);
        lfsrGeneratorBitComputer = new LfsrGeneratorBitComputer();
    }

    private List<Integer> getAdditionOrder(BitSet polynomial) {
        List<Integer> order = Lists.newArrayList();
        for (int i = polynomial.length() -1 ; i >=0 ; i--) {
            if (polynomial.get(i) == true) {
                order.add(i);
            }
        }
        return order;
    }

    public boolean generateNext() {
        boolean computedBit = lfsrGeneratorBitComputer.compute(additionOrder, state);
        return computedBit;
    }

    public BitSet getState() {
        return state;
    }

    public void setState(BitSet state) {
        this.state = state;
    }

    public void shiftRightNoCarry() {
        state = BinaryUtils.shiftRightNoCarry(state);
    }

    public void setFirstStateBit(boolean first) {
        state.set(0, first);
    }

    public void set(int i, boolean b) {
        state.set(i, b);
    }
}
