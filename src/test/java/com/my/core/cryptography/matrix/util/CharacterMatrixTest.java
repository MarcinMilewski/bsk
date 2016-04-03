package com.my.core.cryptography.matrix.util;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.core.Is.is;


public class CharacterMatrixTest {
    @Test
    public void whenMatrix5x5AndTextCryptographyosaGetBy0ColumnShouldBeCoh() throws Exception {
        CharacterMatrix characterMatrix = new CharacterMatrix(5, 5, "cryptographyosa");
        Assert.assertThat(characterMatrix.getByColumn(0), is("coh"));
    }

}