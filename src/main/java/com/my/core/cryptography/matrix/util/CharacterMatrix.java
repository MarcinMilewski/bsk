package com.my.core.cryptography.matrix.util;

import com.my.core.util.CharacterIterator;

public class CharacterMatrix {
    private final int width;
    private final int height;

    private Character[][] matrix;

    public CharacterMatrix(int width, int height) {
        if (width < 0 || height < 0) throw new IllegalArgumentException();
        this.width = width;
        this.height = height;
        matrix = new Character[width][height];
    }

    public CharacterMatrix(int width, int height, String data) {
        if (width < 0 || height < 0) throw new IllegalArgumentException();
        this.width = width;
        this.height = height;
        matrix = new Character[width][height];
        set(data);
    }

    public void set(String data) {
        CharacterIterator characterIterator = new CharacterIterator(data);
        for (int j = 0; j < height; ++j) {
            for (int i = 0; i < width; ++i) {
                if (characterIterator.hasNext()) {
                    matrix[j][i] = characterIterator.next();
                } else {
                    matrix[j][i] = null;
                }
            }
        }
    }

    public String getByColumn(int column) {
        if (column >= width) {
            throw new IllegalArgumentException();
        } else {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < height; i++) {
                if (matrix[i][column] != null) {
                    sb.append(matrix[i][column]);
                }
            }
            return sb.toString();
        }
    }
}
