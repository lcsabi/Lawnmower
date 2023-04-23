package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Weather {

    private final ConfigReader configReader;
    private final String uri;

    public Weather() {
        configReader = new ConfigReader();
        String apiLink = configReader.getAPILink();
        String apiKey = configReader.getAPIKey();
        String city = configReader.getCity();
        String parameter = configReader.getParameter();
        uri = apiLink + apiKey + city + parameter;
    }

    public String getCondition() {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();

            HttpResponse<String> getResponse = httpClient.send(
                    getRequest, HttpResponse.BodyHandlers.ofString()
            );

            Gson gson = new Gson();
            JsonObject conditionObject = gson.fromJson(getResponse.body(), JsonObject.class);

            return conditionObject
                    .getAsJsonObject("current")
                    .getAsJsonObject("condition")
                    .get("text").getAsString().toLowerCase();
        } catch (Exception e) {
            System.out.println("API couldn't be called.");
            return "Error.";
        }
    }
}
