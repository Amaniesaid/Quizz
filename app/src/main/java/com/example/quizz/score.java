package com.example.quizz;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class score extends AppCompatActivity {
    public TextView scoreText;
    public int scoreReceived;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent intent = getIntent();

        scoreReceived = intent.getIntExtra("SCORE_KEY", 0);


        this.scoreText = (TextView) findViewById(R.id.score);
        scoreText.setText(String.format("%d", scoreReceived));

        Button replayButton = (Button) findViewById(R.id.replay);
        replayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scoreReceived = 0;
                Intent mainActivityIntent = new Intent(score.this, MainActivity.class);
                mainActivityIntent.putExtra("SCORE_KEY", scoreReceived);
                startActivity(mainActivityIntent);
                finish();
            }
        });
    }
}
