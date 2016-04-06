package com.my.core.cryptography.stream.util;

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

}