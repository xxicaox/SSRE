package clsrv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import clsrv.key;

public class Client {

    static public void main(String[] args) {
        try {
            
            key k = new key();
            
            FileInputStream kfis = new FileInputStream("keyf");
            byte[] key = new byte[16];
            kfis.read(key);
            kfis.close();

            SecretKeySpec keyS = new SecretKeySpec(key, "RC4");

            // Connect to server
            Socket s = new Socket("127.0.0.1", 4567);

            System.out.println("Connected to server...");

            // Open file to upload
            FileInputStream fis = new FileInputStream(args[0]);

            // Init cipher with enryption mode with the key provided above
            Cipher cipher = Cipher.getInstance("RC4");
            cipher.init(Cipher.ENCRYPT_MODE, keyS);

            // Create cipherinputstream which is connected to the file input stream
            CipherInputStream cis = new CipherInputStream(fis, cipher);
            OutputStream sos = s.getOutputStream();
           // FileOutputStream fos = new FileOutputStream("/tmp/encrypted.txt");

           // byte[] b = new byte[8];
            // int i = cis.read(b);
            // while (i != -1) {
            //      sos.write(b, 0, i);
            //      i = cis.read(b);
            //  }        
            //   sos.write(b, 0, i);
            byte[] buffer = new byte[8];
            int bytes_read = cis.read(buffer);

            while (bytes_read != -1) {
                sos.write(buffer);
                bytes_read = cis.read(buffer);
            }

            // Get socket output stream
            System.out.println("Disconnected from server.");

            // Close socket
            sos.close();

            // Close file
            fis.close();

            // Close cipher input stream
            cis.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
