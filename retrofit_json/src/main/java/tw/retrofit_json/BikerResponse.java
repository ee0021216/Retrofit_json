package tw.retrofit_json;


import android.icu.text.IDNA;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BikerResponse {

    //第一階JSONObject
    @SerializedName("XML_Head")
    public XML_Head xml_head;

}
//第一階 JSONObject
class XML_Head {

    //第二階 JSONObject
    @SerializedName("Listname")
    public String Listname;
    @SerializedName("Language")
    public String Language;
    @SerializedName("Orgname")
    public String Orgname;
    @SerializedName("Updatetime")
    public String Updatetime;

    //第二階 繼續往下 第三階JSONObject
    @SerializedName("Infos")
    public Infos Infos;


}
//第三階JSONObject
class Infos {
    //第三階 JSONObject 裡面的 Info 是一個JsonArray
    //繼續往下 第四階
    @SerializedName("Info")
    public ArrayList<Info> Info = new ArrayList<Info>();
}
//第四階
class Info {
    //第四階裡面 我要的的東西 舉例三個
    @SerializedName("Name")
    public String Name;
    @SerializedName("Bike_length")
    public String Bike_length;
    @SerializedName("Add")
    public String Add;
}