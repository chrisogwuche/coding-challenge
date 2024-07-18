package org.example.dynamic_algorithm;

public class Knapsack {

    public static void main(String[] args) {
        int[] weights = {1, 3, 4, 5};
        int[] values = {1, 4, 5, 7};
        int W = 7;
        int n = weights.length;

        System.out.println("Maximum value in Knapsack = " + knapsack(W, weights, values, n));
    }

    // Function to solve the 0/1 Knapsack problem
    public static int knapsack(int W, int[] weights, int[] values, int n) {
        int[][] dp = new int[n + 1][W + 1];

        // Build table dp[][] in bottom-up manner
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= W; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;  // Base case: no item or no capacity
                } else if (weights[i - 1] <= w) {
                    // Include the item
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    // Exclude the item
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // The maximum value that can be achieved with n items and capacity W
        return dp[n][W];
    }
}
