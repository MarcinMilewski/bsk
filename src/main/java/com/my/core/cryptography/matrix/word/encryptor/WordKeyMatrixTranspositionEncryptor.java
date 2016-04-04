package com.my.core.cryptography.matrix.word.encryptor;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.my.core.cryptography.Encryptor;
import com.my.core.cryptography.matrix.util.CharacterMatrix;
import com.my.core.cryptography.matrix.word.properties.WordKeyMatrixTranspositionProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public class WordKeyMatrixTranspositionEncryptor implements Encryptor {
    private final List<Character> alphabet = Lists.newArrayList('a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');

    @Override
    public String encrypt(String data, Properties properties) {
        String keyword = properties.getProperty(WordKeyMatrixTranspositionProperty.KEY_WORD.name());
        if (keyword.isEmpty()) throw new IllegalArgumentException();
        final int rowsNumber = keyword.length();
        final int columnsNumber = rowsNumber;

        List<CharacterMatrix> matrices = createMatrices(data, rowsNumber, columnsNumber);
        return encryptInternal(matrices, keyword);
    }

    private List<CharacterMatrix> createMatrices(String data, int rowsNumber, int columnsNumber) {
        List<String> strings = split(data, rowsNumber, columnsNumber);
        List<CharacterMatrix> matrices = new ArrayList<>();
        for (String string : strings) {
            matrices.add(new CharacterMatrix(columnsNumber, rowsNumber, string.replaceAll("\\s+","")));
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

    private String encryptInternal(List<CharacterMatrix> matrices, String keyword) {
        List<Integer> order = getOrder(keyword);
        StringBuilder sb = new StringBuilder();
        matrices.forEach(m ->  order.forEach(e -> sb.append(m.getColumn(e))));
        return sb.toString();
    }

    private List<Integer> getOrder(String keyword) {
        Map<Integer, Character> positionCharacterMap = Maps.newHashMap();
        for (int i = 0; i < keyword.length(); i++) {
            positionCharacterMap.put(i, keyword.charAt(i));
        }

        List<Integer> order = Lists.newArrayList();
        String lowerCaseKeyword = keyword.toLowerCase();
        for (Character character : alphabet) {
            if (keyword.contains(character.toString())) {
                order.addAll(positionCharacterMap.entrySet()
                        .stream()
                        .filter(entry -> Objects.equal(entry.getValue(), character))
                        .map(Map.Entry::getKey)
                        .collect(Collectors.toList()));
            }
        }
        return order;
    }
}
