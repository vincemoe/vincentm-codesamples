package com.arlucida.arlucida;

import android.graphics.Bitmap;

public class PosterizeFilter {
    public Bitmap posterizeFilter(Bitmap srcBitmap, int numLevels) {

        int height = srcBitmap.getHeight();
        int width = srcBitmap.getWidth();
        int[] pixelsRGB = new int[width * height];

        int [] levels = new int[256];
        if (numLevels != 1) {
            for (int i = 0; i < 256; i++) {
                levels[i] = 255 * (numLevels * i / 256) / (numLevels - 1);
            }
        }

        srcBitmap.getPixels(pixelsRGB, 0, width, 0, 0, width, height);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = y * width + x;
                int a = pixelsRGB[index] & 0xff000000;
                int r = (pixelsRGB[index] >> 16) & 0xff;
                int g = (pixelsRGB[index] >> 8) & 0xff;
                int b = pixelsRGB[index] & 0xff;

                r = levels[r];
                g = levels[g];
                b = levels[b];

                pixelsRGB[index] = 0xff000000 | (r << 16) | (g << 8) | b;
            }
        }
        Bitmap dstBitmap = Bitmap.createBitmap(pixelsRGB, width, height, srcBitmap.getConfig());

        return dstBitmap;
    }
}
