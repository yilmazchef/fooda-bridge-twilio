package be.fooda.bridge.twilio.controller;

import be.fooda.bridge.twilio.service.FoodaTwilioSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("sms")
@Log4j2
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FoodaSmsController {

    private final FoodaTwilioSender sender;

    @PostMapping("{to}")
    public ResponseEntity<String> sendSms(@PathVariable String to, @RequestParam String message) {

        String phoneNumber = "";

        if (!to.startsWith("32") || !to.startsWith("4") || !to.startsWith("+32"))
            return ResponseEntity.badRequest().body("Phone number is not valid");

        if (to.startsWith("32")) phoneNumber = "+" + to;
        else if (to.startsWith("4")) phoneNumber = "+32" + to;

        sender.send(phoneNumber, message);

        return ResponseEntity.ok("Sms is sent to " + to);
    }
}
