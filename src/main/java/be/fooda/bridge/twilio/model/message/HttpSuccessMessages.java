package be.fooda.bridge.twilio.model.message;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum HttpSuccessMessages {

    MESSAGE_IS_SENT("Message is successfully sent to phone number(s).");

    private String value;
}
