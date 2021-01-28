package be.fooda.bridge.twilio.service;

import be.fooda.bridge.twilio.config.TwilioConfig;
import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

public class FoodaTwilioSender {

    private final TwilioRestClient client;

    public FoodaTwilioSender() {
        client = new TwilioRestClient.Builder(TwilioConfig.getAccountSid(), TwilioConfig.getAuthToken()).build();
    }

    public FoodaTwilioSender(TwilioRestClient client) {
        this.client = client;
    }

    public void send(String to, String message) {
        new MessageCreator(
                new PhoneNumber(to),
                new PhoneNumber(TwilioConfig.getPhoneNumber()),
                message
        ).create(client);
    }

}