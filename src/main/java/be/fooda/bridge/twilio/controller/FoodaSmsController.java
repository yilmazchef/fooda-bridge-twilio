package be.fooda.bridge.twilio.controller;

import be.fooda.bridge.twilio.model.MessageRequest;
import be.fooda.bridge.twilio.model.http.HttpFailureMessages;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("sms")
@Slf4j
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
    public ResponseEntity sendMessages(@RequestBody MessageRequest messageRequest) {

        String regex = "^\\+[0-9]{1,3}\\.[0-9]{4,14}(?:x.+)?$";
        Pattern pattern = Pattern.compile(regex);

        for (String number : messageRequest.getNumbers()) {
            Matcher matcher = pattern.matcher(number);

            if (matcher.matches()) {
                Message message = Message.creator(
                        new PhoneNumber(number),
                        new PhoneNumber(twilioPhoneNumber),
                        messageRequest.getMessage()).create();

                log.trace("Message is send to : " + number);
                return ResponseEntity.status(HttpStatus.OK).body(message);

            } else {
                log.error("Phone number format of " + number + " is not valid!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.PHONE_NUMBER_FORMAT_IS_NOT_VALID);
            }
        }

        log.error("Unexpected error occurred..");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(HttpFailureMessages.UNEXPECTED_ERROR_OCCURRED);
    }
}
