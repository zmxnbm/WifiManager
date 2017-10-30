package com.example.hasee.test.Activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hasee.test.Bean.WiFiInfo;
import com.example.hasee.test.Manager.WiFiManager;
import com.example.hasee.test.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WiFiManager wifiManage;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        wifiManage = new WiFiManager();
        try {
            Init();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void Init() throws Exception {
        List<WiFiInfo> wifiInfos = wifiManage.Read();
        ListView wifiInfosView = (ListView) findViewById(R.id.WifiInfoView);
        WifiAdapter ad = new WifiAdapter(wifiInfos, MainActivity.this);
        wifiInfosView.setAdapter(ad);
    }

    public class WifiAdapter extends BaseAdapter {
        List<WiFiInfo> wifiInfo = null;
        Context con;

        public WifiAdapter(List<WiFiInfo> wifiInfos, Context con) {
            this.wifiInfo = wifiInfos;
            this.con = con;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return wifiInfo.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return wifiInfo.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            convertView = LayoutInflater.from(con).inflate(android.R.layout.simple_list_item_1, null);
            TextView tv = (TextView) convertView.findViewById(android.R.id.text1);
            tv.setText("Wifi:" + wifiInfo.get(position).ssid + "\n密码:" + wifiInfo.get(position).password);
            return convertView;
        }
    }
}

