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


}