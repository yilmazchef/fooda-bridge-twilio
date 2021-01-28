package be.fooda.bridge.twilio.view.client;

import be.fooda.bridge.twilio.view.controller.FoodaTwilioPasswordService;
import com.twilio.Twilio;
import com.twilio.rest.verify.v2.service.Verification;
import com.twilio.rest.verify.v2.service.VerificationCheck;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FoodaUserTwilioClient {

    FoodaTwilioPasswordService service;

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    public String getSmsCode(final String login) {

        Twilio.init(accountSid, authToken);
        Verification verification = Verification.creator(
                service.getServiceSID(),
                '+' + login,
                "sms")
                .create();
        return verification.getServiceSid();
    }


    public String getVerificationStatus(String login, String code, String serviceSID) {
        Twilio.init(accountSid, authToken);
        VerificationCheck verificationCheck = VerificationCheck.creator(
                serviceSID,
                code)
                .setTo('+' + login).create();

        return verificationCheck.getStatus();
    }


    public String getHashCode(final String code) {
        return DigestUtils.sha256Hex(code);
    }
}
