package com.example.codedemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.nfc.cardemulation.HostNfcFService;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    TextView tv ;
    List<String> list = new ArrayList<>();
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    Log.e("longjiang",list.toString());
                    tv.setText(list.toString());
                    break;
            }
        }
    };
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.text);
        List<String> camList = new ArrayList<>();
        camList.add("com.sunmi.ota;");
        camList.add("com.mediatek.camera");
        CameraIdWhiteList cameraIdWhiteList = new CameraIdWhiteList("111",camList);
        Log.e("longjiang",cameraIdWhiteList.toString());
        new Thread(){
            @Override
            public void run() {
                super.run();
                readRawTextFile(getApplicationContext(),R.raw.names);
                //获取所有股票代码
                //runOkHttpGetRequest();
                //挑选
            }
        }.start();

    }


    public void runOkHttpGetRequest(String code) throws JSONException {
        OkHttpClient client = new OkHttpClient();
        Map<String,String> paramsMap=new HashMap<>();
        paramsMap.put("ut","fa5fd1943c7b386f172d6893dbfba10b");
        paramsMap.put("fields","f43,f44,f45,f46,f58,f50,f116");
        paramsMap.put("fltt","2");
        paramsMap.put("ndays","1");
        paramsMap.put("invt","2");
        paramsMap.put("secid",code);
        paramsMap.put("_","1578882361472");
        FormBody.Builder builder = new FormBody.Builder();
//        http://push2.eastmoney.com/api/qt/stock/get?ut=fa5fd1943c7b386f172d6893dbfba10b&invt=2&fltt=2&fields=f43,f57,f58,f169,f170,f46,f44,f51,f168,f47,f164,f163,f116,f60,f45,f52,f50,f48,f167,f117,f71,f161,f49,f530,f135,f136,f137,f138,f139,f141,f142,f144,f145,f147,f148,f140,f143,f146,f149,f55,f62,f162,f92,f173,f104,f105,f84,f85,f183,f184,f185,f186,f187,f188,f189,f190,f191,f192,f107,f111,f86,f177,f78,f110,f262,f263,f264,f267,f268,f250,f251,f252,f253,f254,f255,f256,f257,f258,f266,f269,f270,f271,f273,f274,f275,f127,f199,f128,f193,f196,f194,f195,f197,f80,f280,f281,f282,f284,f285,f286,f287&secid=0.002641&cb=jQuery112405831440079032297_1578892365285&_=1578892365407
//        http://push2.eastmoney.com/api/qt/stock/get?invt=2&fltt=2&ndays=1&fields=f43,f44,f45,f46&ut=fa5fd1943c7b386f172d6893dbfba10b&secid=0.002605&_=1578882361472
        Request request = new Request.Builder()
                .url(buildUrlWithParams("http://push2.eastmoney.com/api/qt/stock/get",paramsMap))
                .get()
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e(getClass().getName(),"onFailure"+e.toString());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Gson gson = new Gson();
                assert response.body() != null;
                String string = response.body().string();
                if(!string.contains("-")){
                    DataBean dataBean = gson.fromJson(string, DataBean.class);
                    // 阳线 最高-当前/当前》0.1 最高-当前/当前-最低》2
//                    if (dataBean.getData().getF44()>dataBean.getData().getF46() && dataBean.getData().getF43()>dataBean.getData().getF46()&& (dataBean.getData().getF44()-dataBean.getData().getF46())/dataBean.getData().getF46()>0.1&& (dataBean.getData().getF44()-dataBean.getData().getF46())/(dataBean.getData().getF46()-dataBean.getData().getF45())>2){
//                         //map.put(dataBean.getData().getF58(),(dataBean.getData().getF44()-dataBean.getData().getF46())/dataBean.getData().getF46());
//                        list.add(dataBean.getData().getF58());
//                    }
//&& dataBean.getData().getF116() > 20
                    if (dataBean.getData().getF46()>dataBean.getData().getF43() && dataBean.getData().getF46()/dataBean.getData().getF43()-1<0.0005
                            && dataBean.getData().getF50() > 0.5&& String.valueOf(dataBean.getData().getF116()).contains("E9")){
                        Log.e(getClass().getName(),"getF116"+dataBean.getData().getF116());
                         //map.put(dataBean.getData().getF58(),(dataBean.getData().getF44()-dataBean.getData().getF46())/dataBean.getData().getF46());
                        list.add(dataBean.getData().getF58()+"  "+code +"\n");
                    }
                }

            }
        });
    }

    public static String buildUrlWithParams(String baseUrl, Map<String, String> params) {
        StringBuilder urlBuilder = new StringBuilder(baseUrl);

        if (params != null && !params.isEmpty()) {
            urlBuilder.append("?");

            for (Map.Entry<String, String> entry : params.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                urlBuilder.append(key).append("=").append(value).append("&");
            }

            urlBuilder.deleteCharAt(urlBuilder.length() - 1);
        }

        return urlBuilder.toString();
    }


    public String readRawTextFile(Context context, int resId) {
        InputStream inputStream = context.getResources().openRawResource(resId);

        InputStreamReader inputReader = new InputStreamReader(inputStream);
        BufferedReader bufferReader = new BufferedReader(inputReader);

        StringBuilder stringBuilder = new StringBuilder();
        String line;
        try {
            while ((line = bufferReader.readLine()) != null) {
                //this.line = line;
                runOkHttpGetRequest(line);
                stringBuilder.append(line);
            }
            handler.sendEmptyMessage(1);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return stringBuilder.toString();
    }


}
