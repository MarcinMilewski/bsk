package com.my.core.cryptography.caesar.decryptor;

import com.google.common.math.IntMath;
import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.caesar.property.CaesarProperty;

import java.util.Properties;

import static com.my.core.cryptography.caesar.util.AlphabetBiMap.alphabet;
import static com.my.core.cryptography.caesar.util.AlphabetBiMap.characterValueMap;

public class CaesarCipherDecryptor implements Decryptor{
    @Override
    public String decrypt(String data, Properties properties) {
        Integer k0 = Integer.valueOf(properties.getProperty(CaesarProperty.K0.name()));
        Integer k1 = Integer.valueOf(properties.getProperty(CaesarProperty.K1.name()));
        Integer n = Integer.valueOf(properties.getProperty(CaesarProperty.N.name()));

        if (!(coprime(k0, n) && coprime(k1, n))) throw new IllegalArgumentException("k1, k0 are not coprime with n");
        return decryptInternal(data.replaceAll("\\s+", ""), k0, k1, n);
    }

    private String decryptInternal(String data, Integer k0, Integer k1, Integer n) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            encrypted.append(decryptChar(data.charAt(i), k0, k1, n));
        }
        return encrypted.toString();
    }

    private final Character decryptChar(char a, Integer k0, Integer k1, Integer n) {
        int c = characterValueMap.get(a);
        int decryptedCharValue =  ( (c + (n -k0))*((int)Math.pow(k1, phiEuler(n) -1))) % n;
        return alphabet.get(decryptedCharValue);
    }

    private final boolean coprime(Integer a, Integer b) {
        return IntMath.gcd(a, b) == 1 ? true : false;
    }

    public int phiEuler(int num){
        int count=0;
        for(int a=1;a<num;a++){ //definition of totient: the amount of numbers less than num coprime to it
            if(IntMath.gcd(num,a)==1){
                count++;
            }
        }
        return(count);
    }
}
