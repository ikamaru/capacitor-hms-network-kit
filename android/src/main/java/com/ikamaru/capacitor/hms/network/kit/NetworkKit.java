package com.ikamaru.capacitor.hms.network.kit;

import android.app.Activity;
import android.util.Log;

import com.getcapacitor.JSObject;
import com.huawei.hms.network.httpclient.Callback;
import com.huawei.hms.network.httpclient.HttpClient;
import com.huawei.hms.network.httpclient.Response;
import com.huawei.hms.network.httpclient.Submit;
import com.huawei.hms.network.restclient.RestClient;
import com.huawei.hms.network.restclient.anno.GET;
import com.huawei.hms.network.restclient.anno.Path;
import com.huawei.hms.network.restclient.anno.Query;

import java.io.IOException;

public class NetworkKit {
    private static final String TAG = "NetworkKit";
    Activity activity;
    NetworkKit(Activity activity){
        this.activity=activity;
        // Initialize the object only once, upon the first call.
        com.huawei.hms.network.NetworkKit.init(activity, new com.huawei.hms.network.NetworkKit.Callback() {
            @Override
            public void onResult(boolean result) {
                if (result) {
                    Log.i(TAG, "init success");
                } else {
                    Log.i(TAG, "init failed");
                }
            }
        });
    }

    public void get(String url,String endPoint,String params,OnListener onListener) {
        RestClient restClient = new RestClient.Builder().baseUrl(url)
                .httpClient(new HttpClient.Builder().build())
                .build();
        Api avatarApi = restClient.create(Api.class);
        avatarApi.get(endPoint,params).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Submit<String> submit, Response<String> response){
                Log.i(TAG, "onResponse: "+response);
                Log.i(TAG, "onResponse: "+response.getBody());
                if(response.getBody()!=null){
                    onListener.onSuccess(new JSObject().put("res",response.getBody()));
                }else{
                    onListener.onFailure(new Exception("empty body"));
                }
            }
            @Override
            public void onFailure(Submit<String> submit, Throwable throwable) {
                Log.i(TAG, "onFailure2: "+throwable.getMessage());
                onListener.onFailure(new Exception(throwable.getMessage()));
            }
        });
    }
    interface Api{
        @GET("{endPoint}")
        Submit<String> get(@Path("endPoint") String endPoint, @Query("params") String params);
    }
    interface OnListener{
        void onSuccess(JSObject jsonObject);
        void onFailure(Exception exception);
    }

}
