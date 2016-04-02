package com.my.core.cryptography.railfence.encryptor;

import com.my.core.cryptography.railfence.properties.RailfenceProperty;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

import static org.junit.Assert.assertTrue;

public class RailFenceEncryptorTest {

    private RailFenceEncryptor railFenceEncryptor;

    @Before
    public void setUp() throws Exception {
        railFenceEncryptor = new RailFenceEncryptor();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNoDepthIsGivenShouldThrowException() throws Exception {
        String output = railFenceEncryptor.encrypt("Cryptography", new Properties());
    }
    
    @Test
    public void whenDepthIs3AndDataIsCryptographyShouldBeCtarporpyygh() throws Exception {
        Properties properties = new Properties();
        properties.setProperty(RailfenceProperty.DEPTH.name(), "3");
        String output = railFenceEncryptor.encrypt("cryptography", properties);
        assertTrue(output.equals("ctarporpyygh"));
    }
}