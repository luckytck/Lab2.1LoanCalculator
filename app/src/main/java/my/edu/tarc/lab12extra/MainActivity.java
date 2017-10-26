package my.edu.tarc.lab12extra;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
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

    public void calculateMonthlyPayment(View view){
        double Salary = Double.parseDouble(editTextSalary.getText().toString());
        double Rate = Double.parseDouble(editTextInterestRate.getText().toString());
        double RepayMonth = Double.parseDouble(editTextRepaymentMonth.getText().toString());
        double DownPayment = Double.parseDouble(editTextDownPayment.getText().toString());
        double VehiclePrice = Double.parseDouble(editTextVehiclePrice.getText().toString());
        double TotalInterest = (VehiclePrice - DownPayment) * (Rate/100) * (RepayMonth/12);
        double TotalLoan = (VehiclePrice - DownPayment) + TotalInterest;
        double MonthlyPayment = TotalLoan/RepayMonth;
        textViewMonthlyPayment.setText(String.format("Monthly Payment\nRM %.2f", MonthlyPayment));
        if (MonthlyPayment < Salary * 0.3){
            textViewResult.setText("Congratulation, you are eligible for the car loan.");
        }
        else{
            textViewResult.setText("Sorry, you are not eligible for the car loan.");
        }
    }
}
