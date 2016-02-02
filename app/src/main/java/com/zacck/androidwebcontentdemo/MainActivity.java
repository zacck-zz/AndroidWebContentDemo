package com.zacck.androidwebcontentdemo;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
            Log.i(getPackageName()+" Url Contents", mDone);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    public class DownloadTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... urls) {
            String result = "";
            URL mUrl;
            HttpURLConnection mUrlConnection = null;

            //try
            try
            {
                mUrl = new URL(urls[0]);
                //opening the connection
                mUrlConnection = (HttpURLConnection)mUrl.openConnection();
                //load the data from the connection
                InputStream  in = mUrlConnection.getInputStream();
                //Read the stream
                InputStreamReader mReader = new InputStreamReader(in);
                int data = mReader.read();
                //read the data to the end
                while(data != -1)
                {
                    char currentChar = (char)data;
                    result += currentChar;

                    data = mReader.read();
                }
                return result;
            }
            catch(Exception e)
            {
                e.printStackTrace();
                return "failed";
            }

        }
    }
}
