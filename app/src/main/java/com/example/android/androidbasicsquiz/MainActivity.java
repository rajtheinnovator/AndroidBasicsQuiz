package com.example.android.androidbasicsquiz;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int score = 0;  //storing the quiz score in a private variable
    int emailScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button emailButton = (Button) findViewById(R.id.mailResult);
        emailButton.setVisibility(View.GONE);
        TextView resultTextview = (TextView) findViewById(R.id.resultTextview);
        resultTextview.setVisibility(View.GONE);
        //setting radio button checked by default for each question
        RadioButton implicit_radio_button = (RadioButton) findViewById(R.id.implicit_radio_button);
        implicit_radio_button.setChecked(true);
        RadioButton eclipse_radio_button = (RadioButton) findViewById(R.id.eclipse_radio_button);
        eclipse_radio_button.setChecked(true);
        RadioButton not_confident = (RadioButton) findViewById(R.id.not_confident);
        not_confident.setChecked(true);
        RadioButton yes_radio_button = (RadioButton) findViewById(R.id.yes_radio_button);
        yes_radio_button.setChecked(true);

    }



    public void submitResponse(View view) {
        //2nd question
        RadioGroup radioGroupQuestion2;
        RadioButton question2radioButton;
        //3rd question
        RadioGroup radioGroupQuestion3;
        RadioButton question3radioButton;
        //7th question
        RadioGroup radioGroupQuestion7;
        RadioButton question7radioButton;
        //8th question
        RadioGroup radioGroupQuestion8;
        RadioButton question8radioButton;
        String question1editText = findViewById(R.id.question1editText).toString();

        //setting result of question 1
        if (question1editText.equals("JAVA")) {
            score += 4;
        } else {
            score += 0;
        }

        //setting result of question 2
        //getting question 2 radio group id
        radioGroupQuestion2 = (RadioGroup) findViewById(R.id.question2radioGroup);
        // get selected radio button from radioGroup
        int selectedIdQuestion2 = radioGroupQuestion2.getCheckedRadioButtonId();
        // find the radio button by returned id
        question2radioButton = (RadioButton) findViewById(selectedIdQuestion2);

        if (question2radioButton.getText().toString().equals("Implicit")) {
            score += 4;
        } else if (question2radioButton.getText().toString().equals("Explicit")) {
            score += 0;
        }

        //setting result of question 3
        //getting question 3 radio group id
        radioGroupQuestion2 = (RadioGroup) findViewById(R.id.question2radioGroup);
        // get selected radio button from radioGroup
        int selectedIdQuestion3 = radioGroupQuestion2.getCheckedRadioButtonId();
        // find the radio button by returned id
        question3radioButton = (RadioButton) findViewById(selectedIdQuestion3);
        if (question3radioButton.getText().toString().equals("All of these")) {
            score += 4;
        } else {
            score += 0;
        }

        //setting result of question 4
        String question4editText = findViewById(R.id.question4editText).toString();

        //setting result of question 1
        if (question4editText.equals("INTERFACE")) {
            score += 4;
        } else {
            score += 0;
        }

        //setting result of question 5
        String question5editText = findViewById(R.id.question5editText).toString();

        //setting result of question 1
        if (question5editText.equals("ABSTRACT")) {
            score += 4;
        } else {
            score += 0;
        }

        //setting result of question 6
        CheckBox array_checkbox = (CheckBox) findViewById(R.id.array_checkbox);
        boolean hasArray = array_checkbox.isChecked();
        CheckBox listView_checkbox = (CheckBox) findViewById(R.id.listView_checkbox);
        boolean hasListView = listView_checkbox.isChecked();
        CheckBox loops_checkbox = (CheckBox) findViewById(R.id.loops_checkbox);
        boolean hasLoops = loops_checkbox.isChecked();
        CheckBox customClasses_checkbox = (CheckBox) findViewById(R.id.customClasses_checkbox);
        boolean hasCustomClasses = customClasses_checkbox.isChecked();
        if (hasArray) {
            score += 1;
        }
        if (hasListView) {
            score += 1;
        }
        if (hasLoops) {
            score += 1;
        }
        if (hasCustomClasses) {
            score += 1;
        }

        // setting result of question 7
        //getting question 7 radio group id
        radioGroupQuestion7 = (RadioGroup) findViewById(R.id.question7radioGroup);
        // get selected radio button from radioGroup
        int selectedIdQuestion7 = radioGroupQuestion7.getCheckedRadioButtonId();
        // find the radio button by returned id
        question7radioButton = (RadioButton) findViewById(selectedIdQuestion7);
        if (question7radioButton.getText().toString().equals("Not Confident")) {
            score += 0;
        } else if (question7radioButton.getText().toString().equals("Somewhat Confident")) {
            score += 2;
        } else {
            score += 4;
        }

        //setting result of question 8
        //getting question 8 radio group id
        radioGroupQuestion8 = (RadioGroup) findViewById(R.id.question8radioGroup);
        // get selected radio button from radioGroup
        int selectedIdQuestion8 = radioGroupQuestion8.getCheckedRadioButtonId();
        // find the radio button by returned id
        question8radioButton = (RadioButton) findViewById(selectedIdQuestion8);
        if (question8radioButton.getText().toString().equals("Yes")) {
            score += 4;
        } else {
            score += 0;
        }

        TextView resultTextview = (TextView) findViewById(R.id.resultTextview);
        resultTextview.setVisibility(View.VISIBLE);
        resultTextview.setText(message(score));
        Button emailButton = (Button) findViewById(R.id.mailResult);
        emailButton.setVisibility(View.VISIBLE);
        emailScore = score;
        score = 0;
    }

    private String message(int score) {
        //setting the message
        String finalMessage = "";
        if (score >= 25) {
            finalMessage += "Your score is " + score + " and you are doing great, you are all good to go ahead";
        } else if (score > 20 && score < 25) {
            finalMessage += "Your score is " + score + " and you need some more practice, but don't worry you can learn ahead but just need to stick to basics.";
        } else {
            finalMessage += "Your score is " + score + " you are having a tough ride! Work hard you will do good then";
        }
        return finalMessage;
    }
    public void mailResult(View view) {
        // Setting eMail intent
        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"));
        email.putExtra(Intent.EXTRA_TEXT, message(emailScore));
        email.putExtra(Intent.EXTRA_SUBJECT, "Result");
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"raj@ymail.com"});
        if (email.resolveActivity(getPackageManager()) != null) {
            startActivity(email);
        }
    }
}