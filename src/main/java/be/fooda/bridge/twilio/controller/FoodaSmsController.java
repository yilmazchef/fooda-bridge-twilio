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
    public ResponseEntity<String> sendSms(@PathVariable String to, @RequestBody String message) {

        sender.send(to, message);

        return ResponseEntity.ok("Sms is sent to " + to);
    }
}
