package com.example.stickhero;

import java.util.Random;

public class RectangularPlatforms {
    public static final int minsizes = 50;
    public static final int maxsizes = 120;
    private int gap;

    public RectangularPlatforms() {
        Random rand = new Random();

        gap = 0;
        while (gap < RectangularPlatforms.minsizes)
            gap = rand.nextInt(RectangularPlatforms.maxsizes);
    }

    public int getGap() {
        return gap;
    }
}
