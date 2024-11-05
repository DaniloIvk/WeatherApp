package ivk.danilo.weatherapp.apis.openweather;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WeatherIconService {
    @GET("{code}@4x.png")
    Call<ResponseBody> getWeatherIcon(@Path("code") String code);
}
