package com.ikamaru.capacitor.hms.network.kit;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "NetworkKit")
public class NetworkKitPlugin extends Plugin {

    private NetworkKit implementation;
    @Override
    public void load() {
        super.load();
        implementation = new NetworkKit(bridge.getActivity());
    }
    @PluginMethod
    public void get(PluginCall call) {
        String url = call.getString("url");
        String endPoint = call.getString("endPoint");
        String params = call.getString("params");
        if(url==null || url.isEmpty() ){
            call.reject("empty URL");
            return;
        }
        if(endPoint==null || endPoint.isEmpty() ){
            call.reject("empty EndPoint");
            return;
        }
        if(params==null){
            call.reject("empty Params");
            return;
        }

        implementation.get(url, endPoint, params, new NetworkKit.OnListener() {
            @Override
            public void onSuccess(JSObject jsonObject) {
                call.resolve(jsonObject);
            }

            @Override
            public void onFailure(Exception exception) {
                call.reject(exception.getMessage());
            }
        });
    }
}
