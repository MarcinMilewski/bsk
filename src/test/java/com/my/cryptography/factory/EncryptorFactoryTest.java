package com.my.cryptography.factory;

import com.my.cryptography.Encryptor;
import com.my.cryptography.enums.Algorithm;
import com.my.cryptography.railfence.encryptor.RailFenceEncryptor;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class EncryptorFactoryTest {
    @Test
    public void getEncryptor() throws Exception {
        Encryptor encryptor = EncryptorFactory.getEncryptor(Algorithm.RAIL_FENCE);
        assertTrue(encryptor instanceof RailFenceEncryptor);
    }

}