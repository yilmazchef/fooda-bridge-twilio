package be.fooda.bridge.twilio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FoodaUserDto {

    private Long userId;
    @NotNull
    private String login;
    private String password;
    private Set<FoodaUserRoleDto> roles;

}
