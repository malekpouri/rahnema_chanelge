package ir.utux;

public class SafeBox {
    public static void main(String[] args) {
        String halghe = "RTIGFIPR";
        String ramz ="RI";
        System.out.println(minimumTime(halghe, ramz));
    }

    public static int minimumTime(String halghe, String ramz) {
        int time = 0;
        int currentPosition = 0;
        for (char c : ramz.toCharArray()) {
            int newPosition = halghe.indexOf(c);
            int clockwise = Math.abs(newPosition - currentPosition);
            int counterClockwise = halghe.length() - clockwise;
            time += Math.min(clockwise, counterClockwise) + 1;
            currentPosition = newPosition;
        }
        return time;
    }
}
