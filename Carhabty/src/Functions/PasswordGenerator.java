/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GARCII
 */
public class PasswordGenerator {
    
                  public static String MergePasswordSalt(String password,String salt){
                      byte[] hashf = null;
                      try {
                          String salted=password+"{"+salt+"}";
                          
                          MessageDigest messageDigest = MessageDigest.getInstance("SHA-512");
                          
                          byte[] garci = messageDigest.digest(salted.getBytes());
                          
                          for (int i = 1; i < 5000 ; i++) {
                              
                              byte[] c = new byte[garci.length + salted.getBytes().length];
                              
                              System.arraycopy(garci, 0, c, 0, garci.length);
                              System.arraycopy(salted.getBytes(), 0, c, garci.length, salted.getBytes().length);
                              
                              garci = messageDigest.digest(c);
                              
                          }
                          
                          hashf = Base64.getEncoder().encode(garci);
                          // System.out.println("Result: " + new String(hashf));
                          
                         
                      } catch (NoSuchAlgorithmException ex) {
                          Logger.getLogger(PasswordGenerator.class.getName()).log(Level.SEVERE, null, ex);
                      }
                     
         return new String(hashf);   }
    
}
