package com.emcloud.tool;

import java.math.BigInteger;

/**
 * 计算cassandra 的Token
 */
public class TokenCalulator {
    public static void main(String[] args) {

       calc(0,2);
        calc(1,2);
//        calc(2,3);
    }

    public static void calc(int node, int total) {
        try {
            if (node < 0 || total <= 0 || node >= total)
                throw new RuntimeException("Invalid input: " + node + " " + total);

            BigInteger token = BigInteger.valueOf(node);
            BigInteger pow = BigInteger.valueOf(2).pow(127).subtract(BigInteger.ONE);
            token = token.multiply(pow).divide(BigInteger.valueOf(total));

            System.out.println("Token " + node + " of " + total + ": " + token.abs().toString());

            return;
        } catch (Throwable t) {
            t.printStackTrace();
            System.err.println("Usage: calculate_token.sh [node] [total]");
        }
    }
}
