/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Functions;



import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class sendSmsToUser {
    public static int sendSmsToUser(String phonenumber, String code) throws Exception {

        //   String phone= phonenumber.getText();
  
        String username = "daflexx";
        String key = "7C19F014-E456-71F7-EFAF-8163FFF0317D";
        String message="Host AND Guest " + code;
       String smssend = "https://api-mapper.clicksend.com/http/v2/send.php?method=http&username="+username+"&key="+key+"&to="+phonenumber+"&message=Votre%20compte%20a%20été%bloqué"+code+"";
        System.out.println("Sending SMS to "+ phonenumber + " --- Verification Code is "+code);
       
        
        URL sms = new URL(smssend);
        StringBuilder result = new StringBuilder();
        HttpURLConnection conn = (HttpURLConnection) sms.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
                System.out.println(conn);
                       System.out.println(conn.getResponseCode());
        return (conn.getResponseCode());


    }
}