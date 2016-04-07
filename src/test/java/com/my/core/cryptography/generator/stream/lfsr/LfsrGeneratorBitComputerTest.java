package com.my.core.cryptography.generator.stream.lfsr;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.BitSet;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;

public class LfsrGeneratorBitComputerTest {

    private LfsrGeneratorBitComputer lfsrGeneratorBitComputer;

    @Before
    public void setUp() throws Exception {
        lfsrGeneratorBitComputer = new LfsrGeneratorBitComputer();
    }

    @Test
    public void whenPolynominalIs1001AndState1010Test() throws Exception {
        List<Integer> order = Lists.newArrayList(3, 0);
        BitSet state = new BitSet(4);
        state.set(0, true);
        state.set(0, false);
        state.set(0, true);
        state.set(0, false);
        boolean computed = lfsrGeneratorBitComputer.compute(order, state);
        Assert.assertThat(computed, is(false));
    }

    @Test
    public void whenPolynominalIs1001AndState0101Test() throws Exception {
        List<Integer> order = Lists.newArrayList(3, 0);
        BitSet state = new BitSet(4);
        state.set(0, false);
        state.set(0, true);
        state.set(0, false);
        state.set(0, true);
        boolean computed = lfsrGeneratorBitComputer.compute(order, state);
        Assert.assertThat(computed, is(true));
    }


}