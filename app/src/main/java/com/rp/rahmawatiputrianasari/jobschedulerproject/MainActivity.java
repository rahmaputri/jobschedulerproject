package com.rp.rahmawatiputrianasari.jobschedulerproject;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.RetryStrategy;
import com.firebase.jobdispatcher.Trigger;

public class MainActivity extends AppCompatActivity {

    private static final String JOB_TAG = "JOB_TAG";
    private static final String JOB_TAG2 = "JOB_TAG2";
    FirebaseJobDispatcher firebaseJobDispatcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseJobDispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(this));
    }

    public void startJob(View view) {
        Job job = firebaseJobDispatcher.newJobBuilder()
                .setService(Service.class)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTag(JOB_TAG)
                .setTrigger(Trigger.executionWindow(10, 10))
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();
        firebaseJobDispatcher.mustSchedule(job);

        Job job2 = firebaseJobDispatcher.newJobBuilder()
                .setService(Service2.class)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTag(JOB_TAG2)
                .setTrigger(Trigger.executionWindow(15, 15))
                .setRetryStrategy(RetryStrategy.DEFAULT_EXPONENTIAL)
                .setReplaceCurrent(false)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .build();
        firebaseJobDispatcher.mustSchedule(job2);
        Toast.makeText(getApplicationContext(), "job scheduled", Toast.LENGTH_LONG).show();
    }

    public void endJob(View view) {
        firebaseJobDispatcher.cancel(JOB_TAG);
        firebaseJobDispatcher.cancel(JOB_TAG2);
        Toast.makeText(getApplicationContext(), "job canceled", Toast.LENGTH_LONG).show();

    }
}
