/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kioki_ipr1;

import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author dzmitry
 */
public class RSACustom {

    public BigInteger p;
    public BigInteger q;

    public BigInteger r;
    public BigInteger phi;
    
    public BigInteger e;
    public BigInteger d;
    
    public int bitlength = 1024;
    public Random ri;

    public RSACustom() {
        ri = new Random();

        p = BigInteger.probablePrime(bitlength, ri);
        q = BigInteger.probablePrime(bitlength, ri);

        r = p.multiply(q);

        phi = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));
        
        e = BigInteger.probablePrime(bitlength / 2, ri);
        while (phi.gcd(e).compareTo(BigInteger.ONE) > 0 && e.compareTo(phi) < 0) {
            e.add(BigInteger.ONE);
        }
        
        d = e.modInverse(phi);
    }

    public RSACustom(BigInteger e, BigInteger d, BigInteger r) {
        this.e = e;
        this.d = d;
        this.r = r;
    }
    
    // Encrypt message
    public byte[] encrypt(byte[] message) {
        return (new BigInteger(message)).modPow(d, r).toByteArray();
    }

    // Decrypt message
    public byte[] decrypt(byte[] message) {
        return (new BigInteger(message)).modPow(e, r).toByteArray();
    }
}
