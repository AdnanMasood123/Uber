package com.example.uber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashscreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        getSupportActionBar().hide();
        Thread th=new Thread()
        {
            @Override
            public void run()
            {
                super.run();

                try
                {
                     sleep(2000);
                }
                catch (Exception e)
                {
                   e.printStackTrace();
                }
                finally
                {
                    Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }




            }
        };

        th.start();

    }
}