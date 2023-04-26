package component;

public record Weather(
        String locationName,
        String currentCondition,
        int currentCloud,
        boolean isDay)
{}
