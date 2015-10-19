/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clsrv;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *
 * @author gzarda
 */
public class key {

    public key() throws FileNotFoundException, IOException, KeyStoreException, CertificateException {
    try {
            
            //ler a chave geral
            FileInputStream kfis = new FileInputStream("keyf");
            byte[] key = new byte[16];
            kfis.read(key);
            kfis.close();
            
            
            //criar key generator
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            
            kg.init(sr);
            
            //criar chave
            SecretKey sk = kg.generateKey();
            
            //criar key Store
            KeyStore ks = KeyStore.getInstance("JCEKS");
            ks.load(null, null);            
            
            //guardar a secret key
            ks.setKeyEntry("345", sk, "123".toCharArray(), null);
            
            //criar key store
            FileOutputStream writeStream = new FileOutputStream("novaks.jks");
            
            
            //guardar key store
            //System.out.println(key.toString().toCharArray());
            ks.store(writeStream, "111".toCharArray());
            
            writeStream.close();
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
}
}
