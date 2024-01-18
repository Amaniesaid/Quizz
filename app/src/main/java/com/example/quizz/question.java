package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class question extends AppCompatActivity {

    private String[] questionList = {
            "Quel numéro est indissociable du footballeur Cristiano Ronaldo ?",
            "D’où est originaire le footballeur portugais Cristiano Ronaldo ?",
            "Quel pays Cristiano Ronaldo n’a-t-il jamais été membre d’un club ?",
            "Dans quel club de foot Cristiano Ronaldo est-il resté le plus longtemps en continu ?",
            "Combien de Ligue des Champions Cristiano Ronaldo a-t-il remporté durant sa carrière madrilène de 2009 à 2018 ?"
    };

    private String[] chooseList = {
            "3", "7", "14", "19",
            "porto", "lisbonne", "madère", "fato",
            "Italie", "Allemagne", "Espagne", "Angleterre",
            "Juventus", "United", "Barcelone", "Madrid",
            "1", "2", "4", "5"
    };

    private String[] correctList = {"7", "madère", "Allemagne", "Madrid", "4"};

    private TextView questionTextView;
    private RadioButton answer1RadioButton;
    private RadioButton answer2RadioButton;
    private RadioButton answer3RadioButton;
    private RadioButton answer4RadioButton;
    private Button nextButton;
    private RadioGroup radioGroup;

    private int currentQuestionIndex = 0;
    private int currentAnswerIndex = 0;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        questionTextView = findViewById(R.id.question);
        answer1RadioButton = findViewById(R.id.answer1);
        answer2RadioButton = findViewById(R.id.answer2);
        answer3RadioButton = findViewById(R.id.answer3);
        answer4RadioButton = findViewById(R.id.answer4);
        nextButton = findViewById(R.id.next);
        radioGroup = findViewById(R.id.radioGroup);

        displayQuestionAndAnswers();

        nextButton.setOnClickListener(v -> {
            int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (checkedRadioButtonId != -1) {
                RadioButton radioButton = findViewById(checkedRadioButtonId);
                String response = radioButton.getText().toString();
                if (response.equals(correctList[currentQuestionIndex])) {
                    score++;
                }
                currentQuestionIndex++;
            }
            if (currentQuestionIndex <= questionList.length - 1) {
                currentAnswerIndex = currentQuestionIndex * 4;
                displayQuestionAndAnswers();
                radioGroup.clearCheck();
            } else {
                nextButton.setText("Résultat");
                Intent intent = new Intent(question.this, score.class);
                intent.putExtra("SCORE_KEY", score);
                startActivity(intent);
            }
        });
    }

    private void displayQuestionAndAnswers() {
        questionTextView.setText(questionList[currentQuestionIndex]);
        answer1RadioButton.setText(chooseList[currentAnswerIndex]);
        answer2RadioButton.setText(chooseList[currentAnswerIndex + 1]);
        answer3RadioButton.setText(chooseList[currentAnswerIndex + 2]);
        answer4RadioButton.setText(chooseList[currentAnswerIndex + 3]);
    }
}
