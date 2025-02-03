package com.example.javasb_taskms.utils;

import java.util.Random;

public class HexUtils {
    public static String generateHexString(int length) {
        Random random = new Random();
        StringBuilder hexBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int randomValue = random.nextInt(16);
            char hexChar = Integer.toHexString(randomValue).charAt(0);
            hexBuilder.append(hexChar);
        }
        return hexBuilder.toString();
    }
}
