package com.example.administrator.cordovaapp;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.apache.cordova.ConfigXmlParser;
import org.apache.cordova.CordovaActivity;
import org.apache.cordova.CordovaInterfaceImpl;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CordovaWebViewImpl;
import org.apache.cordova.engine.SystemWebView;
import org.apache.cordova.engine.SystemWebViewEngine;

public class MainActivity extends CordovaActivity {


    private Button bt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化cordova容器
        SystemWebView systemWebView = (SystemWebView) findViewById(R.id.cordovaWebView);
        ConfigXmlParser parser = new ConfigXmlParser();
        parser.parse(this);
        CordovaWebView cordovaWebView = new CordovaWebViewImpl(new SystemWebViewEngine(systemWebView));
        cordovaWebView.init(new CordovaInterfaceImpl(this), parser.getPluginEntries(), parser.getPreferences());

        //launchUrl cordova
        systemWebView.loadUrl(launchUrl);

        bt=(Button) findViewById(R.id.getMessage);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //android 发送数据到cordova
                final Intent intent = new Intent("didShow");
                Bundle b = new Bundle();
                b.putString( "data", "cordova你收到消息就展示到页面" );
                intent.putExtras( b);
                LocalBroadcastManager.getInstance(MainActivity.this).sendBroadcastSync(intent);
                Toast.makeText(MainActivity.this, "发送成功，收到没有不知道", Toast.LENGTH_LONG).show();
            }
        });
    }



}
