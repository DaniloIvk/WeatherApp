package ivk.danilo.weatherapp.apis.openweather;

import com.google.gson.annotations.SerializedName;

public class WeatherResource {
    @SerializedName("weather")
    private final WeatherInfo[] weather;
    @SerializedName("main")
    private final MainInfo main;

    public WeatherResource() {
        this.weather = new WeatherInfo[]{new WeatherInfo()};
        this.main = new MainInfo();
    }

    public static float roundToOneDecimal(float number) {
        return Math.round(number * 10f) / 10f;
    }

    // Getters
    public WeatherInfo getWeather(int id) {
        return this.weather[id];
    }

    public WeatherInfo getWeather() {
        return this.getWeather(0);
    }

    public MainInfo getMain() {
        return this.main;
    }

    public static class WeatherInfo {
        @SerializedName("description")
        private final String description;
        @SerializedName("icon")
        private final String iconCode;

        public WeatherInfo() {
            this.description = "Description";
            this.iconCode = "Icon Code";
        }

        public String getDescription() {
            return description;
        }

        public String getIconCode() {
            return this.iconCode;
        }
    }

    public static class MainInfo {
        private final String UNIT = "°C";
        @SerializedName("temp")
        private final float currentTemperature;
        @SerializedName("temp_min")
        private final float minimumTemperature;
        @SerializedName("temp_max")
        private final float maximumTemperature;
        @SerializedName("feels_like")
        private final float feelsLikeTemperature;

        public MainInfo() {
            this.currentTemperature = 20;
            this.minimumTemperature = 20;
            this.maximumTemperature = 20;
            this.feelsLikeTemperature = 20;
        }

        public String getCurrentTemperature() {
            return WeatherResource.roundToOneDecimal(this.currentTemperature) + this.UNIT;
        }

        public String getMinimumTemperature() {
            return WeatherResource.roundToOneDecimal(this.minimumTemperature) + this.UNIT;
        }

        public String getMaximumTemperature() {
            return WeatherResource.roundToOneDecimal(this.maximumTemperature) + this.UNIT;
        }

        public String getFeelsLikeTemperature() {
            return WeatherResource.roundToOneDecimal(this.feelsLikeTemperature) + this.UNIT;
        }
    }
}
