package com.my.core.cryptography.des;

import com.my.core.cryptography.generator.stream.util.BinaryUtils;
import org.junit.Assert;
import org.junit.Test;

import static com.my.core.cryptography.des.DesUtils.createBlocks;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DesUtilsTest {
    @Test
    public void createBlocksTest() throws Exception {
        byte[] data = {0, 1, 2, 3, 4, 5 ,6 ,7};
        boolean[][] blocks = createBlocks(data);
        assertThat(blocks.length, is(1));
    }

    @Test
    public void createBlocksWhenIncompleteDataTest() throws Exception {
        byte[] data = {0, 1, 2, 3, 4, 5 ,6, 7, 1};
        boolean[][] blocks = createBlocks(data);
        assertThat(blocks.length, is(2));
    }

    @Test
    public void create64BitKeyTest() throws Exception {
        boolean[] key = BinaryUtils.
                toBooleanArray(new String("00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001").replaceAll("\\s",""));
        assertThat(key.length, is(64));
        assertThat(key[0], is(false));
        assertThat(key[1], is(false));
        assertThat(key[2], is(false));
        assertThat(key[3], is(true));

        assertThat(key[61], is(false));
        assertThat(key[62], is(false));
        assertThat(key[63], is(true));
    }

    @Test
    public void pc1PermutationTest() throws Exception {
        boolean[] key = BinaryUtils.
                toBooleanArray(new String("00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001").replaceAll("\\s",""));
    }
}