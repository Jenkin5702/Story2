package com.kisetsu.story.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.kisetsu.story.MainActivity;
import com.kisetsu.story.R;

/**
 * Created by kisetsu on 18-9-21.
 */

public class ActivityWelcome extends Activity {
    private int count;
    private TextView tvCount;
    private Handler handler;
    private Button btnSkip;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        tvCount=findViewById(R.id.tv_welcome_count);
        btnSkip=findViewById(R.id.btn_skip);
        handler=new Handler();
        count=4;
        tvCount.setText(String.valueOf(count));

        SharedPreferences sp=getSharedPreferences("config",MODE_PRIVATE);
        boolean signed=sp.getBoolean("signed",false);

        Toast.makeText(ActivityWelcome.this,String.valueOf(signed),Toast.LENGTH_SHORT).show();

        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                count--;
                tvCount.setText(String.valueOf(count));
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.postDelayed(this,1);
                if(count==0){
                    startActivity(new Intent(ActivityWelcome.this, MainActivity.class));
                    handler.removeCallbacks(this);
                    finish();
                }
            }

        };
        btnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ActivityWelcome.this, ActivityLogin.class));
                handler.removeCallbacks(runnable);
                finish();
            }
        });
        handler.postDelayed(runnable,1000);
    }
}
