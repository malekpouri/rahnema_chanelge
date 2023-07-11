package ir.utux;

import java.util.Arrays;

public class CalcWaterTrap {
    public static void main(String[] args) {
        int[] height = {0,2,1,3,0,6,2,1,4,2,1};
        System.out.printf("Input: %s%n", Arrays.toString(height));
        System.out.printf("Output: %d%n", calcWaterTrap(height));
        ;
    }

    private static int calcWaterTrap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int n = height.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(height[i], leftMax[i - 1]);
        }

        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(height[i], rightMax[i + 1]);
        }

        int totalWater = 0;
        for (int i = 0; i < n; i++) {
            totalWater += Math.min(leftMax[i], rightMax[i]) - height[i];
        }

        return totalWater;

    }
}
