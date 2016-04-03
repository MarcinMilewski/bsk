package com.my.core.cryptography.matrix.decryptor;

import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SimpleMatrixTranspositionDecryptor implements Decryptor {
    @Override
    public String decrypt(String data, Properties properties) {
        List<String> strings = getSubstrings(data, 25);


        int matricesNumber = (int) Math.ceil(data.length() / 25);
        List<CharacterMatrix> matrices = createMatrices(matricesNumber);
        return null;
    }

    private List<String> getSubstrings(String data, int size) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < data.length(); i = i + size) {
            if (data.length() > i + offest) {
                result.add(data.substring(i, i + size));
            } else {
                result.add(data.substring(i));
            }
        }
        return result;
    }

    private List<CharacterMatrix> createMatrices(int matricesNumber) {
        List<CharacterMatrix> matrices = new ArrayList<>();
        for (int i = 0; i < matricesNumber; ++i) {
            matrices.add(new CharacterMatrix(5 ,5));
        }
        return matrices;
    }
}
