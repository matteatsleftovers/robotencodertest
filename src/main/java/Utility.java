class Utility {
    static double clamp(double value, double low, double high) {
        return Math.max(low, Math.min(value, high));
    }
}
