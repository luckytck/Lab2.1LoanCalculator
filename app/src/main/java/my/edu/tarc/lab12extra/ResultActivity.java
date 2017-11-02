package my.edu.tarc.lab12extra;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    TextView textViewMonthlyPayment, textViewStatus;
    ImageView imageViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewMonthlyPayment = (TextView)findViewById(R.id.textViewMonthlyPayment);
        textViewStatus = (TextView)findViewById(R.id.textViewStatus);
        imageViewResult = (ImageView)findViewById(R.id.imageViewResult);

        //To get the intent
        Intent intent = getIntent();//Asking "who called me?"
        double payment = intent.getDoubleExtra(MainActivity.LOAN_PAYMENT, 0);
        String status = intent.getStringExtra(MainActivity.LOAN_STATUS);

        textViewMonthlyPayment.setText(String.format("Monthly Payment:\nRM%.2f", payment));
        if (status.equals("Approve")){
            textViewStatus.setText("Status:\n" + "Congratulation, you are eligible for the car loan.");
            textViewStatus.setTextColor(Color.parseColor("#1B5E20"));
            imageViewResult.setImageResource(R.drawable.up);
        }
        else{
            textViewStatus.setText("Status:\n" + "Sorry, you are not eligible for the car loan.");
            textViewStatus.setTextColor(Color.parseColor("#F44336"));
            imageViewResult.setImageResource(R.drawable.down);
        }

    }

    public void closeActivity(View view){
        //Terminate the activity
        finish();
    }

}
