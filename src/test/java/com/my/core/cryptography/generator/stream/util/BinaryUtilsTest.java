package com.my.core.cryptography.generator.stream.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.BitSet;

import static org.hamcrest.CoreMatchers.is;

public class BinaryUtilsTest {
    @Test
    public void getMask() throws Exception {
        BitSet mask = BinaryUtils.getMask("10100");
        Assert.assertThat(mask.get(0), is(true));
        Assert.assertThat(mask.get(1), is(false));
        Assert.assertThat(mask.get(2), is(true));
        Assert.assertThat(mask.get(3), is(false));
        Assert.assertThat(mask.get(4), is(false));
    }

    @Test
    public void shiftRightNoCarryTest() throws Exception {
        BitSet mask = BinaryUtils.getMask("10100");
        mask = BinaryUtils.shiftRightNoCarry(mask); // should be 01010
        Assert.assertThat(mask.get(0), is(false));
        Assert.assertThat(mask.get(1), is(true));
        Assert.assertThat(mask.get(2), is(false));
        Assert.assertThat(mask.get(3), is(true));
        Assert.assertThat(mask.get(4), is(false));
    }

    @Test
    public void xorTest() throws Exception {
        Assert.assertThat(Boolean.logicalXor(true, true), is(false));
        Assert.assertThat(Boolean.logicalXor(false, false), is(false));
        Assert.assertThat(Boolean.logicalXor(true, false), is(true));
        Assert.assertThat(Boolean.logicalXor(false, true), is(true));
    }

}