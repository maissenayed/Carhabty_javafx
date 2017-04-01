package Services;
        
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMSServices
{

   public void sendSMS(String phone, String messagetoSend) {
    String accountSid = "AC0deaf0ea6bc9718497a1c232e955f0c0"; // Your Account SID from www.twilio.com/user/account
    String authToken = "b10d9182bd6b718a4ce2da7b3222e172"; // Your Auth Token from www.twilio.com/user/account

    Twilio.init(accountSid, authToken);

    Message message = Message.creator(
    new PhoneNumber("+216"+phone),     // To number
    new PhoneNumber("+16572422389"),  // From number
    messagetoSend                    // SMS body
    ).create();

    System.out.println(message.getSid());

   }
   
 
}