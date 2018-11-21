package br.com.projetofinal.util;

import java.util.Base64;

abstract public class Crypto64 {

    public static String encode(String originalInput) {
        return Base64.getEncoder().encodeToString(originalInput.getBytes());
    }

    public static String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

}
