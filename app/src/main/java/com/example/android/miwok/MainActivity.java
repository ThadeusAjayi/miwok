/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.miwok;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.android.miwok.R.id.phrases;
import static com.example.android.miwok.R.id.start;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_main);

        numberClick();
        familyClick();
        phrasesClick();
        colorClick();

    }

    public void numberClick(){
        TextView numberTextView = (TextView) findViewById(R.id.numbers);
        numberTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent numbersIntent = new Intent(MainActivity.this, NumbersActivity.class);
                Toast.makeText(view.getContext(),"Welcome to numbers",Toast.LENGTH_SHORT).show();
                startActivity(numbersIntent);
            }
        });


    }

    public void familyClick(){

        TextView familyTextView = (TextView) findViewById(R.id.family);
        familyTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent familyIntent = new Intent(MainActivity.this, FamilyActivity.class);
                Toast.makeText(view.getContext(),"Welcome to family",Toast.LENGTH_SHORT).show();
                startActivity(familyIntent);
            }
        });

    }

    public void phrasesClick(){

        TextView phrasesTextView = (TextView) findViewById(phrases);
        phrasesTextView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                Intent phrasesIntent = new Intent(MainActivity.this, PhrasesActivity.class);
                Toast.makeText(view.getContext(),"Welcome to phrases",Toast.LENGTH_SHORT).show();
                startActivity(phrasesIntent);

            }
        });
    }



    public void colorClick(){
        TextView colorTextView = (TextView) findViewById(R.id.colors);
        colorTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent colorIntent = new Intent(MainActivity.this,ColorsActivity.class);
                Toast.makeText(view.getContext(),"Welcome to colors",Toast.LENGTH_SHORT).show();
                startActivity(colorIntent);
            }
        });
    }

}
