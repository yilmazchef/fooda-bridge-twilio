package be.fooda.bridge.twilio.view.controller;

import be.fooda.bridge.twilio.model.response.FoodaUserRes;
import be.fooda.bridge.twilio.service.FoodaUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FoodaUserController {

    private FoodaUserService userService;

    @GetMapping("send_sms")
    public ResponseEntity<FoodaUserRes> sendSms(@RequestParam final String login) {
        return userService.getSmsCodeSaveCodeAndReturn(login)
                .map(res -> new ResponseEntity<>(res, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.CONFLICT));
    }

    @GetMapping("validate_sms")
    public ResponseEntity<FoodaUserRes> validateSms(@RequestParam final String login, @RequestParam final String password, @RequestParam final String serviceSID) {
        if (userService.validateUserByCode(login, password, serviceSID).equals(Boolean.TRUE)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

}
