package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ApiHandler {

    ConfigReader configReader;
    String uri;

    public ApiHandler() {
        configReader = new ConfigReader();
        String endpoint = configReader.getEndpoint();
        String apiKey = configReader.getAPIKey();
        String city = configReader.getCity();
        uri = endpoint + apiKey + city;
    }

    public Weather getWeatherInfo() {
        HttpClient httpClient = HttpClient.newHttpClient();
        try {
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI(uri))
                    .GET()
                    .build();

            HttpResponse<String> getResponse = httpClient.send(
                    getRequest, BodyHandlers.ofString()
            );

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(
                    getResponse.body(),
                    JsonObject.class
            );
            String locationName = jsonObject
                    .getAsJsonObject("location")
                    .get("name")
                    .getAsString();
            String currentConditionText = jsonObject
                    .getAsJsonObject("current")
                    .getAsJsonObject("condition")
                    .get("text")
                    .getAsString();
            int currentCloud = jsonObject
                    .getAsJsonObject("current")
                    .get("cloud")
                    .getAsInt();

            Weather weather = new Weather(locationName, currentConditionText, currentCloud);

            return weather;
        } catch (JsonSyntaxException | InterruptedException | URISyntaxException | IOException e) {
            System.out.println("Error when requesting weather information.");
            return null;
        }
    }
}
