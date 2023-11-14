package com.example.ooptradingproject.utils;

import javafx.scene.layout.Region;

public class Spacer {
    public static Region getSpacer() {
        Region region = new Region();
        region.setPrefSize(Double.MAX_VALUE, 0.0);
        return region;
    }
}
