package com.edu0988.lesson1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button yesBtn;
    Button noBtn;
    TextView questionTextView;
    Question[] questions = {
            new Question(R.string.question1, true),
            new Question(R.string.question2, true),
            new Question(R.string.question3, false),
            new Question(R.string.question4, true),
            new Question(R.string.question5, false)
    };
    int questionIndex;
    Toast toast;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("questionIndex", questionIndex);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        yesBtn = findViewById(R.id.yesBtn);
        noBtn = findViewById(R.id.noBtn);
        questionTextView = findViewById(R.id.questionTextView);

        if (savedInstanceState != null)
            questionIndex = savedInstanceState.getInt("questionIndex");

        questionTextView.setText(questions[questionIndex].getQuestionText());

        yesBtn.setOnClickListener(onClickListener);
        noBtn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = view -> {
        if (questions[questionIndex].isAnswerTrue() == (view == yesBtn))
            toastShow("Правильно!");
        else
            toastShow("Не правильно!");
        questionIndex = (questionIndex + 1) % questions.length;
        questionTextView.setText(questions[questionIndex].getQuestionText());
    };

    private void toastShow(CharSequence message) {
        if(toast != null) toast.cancel();
        toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

}