package com.jintin.app.adapter;

public enum Color {
    RED, GREEN, BLUE, ORANGE, PURPLE, BLACK;

    int getColor() {
        switch (this) {
            case RED:
                return android.R.color.holo_red_light;
            case BLUE:
                return android.R.color.holo_blue_bright;
            case GREEN:
                return android.R.color.holo_green_light;
            case ORANGE:
                return android.R.color.holo_orange_light;
            case PURPLE:
                return android.R.color.holo_purple;
        }
        return android.R.color.black;
    }
}