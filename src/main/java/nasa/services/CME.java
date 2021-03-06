package nasa.services;

import okhttp3.HttpUrl;
import okhttp3.Request;

import java.io.IOException;

public class CME extends BaseNasa {
    private String BaseCMEUrl;

    public CME() {
        this.BaseCMEUrl = this.baseUrl + "DONKI/CMEAnalysis";
    }

    public String run() throws IOException {
        String url = createUrl();
        Request request = new Request.Builder()
                .url(url)
                .build();

        return getResponse(request);
    }

    private String createUrl(){
        HttpUrl.Builder urlBuilder = HttpUrl.parse(BaseCMEUrl).newBuilder();
        urlBuilder.addQueryParameter("api_key", apiKey);
        return urlBuilder.build().toString();
    }


}
