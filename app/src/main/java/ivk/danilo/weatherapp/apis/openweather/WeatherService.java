package ivk.danilo.weatherapp.apis.openweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherService {
    @GET("weather")
    Call<WeatherResource> getWeather(
            @Query("q") String city,
            @Query("appid") String apiKey,
            @Query("units") String units,
            @Query("lang") String language
    );
}
