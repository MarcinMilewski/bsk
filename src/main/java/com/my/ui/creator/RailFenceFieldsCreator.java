package com.my.ui.creator;

import com.my.ui.items.RailFencePropertyItem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RailFenceFieldsCreator implements AlgorithmFieldsCreator {
    @Override
    public List<TextField> getTextFields() {
        List<TextField> textFields = new ArrayList<>();
        TextField depth = new TextField("Depth");
        depth.setName(RailFencePropertyItem.DEPTH.name());
        textFields.add(depth);
        return textFields;
    }
}
