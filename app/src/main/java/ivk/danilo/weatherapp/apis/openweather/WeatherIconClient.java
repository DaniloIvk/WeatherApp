package ivk.danilo.weatherapp.apis.openweather;

import java.util.Arrays;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class WeatherIconClient {
    private static final String BASE_URL = "https://openweathermap.org/img/wn/";
    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS, ConnectionSpec.RESTRICTED_TLS))
            .build();
    private static final Retrofit retrofit = new Retrofit.Builder()
            .client(CLIENT)
            .baseUrl(BASE_URL)
            .build();

    public static Retrofit getClient() {
        return retrofit;
    }
}
