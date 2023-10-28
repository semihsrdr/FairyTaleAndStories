package com.semihserdarsahin.fairytaleandstories;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class FeedBackActivity extends AppCompatActivity {
    EditText contextText;
    EditText number;
    EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        contextText=findViewById(R.id.editTextNameMail);
        number=findViewById(R.id.editTextPhone);
        name=findViewById(R.id.editTextName);
    }
    public void send(View view){
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("message/rfc822");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"smhserdarshn52@gmail.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Story App Feedback");
        emailIntent.putExtra(Intent.EXTRA_TEXT, contextText.getText().toString()+"\n Name: "+name.getText().toString()+
                "\n Phone Number : "+number.getText().toString()); //

        try {
            startActivity(Intent.createChooser(emailIntent, "E-posta Gönder"));
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}