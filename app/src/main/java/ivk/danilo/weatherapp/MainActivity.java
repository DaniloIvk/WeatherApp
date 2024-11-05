package ivk.danilo.weatherapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ivk.danilo.weatherapp.apis.openweather.Weather;
import ivk.danilo.weatherapp.apis.openweather.WeatherCallback;
import ivk.danilo.weatherapp.apis.openweather.WeatherResource;

public class MainActivity extends AppCompatActivity implements WeatherCallback {
    private TextView weatherDescriptionTextView;
    private TextView currentTemperatureTextView;
    private TextView minimumTemperatureTextView;
    private TextView maximumTemperatureTextView;
    private TextView feelsLikeTemperatureTextView;
    private ImageView weatherImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.initializeViews();

        new Weather(
                getString(R.string.default_location),
                getString(R.string.openweather_key),
                this,
                this
        );
    }

    private void initializeViews() {
        this.weatherDescriptionTextView = findViewById(R.id.weather_description);
        this.currentTemperatureTextView = findViewById(R.id.current_temperature);
        this.minimumTemperatureTextView = findViewById(R.id.minimum_temperature);
        this.maximumTemperatureTextView = findViewById(R.id.maximum_temperature);
        this.feelsLikeTemperatureTextView = findViewById(R.id.feels_like_temperature);
        this.weatherImageView = findViewById(R.id.weather_forecast_image);
    }

    @Override
    public void onWeatherDataLoaded(WeatherResource weather, Bitmap icon) {
        this.weatherDescriptionTextView.setText(weather.getWeather().getDescription());
        this.currentTemperatureTextView.setText(weather.getMain().getCurrentTemperature());
        this.minimumTemperatureTextView.setText(weather.getMain().getMinimumTemperature());
        this.maximumTemperatureTextView.setText(weather.getMain().getMaximumTemperature());
        this.feelsLikeTemperatureTextView.setText(weather.getMain().getFeelsLikeTemperature());

        if (icon != null) {
            this.weatherImageView.setImageBitmap(icon);
        }
    }
}
