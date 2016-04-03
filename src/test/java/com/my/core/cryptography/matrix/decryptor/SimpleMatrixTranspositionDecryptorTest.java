package com.my.core.cryptography.matrix.decryptor;

import org.junit.Assert;
import org.junit.Test;

import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;

public class SimpleMatrixTranspositionDecryptorTest {
    @Test
    public void when5x5AndYropascohtpargyAndKey34152ShouldBeCryptographyosa() throws Exception {
        SimpleMatrixTranspositionDecryptor simpleMatrixTranspositionDecryptor = new SimpleMatrixTranspositionDecryptor();
        String output = simpleMatrixTranspositionDecryptor.decrypt("yropascohtpargy", new Properties());
        Assert.assertThat(output, is("cryptographyosa"));
    }

    @Test
    public void whenTextIsLongerForOneMatrixShoudlReadFromMore() throws Exception {
        SimpleMatrixTranspositionDecryptor simpleMatrixTranspositionDecryptor = new SimpleMatrixTranspositionDecryptor();
        String output = simpleMatrixTranspositionDecryptor.decrypt("yroyrpaspacohcotpatprgyrgoshay", new Properties());
        Assert.assertThat(output, is("cryptographyosacryptographyosa"));
    }
}