package fr.cocoraid.prodigysuits.utils;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class UtilMath {

    private static Random rand = new Random();
    public static float randFloat(float min, float max) {
        return rand.nextFloat() * (max - min) + min;
    }

    public static int randInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static byte toPackedByte(float f) { return (byte)(int)(f * 256.0F / 360.0F); }
}
