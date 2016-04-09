package com.my.core.cryptography.generator.stream.util;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class BinaryUtilsTest {
    @Test
    public void getMask() throws Exception {
        boolean[] mask = BinaryUtils.getBooleanArray("10100");
        assertThat(mask[0], is(true));
        assertThat(mask[1], is(false));
        assertThat(mask[2], is(true));
        assertThat(mask[3], is(false));
        assertThat(mask[4], is(false));
    }

    @Test
    public void shiftRightNoCarryTest() throws Exception {
        boolean[] mask = BinaryUtils.getBooleanArray("10100");
        mask = BinaryUtils.shiftRightNoCarry(mask); // should be 01010
        assertThat(mask[0], is(false));
        assertThat(mask[1], is(true));
        assertThat(mask[2], is(false));
        assertThat(mask[3], is(true));
        assertThat(mask[4], is(false));
    }

    @Test
    public void xorTest() throws Exception {
        assertThat(Boolean.logicalXor(true, true), is(false));
        assertThat(Boolean.logicalXor(false, false), is(false));
        assertThat(Boolean.logicalXor(true, false), is(true));
        assertThat(Boolean.logicalXor(false, true), is(true));
    }

    @Test
    public void toBytesWhenNewBooleanTable() throws Exception {
        boolean[] boolArr = new boolean[8];
        byte[] byteArray = BinaryUtils.toBytes(boolArr);
        assertThat(byteArray.length, is(1));
        assertThat(byteArray[0], is((byte) 0));
    }

    @Test
    public void toBytesWhen2NewBooleanTable() throws Exception {
        boolean[] boolArr = new boolean[10];
        byte[] byteArray = BinaryUtils.toBytes(boolArr);
        assertThat(byteArray.length, is(2));
        assertThat(byteArray[0], is((byte) 0));
        assertThat(byteArray[1], is((byte) 0));
    }

}