package clsrv;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

public class Server {
    static public void main(String[] args) {
        try {
            
            System.out.println("WAAZZZAAA");
            FileInputStream kfis = new FileInputStream("keyf");
            byte[] key = new byte[16];
            kfis.read(key);
            kfis.close();

            SecretKeySpec keyS = new SecretKeySpec(key, "RC4");
            
            key kk = new key();
           // kk.newkey();
            // Create server socket
            ServerSocket ss = new ServerSocket(4567);
            
            // Start upload counter
            int counter = 0;
            
            System.out.println("Server started ...");

            while(true) {
                
                Cipher cipher = Cipher.getInstance("AES");
                cipher.init(Cipher.DECRYPT_MODE, kk.iskeyvalid());
                // Wait for client            
                Socket s = ss.accept();
                
                // Increment counter
                counter++;

                System.out.println("Accepted connection "+counter+".");

                // Open file to write to
                FileOutputStream fos = new FileOutputStream(args[0]+"/"+counter);
                
                CipherOutputStream cos = new CipherOutputStream(fos, cipher);
                
                // Get socket input stream
                InputStream sis = s.getInputStream();             
                
                
                // Get file 50 bytes at a time
                byte[] buffer = new byte[8];
                int bytes_read = sis.read(buffer);
                
                while (bytes_read != -1) {
                   cos.write(buffer,0,bytes_read);
                   bytes_read = sis.read(buffer);
                }
                //cos.write(buffer,0,bytes_read);
                cos.close();
                // Close socket
                s.close();
                //sis.close();
                System.out.println("Closed connection.");
                                
                // Close file
                fos.close();
            }
                     
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
}
