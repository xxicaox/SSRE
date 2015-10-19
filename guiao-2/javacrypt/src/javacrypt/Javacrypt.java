/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacrypt;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.crypto.*;
import java.security.AlgorithmParameters;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.KeySpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author gzarda
 */
public class Javacrypt {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, ShortBufferException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, FileNotFoundException, IOException {
        // TODO code application logic here
          
    byte[] input = " www.java2s.com ".getBytes();
    
    byte[] keyBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09,
        0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};

    int novo = 113915;
    SecretKeySpec key = new SecretKeySpec (keyBytes, "RC4");
    System.out.println(key.hashCode()+ " ---> 677327b6 "+ keyBytes);
    Cipher cipher = Cipher.getInstance("RC4");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    byte[] b = new byte[8];
    FileInputStream fis = new FileInputStream("/tmp/text1.txt");
    CipherInputStream cis = new CipherInputStream(fis, cipher);
    FileOutputStream fos = new FileOutputStream("/tmp/encrypted.txt");

    int i = cis.read(b);
    while (i != -1) {
        fos.write(b, 0, i);
        i = cis.read(b);
    }
    fis.close();
    fos.close();
    // decryption pass

    cipher.init(Cipher.DECRYPT_MODE, key);
    fis = new FileInputStream("/tmp/encrypted.txt");
    fos = new FileOutputStream("/tmp/text2.txt");
    CipherOutputStream cos = new CipherOutputStream(fos, cipher);
    i = fis.read(b);
    while (i != -1) {
        cos.write(b, 0, i);
        i = fis.read(b);
    }
    cos.flush();
    fos.close();
    fis.close();
    }
    
}
