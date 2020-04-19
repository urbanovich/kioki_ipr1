/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kioki_ipr1;

import java.math.BigInteger;

/**
 *
 * @author dzmitry
 */
public class Signature {
    
    public static byte[] create(
            byte[] M, 
            BigInteger k, 
            BigInteger r
    ) {
        return (new BigInteger(M)).modPow(k, r).toByteArray();
    }

    public static String toHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            sb.append(String.format("%02X", b[i] & 0xFF));
        }
        return sb.toString().toLowerCase();
    }
    
    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }
}
