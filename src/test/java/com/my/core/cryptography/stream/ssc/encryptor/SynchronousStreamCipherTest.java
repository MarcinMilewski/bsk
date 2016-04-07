package com.my.core.cryptography.stream.ssc.encryptor;

import com.my.core.cryptography.generator.stream.property.LfsrGeneratorProperty;
import com.my.core.cryptography.stream.ssc.property.SynchronousStreamProperty;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

public class SynchronousStreamCipherTest {

    private SynchronousStreamCipher synchronousStreamCipher;
    private Properties properties;
    private File inputFile;

    @Before
    public void setUp() throws Exception {
        synchronousStreamCipher = new SynchronousStreamCipher();
        properties = new Properties();
        properties.setProperty(SynchronousStreamProperty.OUTPUT_FILE_PATH.name(), "/home/marcin/testOutput2.bin");
        properties.setProperty(LfsrGeneratorProperty.POLYNOMIAL.name(), "1001");
        properties.setProperty(LfsrGeneratorProperty.SEED.name(), "1010");
        inputFile = new File(this.getClass().getResource("/test.txt").toURI());
    }


    @Test
    public void inputFileTest() throws Exception {
        assertThat(inputFile.exists(), is(true));
        assertThat(inputFile.length(), greaterThan(0l));
    }

    @Test
    public void encryptOutFileShouldSameLengthAsInputFile() throws Exception {
        File outputFile = synchronousStreamCipher.encrypt(inputFile, properties);
        assertThat(outputFile.length(), is(inputFile.length()));
    }

}