package de.cg.cgge.utils;

public class AnimationHelper {

    private static double normalize(double d){
        return Math.min(1, Math.max(0, d));
    }

    public static double linear(double timeFactor) {
        return normalize(
            timeFactor
        );
    }

    public static double linear(long started, long duration) {
        return linear((System.currentTimeMillis() - started) / (double) duration);
    }

    public static double easeIn(double timeFactor) {
        return normalize(
            Math.pow(timeFactor, 2)
        );
    }

    public static double easeIn(long started, long duration) {
        return easeIn((System.currentTimeMillis() - started) / (double) duration);
    }

    public static double easeOut(double timeFactor) {
        return normalize(
            Math.sqrt(timeFactor)
        );
    }

    public static double easeOut(long started, long duration) {
        return easeOut((System.currentTimeMillis() - started) / (double) duration);
    }

    public static double easeInOut(double timeFactor) {
        return normalize(
            Math.pow(timeFactor, 2) / (Math.pow(timeFactor, 2) + Math.pow(1 - timeFactor, 2))
        );
    }

    public static double easeInOut(long started, long duration) {
        return easeInOut((System.currentTimeMillis() - started) / (double) duration);
    }

    public static double easeSigmoid(double timeFactor, int k) {
        return normalize(
            1 / (1 + Math.exp(-k * (timeFactor - 0.5)))
        );
    }

    public static double easeSigmoid(long started, long duration, int k) {
        return easeSigmoid((System.currentTimeMillis() - started) / (double) duration, k);
    }




}
