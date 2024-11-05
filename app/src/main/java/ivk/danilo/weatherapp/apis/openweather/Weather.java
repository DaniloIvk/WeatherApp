package ivk.danilo.weatherapp.apis.openweather;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.annotation.NonNull;

import ivk.danilo.weatherapp.R;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Weather {
    private final Context context;
    private final WeatherService service;
    private final WeatherIconService iconService;
    private WeatherResource weather = null;
    private Bitmap weatherIcon = null;

    public Weather(String city, String apiKey, String systemOfUnits, String language, Context context, WeatherCallback callback) {
        this.context = context;
        this.service = WeatherClient.getClient().create(WeatherService.class);
        this.iconService = WeatherIconClient.getClient().create(WeatherIconService.class);

        this.initializeWeather(city, apiKey, systemOfUnits, language, callback);
    }

    public Weather(String city, String apiKey, Context context, WeatherCallback callback) {
        this(city, apiKey, "metric", "en", context, callback);
    }

    private void initializeWeather(String city, String apiKey, String systemOfUnits, String language, WeatherCallback callback) {
        this.service.getWeather(city, apiKey, systemOfUnits, language).enqueue(new Callback<>() {
            /**
             * If the response was successful set weather to response body
             *
             * @param call     call
             * @param response response
             */
            @Override
            public void onResponse(@NonNull Call<WeatherResource> call, @NonNull Response<WeatherResource> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        weather = response.body();
                        initializeWeatherIcon(weather.getWeather().getIconCode(), callback);
                    }
                } catch (Exception e) {
                    Log.e("OpenWeather API Error", "An error occurred while retrieving weather data from the endpoint: " + e.getLocalizedMessage());
                }
            }

            /**
             * If the response was not successful set weather to default data
             *
             * @param call call
             * @param t    throwable
             */
            @Override
            public void onFailure(@NonNull Call<WeatherResource> call, @NonNull Throwable t) {
                Log.e("OpenWeather API Error", "An error occurred while retrieving weather data from the endpoint: " + t.getLocalizedMessage());

                weather = new WeatherResource();
                initializeWeatherIcon(null, callback);
            }
        });
    }

    private void initializeWeatherIcon(String iconCode, WeatherCallback callback) {
        this.iconService.getWeatherIcon(iconCode).enqueue(new Callback<>() {
            /**
             * If the response was successful set weather to response body
             *
             * @param call     call
             * @param response response
             */
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                try {
                    if (response.isSuccessful() && response.body() != null) {
                        weatherIcon = BitmapFactory.decodeStream(response.body().byteStream());
                        callback.onWeatherDataLoaded(weather, weatherIcon);
                    }
                } catch (Exception e) {
                    Log.e("OpenWeather API Error", "An error occurred while retrieving weather data from the endpoint: " + e.getLocalizedMessage());
                }
            }

            /**
             * If the response was not successful set weather to default data
             *
             * @param call call
             * @param t    throwable
             */
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e("OpenWeather API Error", "An error occurred while retrieving weather data from the endpoint: " + t.getLocalizedMessage());

                weatherIcon = BitmapFactory.decodeResource(context.getResources(), getWeatherIconResourceId(iconCode));
                callback.onWeatherDataLoaded(weather, weatherIcon);
            }
        });
    }

    private int getWeatherIconResourceId(String iconCode) {
        switch (iconCode) {
            case "01d":
                return R.drawable.i01d;
            case "01n":
                return R.drawable.i01n;
            case "02d":
                return R.drawable.i02d;
            case "02n":
                return R.drawable.i02n;
            case "03d":
                return R.drawable.i03d;
            case "03n":
                return R.drawable.i03n;
            case "04d":
                return R.drawable.i04d;
            case "04n":
                return R.drawable.i04n;
            case "09d":
                return R.drawable.i09d;
            case "09n":
                return R.drawable.i09n;
            case "10d":
                return R.drawable.i10d;
            case "10n":
                return R.drawable.i10n;
            case "11d":
                return R.drawable.i11d;
            case "11n":
                return R.drawable.i11n;
            case "13d":
                return R.drawable.i13d;
            case "13n":
                return R.drawable.i13n;
            case "50d":
                return R.drawable.i50d;
            case "50n":
                return R.drawable.i50n;
            default:
                return R.drawable.icon_not_found;
        }
    }
}
