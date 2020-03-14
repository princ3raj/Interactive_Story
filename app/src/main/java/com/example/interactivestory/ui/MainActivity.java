package com.example.interactivestory.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.interactivestory.R;

public class MainActivity extends AppCompatActivity {
    private EditText nameField;
    private Button startButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nameField=(EditText) findViewById(R.id.editText2);
        startButton=(Button)findViewById(R.id.viewButton);

startButton.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        String name= nameField.getText().toString();

     startStory(name);

    }
});


}

    private void startStory(String name) {
        Intent intent =new Intent(this,storyActivity.class);
        Resources resources=getResources();
        String key=resources.getString(R.string.key_name);
        intent.putExtra(key, name);
        startActivity(intent);
    }
    }
