package ivk.danilo.weatherapp.apis.openweather;

import android.graphics.Bitmap;

public interface WeatherCallback {
    void onWeatherDataLoaded(WeatherResource weather, Bitmap icon);
}
