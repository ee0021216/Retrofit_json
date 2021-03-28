package tw.retrofit_json;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BikerService {

/*  選擇 GET 還是POST


    本來天氣的是這樣
    https://api.openweathermap.org/data/2.5/weather?lat=24.22134756920595&lon=120.5569322280767&lang=zh_tw&appid=you Key&units=metric

    後段是
    data/2.5/weather?
     lat=24.123& lon=120.123& lang=zh_tw& appid=KEY
    可以帶數代網址後面 */

/*    @GET("data/2.5/weather?")
    Call<bike.life.WeatherResponse> getCurrentWeatherData(@Query("lat") String lat,
                                                          @Query("lon") String lon,
                                                          @Query("lang") String lang,
                                                          @Query("APPID") String app_id);*/

    /*這個 觀光局的OpenData不用帶參數 這樣就好*/
    @GET("Bike_f.json")
    Call<BikerResponse> getOpenData();
}
