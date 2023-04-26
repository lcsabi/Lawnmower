package util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import component.Weather;

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

    private HttpResponse<String> getApiResponse() throws
            IOException,
            InterruptedException,
            URISyntaxException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();

        HttpResponse<String> getResponse = httpClient.send(
                getRequest, BodyHandlers.ofString()
        );

        return getResponse;
    }

    public Weather getWeatherInfo() throws IOException, URISyntaxException, InterruptedException {
        HttpResponse<String> getResponse = getApiResponse();

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
        boolean isDay = jsonObject
                .getAsJsonObject("current")
                .get("is_day")
                .getAsInt() == 1;

        Weather weather = new Weather(locationName, currentConditionText, currentCloud, isDay);
        return weather;
    }
}
