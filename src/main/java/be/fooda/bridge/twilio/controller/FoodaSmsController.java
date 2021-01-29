package be.fooda.bridge.twilio.controller;

import be.fooda.bridge.twilio.model.MessageRequest;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sms")
public class FoodaSmsController {

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Autowired
    public FoodaSmsController(
            @Value("${twilio.account.sid}") String twilioAccountSid,
            @Value("${twilio.auth.token}") String twilioAuthToken) {
        Twilio.init(twilioAccountSid, twilioAuthToken);
    }

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessages(@RequestBody MessageRequest messageRequest) {

        messageRequest.getNumbers().forEach(number -> {
            Message message = Message.creator(
                    new PhoneNumber(number),
                    new PhoneNumber(twilioPhoneNumber),
                    messageRequest.getMessage()).create();

            System.out.println("Sent message w/ sid: " + message.getSid());
        });
    }
}
