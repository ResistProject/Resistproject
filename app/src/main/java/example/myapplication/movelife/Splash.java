package example.myapplication.movelife;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import example.myapplication.movelife.R;

/**
 * Created by Thomas on 14-5-2014.
 */



public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread timer = new Thread() {
            public void run(){
                try{
                    sleep(5000);

            }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent openStartingPoint = new Intent("example.myapplication.movelife.MENU");
                    startActivity(openStartingPoint);
                }
                }

        };
        timer.start();
    }
}

