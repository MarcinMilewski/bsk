package com.my.core.cryptography.des;

import com.my.core.cryptography.generator.stream.util.BinaryUtils;
import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class DesAlgorithmTest {

    @Test
    public void create64BitKeyTest() throws Exception {
        boolean[] key = BinaryUtils.
                toBooleanArray(new String("00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001").replaceAll("\\s",""));
        boolean[] pc1Key = DesAlgorithm.create64BitKey(key);
        assertThat(pc1Key.length, is(56));
        assertThat(pc1Key[0], is(true));
        assertThat(pc1Key[1], is(true));
        assertThat(pc1Key[2], is(true));
        assertThat(pc1Key[3], is(true));
        assertThat(pc1Key[4], is(false));
        assertThat(pc1Key[54], is(true));
        assertThat(pc1Key[55], is(true));
    }

    @Test
    public void create16Subkeys() throws Exception {
        boolean[] key = BinaryUtils.
                toBooleanArray(new String("00010011 00110100 01010111 01111001 10011011 10111100 11011111 11110001").replaceAll("\\s",""));
        boolean[] pc1Key = DesAlgorithm.create64BitKey(key);
        boolean[][] subKeys = DesAlgorithm.create16Subkeys(pc1Key);
        assertThat(subKeys[0][0], is(false));
        assertThat(subKeys[0][1], is(false));
        assertThat(subKeys[0][2], is(false));
        assertThat(subKeys[0][3], is(true));
        assertThat(subKeys[0][4], is(true));
        assertThat(subKeys[0][5], is(false));
    }

    @Test
    public void initialPermutationTest() throws Exception {
        boolean[] block = BinaryUtils.
                toBooleanArray(new String("0000 0001 0010 0011 0100 0101 0110 0111 1000 1001 1010 1011 1100 1101 1110 1111").replaceAll("\\s",""));
        boolean[] ipBlock = DesAlgorithm.initialPermutate(block);
        boolean[] expected = BinaryUtils.
                toBooleanArray(new String("1100 1100 0000 0000 1100 1100 1111 1111 1111 0000 1010 1010 1111 0000 1010 1010").replaceAll("\\s",""));
        assertTrue(Arrays.equals(ipBlock, expected));
    }

    @Test
    public void finalPermutationTest() throws Exception {
        boolean[] block = BinaryUtils.
                toBooleanArray(new String("00001010 01001100 11011001 10010101 01000011 01000010 00110010 00110100").replaceAll("\\s",""));
        boolean[] ipBlock = DesAlgorithm.finalPermutate(block);
        boolean[] expected = BinaryUtils.
                toBooleanArray(new String("10000101 11101000 00010011 01010100 00001111 00001010 10110100 00000101").replaceAll("\\s",""));
        assertTrue(Arrays.equals(ipBlock, expected));
    }

    @Test
    public void getExpandedTest() throws Exception {
        boolean[] bits32 = BinaryUtils.
                toBooleanArray(new String("1111 0000 1010 1010 1111 0000 1010 1010 ").replaceAll("\\s",""));
        boolean[] expected = BinaryUtils.
                toBooleanArray(new String("011110 100001 010101 010101 011110 100001 010101 010101 ").replaceAll("\\s",""));
        boolean[] result = DesAlgorithm.getExpanded(bits32);
        assertTrue(Arrays.equals(result, expected));
    }

}