package com.fahed.developer.hackspaceperu_reto1;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText editTextWork;
    private EditText editTextSleep;
    private EditText editTextPomodoro;
    private TextView textViewTime;
    private Button buttonStart;
    private ImageView imageViewBanner;

    private int timeWork;
    private int timeSleep;
    private int numberPomodoro;

    private Handler handler;
    private long timeRemeaning = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextWork = (EditText)findViewById(R.id.editTextWork);
        editTextSleep = (EditText)findViewById(R.id.editTextSleep);
        editTextPomodoro = (EditText)findViewById(R.id.editTextPomodoro);
        textViewTime = (TextView) findViewById(R.id.textViewTime);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        imageViewBanner = (ImageView) findViewById(R.id.imageViewBanner);

        handler =new Handler();
        buttonStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == buttonStart.getId()){
            if(!editTextWork.getText().toString().equals(getString(R.string.empty_string))  &&
                    !editTextSleep.getText().toString().equals(getString(R.string.empty_string)) &&
                    !editTextPomodoro.getText().toString().equals(getString(R.string.empty_string)) ){

                timeWork = Integer.parseInt(editTextWork.getText().toString());
                timeSleep = Integer.parseInt(editTextSleep.getText().toString());
                numberPomodoro = Integer.parseInt(editTextPomodoro.getText().toString());
                Working();
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                    int cx= buttonStart.getWidth()/2;
                    int cy= buttonStart.getHeight()/2;
                    float radius = buttonStart.getWidth();
                    Animator anim = ViewAnimationUtils.createCircularReveal(buttonStart, cx, cy, radius, 0);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            buttonStart.setVisibility(View.INVISIBLE);
                        }
                    });
                    anim.start();

                }else{
                    buttonStart.setVisibility(View.INVISIBLE);
                }
            }else{
                Toast.makeText(getBaseContext(),R.string.toast_message_error,Toast.LENGTH_LONG).show();
            }
        }
    }


    private void Working(){
        Toast.makeText(getBaseContext(),R.string.toast_message_work,Toast.LENGTH_LONG).show();
        imageViewBanner.setImageDrawable(ContextCompat.getDrawable(getBaseContext(),R.mipmap.work));
        timeRemeaning = convertToMiliseconds(timeWork);
        final Runnable runnableWork = new Runnable() {
            @Override
            public void run() {
                timeRemeaning = timeRemeaning - 1000;
                if(timeRemeaning > 0) {
                    updateTime(timeRemeaning);
                    handler.postDelayed(this, 1000);
                }else{
                    Sleeping();
                }

            }
        };

        handler.postDelayed(runnableWork,1000);
    }

    private void Sleeping(){
        Toast.makeText(getBaseContext(),R.string.toast_message_sleep,Toast.LENGTH_LONG).show();
        imageViewBanner.setImageDrawable(ContextCompat.getDrawable(getBaseContext(),R.mipmap.sleep));
        timeRemeaning = convertToMiliseconds(timeSleep);
        final Runnable runnableSleep = new Runnable() {
            @Override
            public void run() {
                timeRemeaning = timeRemeaning - 1000;
                if(timeRemeaning > 0) {
                    updateTime(timeRemeaning);
                    handler.postDelayed(this, 1000);
                }else{
                    Pomodoros();
                }

            }
        };

        handler.postDelayed(runnableSleep,1000);

    }

    private void Pomodoros(){
        numberPomodoro--;
        if(numberPomodoro > 0){
            Toast.makeText(getBaseContext(),numberPomodoro+" "+getString(R.string.toast_message_in_progress_pomodoro),
                    Toast.LENGTH_LONG).show();
            Working();
        }else{
            Toast.makeText(getBaseContext(),R.string.toast_message_pomodoro,Toast.LENGTH_LONG).show();
            imageViewBanner.setImageDrawable(ContextCompat.getDrawable(getBaseContext(),R.mipmap.tomato));
            textViewTime.setText(getString(R.string.empty_string));
            buttonStart.setVisibility(View.VISIBLE);
        }
    }

    private Long convertToMiliseconds(int minutes){
        return new Long(minutes * 60000);
    }

    private void updateTime(Long miliseconds){
        textViewTime.setText(getMinute(miliseconds)+" : "+getSecond(miliseconds));
    }

    private Long getMinute(Long miliseconds){
        return new Long  (TimeUnit.MILLISECONDS.toMinutes(miliseconds) -
                TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(miliseconds)));
    }

    private Long getSecond(Long miliseconds){
        return new Long (TimeUnit.MILLISECONDS.toSeconds(miliseconds) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(miliseconds)));
    }



}
