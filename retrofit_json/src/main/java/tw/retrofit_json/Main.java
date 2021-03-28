package tw.retrofit_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main extends AppCompatActivity {
    ArrayList<HashMap<String, String>> arrayList;
    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        setupViewComponent();
    }

    private void setupViewComponent() {
        arrayList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.listview);

        // OpenData網址 自行車道 https://gis.taiwan.net.tw/XMLReleaseALL_public/Bike_f.json

        String url = "https://gis.taiwan.net.tw/XMLReleaseALL_public/";
        //他要拆解成這樣 https://gis.taiwan.net.tw/XMLReleaseALL_public/ 這段在這邊
        //  第二段 Bike_f.json 在BikerService.class另一邊

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BikerService service = retrofit.create(BikerService.class);
        retrofit2.Call<BikerResponse> call =service.getOpenData();

        call.enqueue(new Callback<BikerResponse>() {
            @Override
            public void onResponse(Call<BikerResponse> call, Response<BikerResponse> response) {
                if(response.code()==200){
                    //這個可以一直點到最後一層
//                    String a = response.body().xml_head.Infos.Info.get(0).Add;
                    //但是我要最後一層不想一直 點 下去 所以直接用變數  解到最後一層
                    ArrayList<Info> Info = response.body().xml_head.Infos.Info;
                    for (int i=0;i<Info.size();i++){
                        String name = Info.get(i).Name;
                        String add = Info.get(i).Add;
                        String length = Info.get(i).Bike_length;


                        HashMap<String, String> data = new HashMap<>();
                        data.put("name", name);
                        data.put("add", add);
                        data.put("length", length);


                        arrayList.add(data);
                        SimpleAdapter adapter = new SimpleAdapter(
                                Main.this,
                                arrayList,
                                R.layout.listview_layout,
                                new String[]{"name", "add", "length"},
                                new int[]{R.id.t001, R.id.t002, R.id.t003});
                        lv.setAdapter(adapter);

                    }



                }
            }

            @Override
            public void onFailure(Call<BikerResponse> call, Throwable t) {
                //錯誤的話
            }
        });


    }
}