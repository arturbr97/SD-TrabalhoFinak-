/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projetofinal.util;

import java.util.Base64;

/**
 *
 * @author hilton
 */
abstract public class Crypto64 {

    public static String encode(String originalInput) {
        return Base64.getEncoder().encodeToString(originalInput.getBytes());
    }

    public static String decode(String encodedString) {
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        return new String(decodedBytes);
    }

}
