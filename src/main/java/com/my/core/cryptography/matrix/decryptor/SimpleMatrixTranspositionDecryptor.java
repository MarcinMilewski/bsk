package com.my.core.cryptography.matrix.decryptor;

import com.google.common.collect.Lists;
import com.my.core.cryptography.Decryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SimpleMatrixTranspositionDecryptor implements Decryptor {
    @Override
    public String decrypt(String data, Properties properties) {
        final int rowsNumber = 5;
        final int columnsNumber = 5;
        final int size = rowsNumber * columnsNumber;
        List<Integer> key = Lists.newArrayList(3, 4, 1, 5, 2);
        List<String> strings = getSubstrings(data, size);
        List<CharacterMatrix> matrices = createMatrices(strings.size());
        for (String encoded : strings) {
            Map<Integer, Integer> columnToLettersNumberMap = getColumnToLettersNumberMap(encoded, rowsNumber, columnsNumber);
            Map<Integer, String> columnToSubstringMap = getColumnToSubstringMap(encoded, columnToLettersNumberMap, key);
            matrices.add(createMatriceWithEncoded(rowsNumber, columnToSubstringMap));
        }

        StringBuilder sb = new StringBuilder();
        for (CharacterMatrix matrix : matrices) {
            sb.append(readByKeyOrder(matrix, key));
        }
        return sb.toString();
    }

    private String readByKeyOrder(CharacterMatrix matrix, List<Integer> key) {
        StringBuilder sb = new StringBuilder();
        key.stream().forEach(k -> sb.append(matrix.getColumn(k)));
        return sb.toString();
    }

    private CharacterMatrix createMatriceWithEncoded(int rowsNumber, Map<Integer, String> columnToSubstringMap) {
        CharacterMatrix characterMatrix = new CharacterMatrix(rowsNumber, columnToSubstringMap.size());
        columnToSubstringMap.entrySet().stream().forEach(e -> characterMatrix.setColumn(e.getKey(), e.getValue()));
        return characterMatrix;
    }

    private Map<Integer, String> getColumnToSubstringMap(String encoded, Map<Integer, Integer> columnToLettersNumberMap, List<Integer> key) {
        Map<Integer, String> columnToSubstringMap = new HashMap<>();
        int startIndex = 0;
        for (Integer currentColumn : key) {
            columnToSubstringMap.put(currentColumn, getColumnSubstring(encoded, startIndex, columnToLettersNumberMap.get(currentColumn)));
            startIndex += columnToLettersNumberMap.get(currentColumn);
        }
        return columnToSubstringMap;
    }

    private String getColumnSubstring(String encoded, Integer startIndex, Integer offset) {
        return encoded.length() > startIndex + offset ? encoded.substring(startIndex, startIndex + offset) : encoded.substring(startIndex);
    }

    private Map<Integer, Integer> getColumnToLettersNumberMap(String encoded, int rowsNumber, int columnsNumber) {
        Map<Integer, Integer> columnLettersNumberMap = new HashMap<>();
        final int mod = encoded.length() % columnsNumber;
        final int standardLengthOfColumn = (int) Math.floor((rowsNumber * columnsNumber) / columnsNumber);
        for (int i = 0; i < columnsNumber; i++) {
            columnLettersNumberMap.put(i, i + 1 <= mod ? standardLengthOfColumn + 1 : standardLengthOfColumn);
        }
        return columnLettersNumberMap;
    }

    private List<String> getSubstrings(String data, int size) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < data.length(); i = i + size) {
            if (data.length() > i + size) {
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
            matrices.add(new CharacterMatrix(5, 5));
        }
        return matrices;
    }
}
