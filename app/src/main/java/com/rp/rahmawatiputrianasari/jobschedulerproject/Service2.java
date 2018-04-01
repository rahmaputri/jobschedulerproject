package com.rp.rahmawatiputrianasari.jobschedulerproject;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by rahmawatiputrianasari on 4/1/18.
 */



public class Service2 extends com.firebase.jobdispatcher.JobService {

    BackgroundTask backgroundTask;
    @Override
    public boolean onStartJob(final com.firebase.jobdispatcher.JobParameters job) {
        Toast.makeText(getApplicationContext(), "ON!!!", Toast.LENGTH_LONG).show();
        Log.e("lalala","START");
        backgroundTask = new BackgroundTask(){
            @Override
            protected void onPostExecute(String s) {
                Log.e("lalala","asyntask");
                Toast.makeText(getApplicationContext(),"SERVICE2"+s,Toast.LENGTH_LONG).show();
                jobFinished(job,false);
            }
        };
        backgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        return true;
    }

    public static class BackgroundTask extends AsyncTask<Void,Void,String>{


        @Override
        protected String doInBackground(Void... voids) {
            return "from asyntask";
        }
    }
}
