package com.example.interactivestory.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;

import com.example.interactivestory.R;
import com.example.interactivestory.model.Page;
import com.example.interactivestory.model.Story;

public class storyActivity extends AppCompatActivity {

    public static final String TAG =storyActivity.class.getSimpleName();

    private String name;

    private Story story;
    private ImageView storyImageView;
    private TextView storyTextView;
    private Button choice1Button;
    private Button choice2Button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);

        storyImageView=(ImageView)findViewById(R.id.imageView2);
        storyTextView = (TextView)findViewById(R.id.textView);
        choice1Button = (Button)findViewById(R.id.button);
        choice2Button = (Button)findViewById(R.id.button2);

        Intent intent=getIntent();

        name=intent.getStringExtra(getString(R.string.key_name));
        if(name==null || name.isEmpty())
            name= "PRINCE";
        Log.d(TAG, name);

        story =new Story();
        loadPage(0);

    }

    private void loadPage(int pageNumber) {
        final Page page=story.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        storyImageView.setImageDrawable(image);

      String pageText= getString(page.getTextId());
      pageText= String.format(pageText,name);
      storyTextView.setText(pageText);

      if(page.isFinalPage()){
          choice1Button.setVisibility(View.INVISIBLE);
          choice2Button.setText(R.string.play_again_text_button);


      }
      else {

          loadButtons(page);
      }

    }

    private void loadButtons(final Page page) {
        choice1Button.setText(page.getChoice1().getTextId());
        choice1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);
            }
        });

        choice2Button.setText(page.getChoice2().getTextId());
        choice2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int nextPage = page.getChoice2().getNextPage();
                loadPage(nextPage);
            }
        });
    }
}
