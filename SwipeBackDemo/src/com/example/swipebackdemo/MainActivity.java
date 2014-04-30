/*
 * Copyright (C) 2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.swipebackdemo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    private final static boolean DEBUG = true;
    private final static String TAG = "zyw";
    public static int sActivityNumber = 1;
    private Button mStartActivity;
    private Button mFinishActivity;
    private TextView mTipsLeft;
    private TextView mTipsRight;
    private TextView mTipsBottom;
    private SwipeBackLayout mSwipeBackLayout;
    private boolean mAllowEnableSwipeBack = true;
    private ViewGroup mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        // getWindow().setBackgroundDrawable(new ColorDrawable(0));
        // getWindow().getDecorView().setBackgroundDrawable(null);
        setContentView(R.layout.main);
        mRoot = (ViewGroup) findViewById(R.id.root);
        int red = (int) (Math.random() * 255);
        int green = (int) (Math.random() * 255);
        int blue = (int) (Math.random() * 255);
        int color = 0xff000000 | (sActivityNumber * 0x11 % 0xff) << 16
                | (sActivityNumber * 0x11 % 0xff) << 8 | (sActivityNumber * 0x11 % 0xff);
        int randomColor = 0xff000000 | red << 16 | green << 8 | blue;
        color = randomColor;
        Log.i(TAG, ">>>>>>>>>color = " + Integer.toHexString(color));
        getActionBar().setBackgroundDrawable(new ColorDrawable(color));
        mRoot.setBackgroundColor(color);
        mSwipeBackLayout = new SwipeBackLayout(this);
        if (mSwipeBackLayout != null) {
            boolean allowSwipe = true;// "1".equals(SystemProperties.get("debug.zyw.swipeable",
                                      // "1"));
            String pkg = this.getApplicationInfo().packageName;
            String cls = this.getApplicationInfo().className;
            android.util.Log.i(TAG, ">>>>>>>>>>>>>>Activity.onCreate pkg=" + pkg + ", cls=" + cls
                    + ",allowSwipe=" + allowSwipe);
            if ("com.android.launcher".equals(pkg) || "com.example.testmiui".equals(pkg)) {
                mAllowEnableSwipeBack = false;
            }
            mSwipeBackLayout.setEnableGesture(mAllowEnableSwipeBack && allowSwipe);
            mSwipeBackLayout.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        }
        mStartActivity = (Button) findViewById(R.id.start_activity);
        mFinishActivity = (Button) findViewById(R.id.finish_activity);
        mTipsLeft = (TextView) findViewById(R.id.tips_left);
        mTipsRight = (TextView) findViewById(R.id.tips_right);
        mTipsBottom = (TextView) findViewById(R.id.tips_bottom);
        mTipsLeft.setText(getString(R.string.tips) + sActivityNumber);
        mTipsRight.setText(getString(R.string.tips) + sActivityNumber);
        mTipsBottom.setText(getString(R.string.tips) + sActivityNumber);
        sActivityNumber++;

        mStartActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                MainActivity.this.startActivity(new Intent(MainActivity.this, MainActivity.class));
            }
        });

        mFinishActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                MainActivity.this.finish();
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onPostCreate(savedInstanceState);
        if (mSwipeBackLayout != null) {
            mSwipeBackLayout.attachToActivity(this);
        }
    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        sActivityNumber--;
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
    }

}
