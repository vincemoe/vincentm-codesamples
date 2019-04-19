package com.arlucida.arlucida;

import android.graphics.Bitmap;

public class ColorsFilter {
    public Bitmap colorsFilter(Bitmap srcBitmap) {

        int height = srcBitmap.getHeight();
        int width = srcBitmap.getWidth();
        int[] pixelsRGB = new int[width * height];
        srcBitmap.getPixels(pixelsRGB, 0, width, 0, 0, width, height);
        int bitOffset = 64;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int index = y * width + x;
                int a = pixelsRGB[index] & 0xff000000;
                int r = (pixelsRGB[index] >> 16) & 0xff;
                int g = (pixelsRGB[index] >> 8) & 0xff;
                int b = pixelsRGB[index] & 0xff;


                r = ((r + (bitOffset / 2)) - ((r + (bitOffset / 2)) % bitOffset) - 1);
                if(r < 0) { r = 0; }
                g = ((g + (bitOffset / 2)) - ((g + (bitOffset / 2)) % bitOffset) - 1);
                if(g < 0) { g = 0; }
                b = ((b + (bitOffset / 2)) - ((b + (bitOffset / 2)) % bitOffset) - 1);
                if(b < 0) { b = 0; }


                pixelsRGB[index] = 0xff000000 | (r << 16) | (g << 8) | b;
            }
        }
        Bitmap dstBitmap = Bitmap.createBitmap(pixelsRGB, width, height, srcBitmap.getConfig());
        return dstBitmap;
    }

}

