/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;

import java.util.Random;

/**
 *
 * @author GARCII
 */
public class SaltGenerator {
    
    
    
    public static String GenerateSalt(){
    char[] chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPKRSTUVWXYZ0123456789".toCharArray();
    StringBuilder sb = new StringBuilder();
    Random random = new Random();
    for (int i = 0; i < 43; i++) {
    char c = chars[random.nextInt(chars.length)];
    sb.append(c);
    }
    String output = sb.toString();

    return output;
    }
    

    
}
