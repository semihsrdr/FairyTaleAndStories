package com.semihserdarsahin.fairytaleandstories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.speech.tts.TextToSpeech;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import com.semihserdarsahin.fairytaleandstories.databinding.ActivityDetailsBinding;

import java.util.Locale;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    private TextToSpeech textToSpeech;
    Story selectedStory;
    int currentIndex=0;
    private String[] words;
    private MediaPlayer mediaPlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityDetailsBinding.inflate(getLayoutInflater());
        View view=binding.getRoot();
        setContentView(view);


        Intent intent=getIntent();
        selectedStory=(Story) intent.getSerializableExtra("selectedStory");

        binding.textViewHeader.setText(selectedStory.header);
        binding.textViewStory.setText(selectedStory.story);
        binding.imageView6.setImageResource(selectedStory.image);

        mediaPlayer=MediaPlayer.create(this,R.raw.pussinboots);



        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                if (i==TextToSpeech.SUCCESS){
                    int result=textToSpeech.setLanguage(Locale.US);

                    if (result==textToSpeech.LANG_MISSING_DATA||result==textToSpeech.LANG_NOT_SUPPORTED){
                        Toast.makeText(DetailsActivity.this, "Can't Play", Toast.LENGTH_SHORT).show();
                    }
                    else{
                    }
                }
                else{
                    Toast.makeText(DetailsActivity.this, "Can't Play", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void start(View view){
        System.out.println("Started");
        playWholeStory();
    }
    public void stop(View view){
        textToSpeech.stop();
        mediaPlayer.stop();
        System.out.println("Stopped");
    }
    public void playWholeStory(){
        if (selectedStory.header.matches("Puss in Boots")){
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start(); // MP3 çalmaya başla
            }
        }
        else{
            textToSpeech.speak(selectedStory.header,TextToSpeech.QUEUE_FLUSH,null);
            Handler handler = new Handler();
            int gecikmeSuresi = 3000; // 3 saniye (milisaniye cinsinden)

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    textToSpeech.speak(selectedStory.story,TextToSpeech.QUEUE_FLUSH,null);
                }
            }, gecikmeSuresi);
        }



    }
    @Override
    protected void onDestroy() {
        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release(); // MediaPlayer'ı serbest bırak
        }
    }
}