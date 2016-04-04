package com.my.ui.reader.matrix;

import com.my.core.cryptography.matrix.properties.SimpleMatrixTranspositionProperty;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.Properties;

public class SimpleMatrixTranspositionFieldsReader implements AlgorithmFieldsReader {

    @Override
    public Properties read(java.util.List<TextField> textFields) {
        if (textFields.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (TextField textField : textFields) {
            SimpleMatrixTranspositionProperty railFencePropertyItem = SimpleMatrixTranspositionProperty.valueOf(textField.getName());
            switch (railFencePropertyItem) {
                case KEY: properties.setProperty(SimpleMatrixTranspositionProperty.KEY.name(), textField.getText()); break;
            }
        }
        return properties;    }
}
