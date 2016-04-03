package com.my.core.cryptography.matrix.encryptor;

import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SimpleMatrixTranspositionEncryptor implements Encryptor{
    @Override
    public String encrypt(String data, Properties properties) {
        List<CharacterMatrix> matrices = createMatrices(data);
        return encryptInternal(matrices);
    }

    private List<CharacterMatrix> createMatrices(String data) {
        List<String> strings = split(data, 5, 5);
        List<CharacterMatrix> matrices = new ArrayList<>();
        for (String string : strings) {
            string.replaceAll("\\s+","");
            matrices.add(new CharacterMatrix(5, 5, string));
        }
        return matrices;
    }

    private List<String> split(String data, int width, int height) {
        int offest = width * height;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < data.length(); i = i + offest) {
            if (data.length() > i + offest) {
                result.add(data.substring(i, i + offest));
            } else {
                result.add(data.substring(i));
            }
        }
        return result;
    }

    private String encryptInternal(List<CharacterMatrix> matrices) {
        StringBuilder sb = new StringBuilder();
        for (CharacterMatrix matrix : matrices) {
            sb.append(matrix.getByColumn(2));
            sb.append(matrix.getByColumn(3));
            sb.append(matrix.getByColumn(0));
            sb.append(matrix.getByColumn(4));
            sb.append(matrix.getByColumn(1));
        }
        return sb.toString();
    }
}
