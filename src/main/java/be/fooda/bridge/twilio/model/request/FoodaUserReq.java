package be.fooda.bridge.twilio.model.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
public class FoodaUserReq {
    private final String login;
    private final String password;
}
