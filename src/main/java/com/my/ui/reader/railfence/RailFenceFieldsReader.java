package com.my.ui.reader.railfence;

import com.my.core.cryptography.railfence.properties.RailfenceProperty;
import com.my.ui.items.RailFencePropertyItem;
import com.my.ui.reader.AlgorithmFieldsReader;

import java.awt.*;
import java.util.*;

public class RailFenceFieldsReader implements AlgorithmFieldsReader {

    @Override
    public Properties read(java.util.List<TextField> textFields) {
        if (textFields.isEmpty()) throw new IllegalArgumentException();
        Properties properties = new Properties();
        for (TextField textField : textFields) {
            RailFencePropertyItem railFencePropertyItem = RailFencePropertyItem.valueOf(textField.getName());
            switch (railFencePropertyItem) {
                case DEPTH: properties.setProperty(RailfenceProperty.DEPTH.name(), textField.getText()); break;
            }
        }
        return properties;
    }
}
