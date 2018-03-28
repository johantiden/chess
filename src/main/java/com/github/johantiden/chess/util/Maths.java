package com.github.johantiden.chess.util;

import java.util.Random;

public class Maths {

    public static final Random RANDOM = new Random();

    public static int roundI(double d) {
        return (int)Math.round(d);
    }
    public static int floorI(double d) {
        return (int)Math.floor(d);
    }
    public static int ceilI(double d) {
        return (int)Math.ceil(d);
    }

    public static float roundF(double d) {
        return (float)Math.round(d);
    }

    public static double minmax(double v, double min, double max) {
        if (v > max) {
            return max;
        }
        if (v < min) {
            return min;
        }

        return v;
    }

    public static long minmax(long v, long min, long max) {
        if (v > max) {
            return max;
        }
        if (v < min) {
            return min;
        }

        return v;
    }

    public static float minmaxF(float v, float min, float max) {
        if (v > max) {
            return max;
        }
        if (v < min) {
            return min;
        }

        return v;
    }

    public static double avg(double a, double b) {
        return (a+b)/2;
    }

    public static float avgF(double a, double b) {
        return (float) avg(a,b);
    }

    public static int avgI(int a, int b) {
        return roundI((a + b) / 2.0);
    }

    public static int randomInt(int bound) {
        int i = RANDOM.nextInt(bound);
        return i;
    }

    public static int maxRound(double a, double b) {
        return roundI(Math.max(a, b));
    }
    public static int maxFloor(double a, int b) {
        return floorI(Math.max(a, b));
    }
    public static int maxCeil(double a, int b) {
        return ceilI(Math.max(a, b));
    }

    public static int minRound(double a, double b) {
        return roundI(Math.min(a, b));
    }
    public static int minFloor(double a, double b) {
        return floorI(Math.min(a, b));
    }
    public static int minCeil(double a, double b) {
        return ceilI(Math.min(a, b));
    }

    public static double randomize(double maxJump, double d, double min, double max) {

        double change = Math.random()*2*maxJump - maxJump;
        double v = d + change;
        return minmax(v, min, max);
    }

    public static double randomize(double maxJump, double d) {
        return randomize(maxJump, d, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
    }

    public static double random() {
        return RANDOM.nextDouble();
    }
    public static double random(double limit) {
        return RANDOM.nextDouble()*limit;
    }
}
