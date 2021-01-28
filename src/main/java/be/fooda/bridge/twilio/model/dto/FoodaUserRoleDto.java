package be.fooda.bridge.twilio.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class FoodaUserRoleDto {
    private Long userRoleId;
    private String name;
    private Boolean hasAccessToFooda;
    private Boolean hasAccesstoDella;
    private Boolean hasAccesstoResta;
}

