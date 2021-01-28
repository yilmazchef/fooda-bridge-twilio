package be.fooda.bridge.twilio.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FoodaUserRes {
    private Long userId;
    private String login;
    private String password;
    private Set<FoodaUserRoleRes> roles;
}
