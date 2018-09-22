/*
 * Created by 动脑科技-Tim on 17-7-18 下午5:49
 * Copyright (c) 2017. All rights reserved
 *
 * Last modified 17-7-18 下午5:33
 */

package com.dn.tim.customertest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends com.lythonliu.LinkAppCompatActivity {
    @Override
    public String getAppName(){
        return BuildConfig.APP_NAME;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aspact_layout);
    }
}
