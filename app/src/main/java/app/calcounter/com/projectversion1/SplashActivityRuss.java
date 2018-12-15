package app.calcounter.com.projectversion1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivityRuss extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashruss);

        Thread splashThread = new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    sleep(3000);
                    Intent splashIntent = new Intent(getApplicationContext(),LoginActivityRuss.class);
                    startActivity(splashIntent);
                    finish();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }


            }
        };
        splashThread.start();
    }
}
