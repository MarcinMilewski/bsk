package com.my.core.cryptography.matrix.encryptor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class SimpleMatrixTranspositionEncryptorTest {

    private SimpleMatrixTranspositionEncryptor simpleMatrixTranspositionEncryptor;

    @Before
    public void setUp() throws Exception {
        simpleMatrixTranspositionEncryptor = new SimpleMatrixTranspositionEncryptor();
    }

    @Test
    public void whenTextCryptographyosaAnd5x5AndKey34152ShouldBeYropascohtpargy() throws Exception {
        String output = simpleMatrixTranspositionEncryptor.encrypt("cryptographyosa", new Properties());
        Assert.assertThat(output, is("yropascohtpargy"));
    }

}