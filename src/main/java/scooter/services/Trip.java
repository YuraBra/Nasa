package scooter.services;

import lombok.Getter;
import okhttp3.Request;
import scooter.data.TripObject;
import scooter.util.RequestUtil;

import java.io.IOException;

import static scooter.data.Data.USER_ID;
import static scooter.data.Data.USER_TOKEN;

@Getter
public class Trip extends BaseScooter {
    private String tripStartUrl;
    private String tripFinishUrl;
    private RequestUtil requestUtil;

    public Trip() {
        this.tripStartUrl = this.baseUrl + "trip-service/trips/start";
        this.tripFinishUrl = this.baseUrl + "trip-service/trips/finish";
        this.requestUtil = new RequestUtil();
    }

    public String startTrip(String scooterId) throws IOException {
        return getTrip(scooterId, tripStartUrl);
    }

    public String finishTrip(String scooterId) throws IOException {
        return getTrip(scooterId, tripFinishUrl);
    }

    private String getTrip(String scooterId, String url) throws IOException {
        TripObject tripObject = new TripObject(USER_ID, scooterId);
        Request request = requestUtil.postRequest(url, tripObject, USER_TOKEN);
        return requestUtil.getResponse(request);
    }
}