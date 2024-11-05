package ivk.danilo.weatherapp.apis.openweather;

import java.util.Arrays;

import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherClient {
    private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/";
    private static final OkHttpClient CLIENT = new OkHttpClient.Builder()
            .connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.COMPATIBLE_TLS))
            .build();
    private static final Retrofit retrofit = new Retrofit.Builder()
            .client(CLIENT)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static Retrofit getClient() {
        return retrofit;
    }
}
