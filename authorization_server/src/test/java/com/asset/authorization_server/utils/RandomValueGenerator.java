package com.asset.authorization_server.utils;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Random;

public class RandomValueGenerator {
    private RandomValueGenerator() {}

    private static final Random generator = new SecureRandom();

    public static String generateAlphaNumericString(int size) {
        byte[] array = new byte[256];
        generator.nextBytes(array);

        String randomString = new String(array, StandardCharsets.UTF_8);

        StringBuilder buffer = new StringBuilder();

        for (int k = 0; k < randomString.length(); k++) {
            char character = randomString.charAt(k);

            if (((character >= 'a' && character <= 'z')
                    || (character >= 'A' && character <= 'Z')
                    || (character >= '0' && character <= '9'))
                    && (size > 0)) {

                buffer.append(character);
                size--;
            }
        }

        return buffer.toString();
    }

    public static int generateRandomInteger(int min, int max) {
        return generator.nextInt(min, max);
    }

    public static long generateRandomLong(long min, long max) {
        return generator.nextLong(min, max);
    }
}
