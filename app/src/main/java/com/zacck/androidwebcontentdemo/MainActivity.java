package com.zacck.androidwebcontentdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //call the thread
        DownloadTask mTask = new DownloadTask();
        try
        {
            String mDone = mTask.execute("http://launchdesign.co.za").get();
            Log.i(getPackageName()+" Done", mDone);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public class DownloadTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {
            Log.i(getPackageName()+" Url", params[0]);
            return "Done";
        }
    }
}
