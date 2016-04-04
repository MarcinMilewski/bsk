package com.my.ui.creator.railfence;

import com.my.ui.creator.AlgorithmFieldsCreator;
import com.my.ui.items.RailFencePropertyItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RailFenceFieldsCreator implements AlgorithmFieldsCreator {
    @Override
    public List<TextField> getTextFields() {
        List<TextField> textFields = new ArrayList<>();
        TextField depth = new TextField();
        depth.setName(RailFencePropertyItem.DEPTH.name());
        depth.setText("3");
        depth.setEditable(false);
        textFields.add(depth);
        return textFields;
    }
}
