package be.fooda.bridge.twilio.controller;

import com.twilio.Twilio;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.twilio.rest.api.v2010.account.Message;

@RestController
@RequestMapping("")
@Log4j2
public class FoodaSmsController {

    public static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    public static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public FoodaSmsController() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

    @PostMapping("sms/from{from}/to/{to}/message/{content}")
    public ResponseEntity<String> sendSms(@PathVariable String from, @PathVariable String to, @PathVariable String content) {
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(to),
                new com.twilio.type.PhoneNumber(from),
                content).create();

        log.info("Twilio sms is sent via sid: " + message.getSid());

        return ResponseEntity.ok("Sms is sent to " + to);
    }
}
