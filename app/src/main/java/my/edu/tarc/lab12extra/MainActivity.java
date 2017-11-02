package my.edu.tarc.lab12extra;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    public static final String LOAN_PAYMENT = "payment";
    public static final String LOAN_STATUS = "status";
    private EditText editTextSalary, editTextVehiclePrice, editTextDownPayment, editTextInterestRate, editTextRepaymentMonth;
    private TextView textViewMonthlyPayment, textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextDownPayment = (EditText)findViewById(R.id.editTextDownPayment);
        editTextInterestRate = (EditText)findViewById(R.id.editTextInterestRate);
        editTextRepaymentMonth = (EditText)findViewById(R.id.editTextRepaymentMonth);
        editTextSalary = (EditText)findViewById(R.id.editTextSalary);
        editTextVehiclePrice = (EditText)findViewById(R.id.editTextVehiclePrice);
        textViewMonthlyPayment = (TextView)findViewById(R.id.textViewMonthlyPayment);
        textViewResult = (TextView)findViewById(R.id.textViewResult);
    }

    public void calculateMonthlyPayment(View view) {
        //TODO: Calculate repayment amount and determine the status of loan application
        double Salary = Double.parseDouble(editTextSalary.getText().toString());
        double Rate = Double.parseDouble(editTextInterestRate.getText().toString());
        double RepayMonth = Double.parseDouble(editTextRepaymentMonth.getText().toString());
        double DownPayment = Double.parseDouble(editTextDownPayment.getText().toString());
        double VehiclePrice = Double.parseDouble(editTextVehiclePrice.getText().toString());
        double TotalInterest = (VehiclePrice - DownPayment) * (Rate / 100) * (RepayMonth / 12);
        double TotalLoan = (VehiclePrice - DownPayment) + TotalInterest;
        double MonthlyPayment = TotalLoan / RepayMonth;
        textViewMonthlyPayment.setText(String.format("Monthly Payment\nRM %.2f", MonthlyPayment));
        String status;

        if (MonthlyPayment < Salary * 0.3) {
            status = "Approve";
            textViewResult.setText("Congratulation, you are eligible for the car loan.");
            textViewResult.setTextColor(Color.parseColor("#1B5E20"));
        } else {
            status = "Decline";
            textViewResult.setText("Sorry, you are not eligible for the car loan.");
            textViewResult.setTextColor(Color.parseColor("#F44336"));
        }

        //Define an Intent Object
        //This is an Explicit Intent
        Intent intent = new Intent(this, ResultActivity.class);

        intent.putExtra(LOAN_PAYMENT, MonthlyPayment);
        intent.putExtra(LOAN_STATUS, status);
        startActivity(intent);
    }

    public void resetUI(View view){
        editTextDownPayment.setText("");
        editTextInterestRate.setText("");
        editTextRepaymentMonth.setText("");
        editTextSalary.setText("");
        editTextVehiclePrice.setText("");;
        textViewMonthlyPayment.setText("Monthly Payment:");
        textViewResult.setText("");
    }
}
