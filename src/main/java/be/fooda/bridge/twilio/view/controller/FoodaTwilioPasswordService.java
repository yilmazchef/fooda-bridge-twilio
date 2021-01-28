package be.fooda.bridge.twilio.view.controller;

import com.twilio.Twilio;
import com.twilio.rest.verify.v2.Service;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

@org.springframework.stereotype.Service
public class FoodaTwilioPasswordService {
    // Find your Account Sid and Token at twilio.com/console
    // DANGER! This is insecure. See http://twil.io/secure

    @Value("${twilio.account.sid}")
    private static String accountSid;

    @Value("${twilio.auth.token}")
    private static String authToken;

    public static String getServiceSID() {
        Twilio.init(accountSid, authToken);
        Service service = Service.creator("Fooda_Verification_Service").create();
        return service.getSid();
    }
}