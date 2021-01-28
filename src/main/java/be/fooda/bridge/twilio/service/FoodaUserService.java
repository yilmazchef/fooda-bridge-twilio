package be.fooda.bridge.twilio.service;

import be.fooda.bridge.twilio.model.response.FoodaUserRes;
import be.fooda.bridge.twilio.view.client.FoodaUserTwilioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FoodaUserService {

    private final FoodaUserTwilioClient twilioClient;

    public Optional<FoodaUserRes> getSmsCodeSaveCodeAndReturn(String login) {
        final String code = twilioClient.getSmsCode(login);
        return Optional.of(FoodaUserRes.builder()
                .login(login)
                .password(code)
                .build());
    }

    public Boolean validateUserByCode(String login, String code, final String serviceSID) {
        return twilioClient.getVerificationStatus(login, code, serviceSID).toLowerCase().equals("approved");
    }
}
