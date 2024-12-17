package com.example.listcalcproj;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity
{
    EditText etNum1, etNum2;
    TextView tvResult;
    double num1, num2, result;
    String temp1, temp2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->
        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        tvResult = findViewById(R.id.tvResult);

    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);

        return true;
    }

    public boolean onOptionsItemSelected (@NonNull MenuItem item)
    {
        String action = item.getTitle().toString();

        temp1 = etNum1.getText().toString();
        temp2 = etNum2.getText().toString();

        if ( (isValidNum(temp1)) && (isValidNum(temp2)) )
        {
            num1 = Double.parseDouble(temp1);
            num2 = Double.parseDouble(temp2);

            if (action.equals("+"))
            {
                result = num1 + num2;
                tvResult.setText( "result: "+ (differentView(result)));
            }

            else if (action.equals("-"))
            {
                result = num1 - num2;
                tvResult.setText( "result: "+ (differentView(result)));
            }

            else if (action.equals("*"))
            {
                result = num1 * num2;
                tvResult.setText( "result: "+ (differentView(result)));
            }

            else if (action.equals("/"))
            {
                if (num2 != 0)
                {
                    result = num1 / num2;
                    tvResult.setText( "result: "+ (differentView(result)));
                }

                else
                {
                    wrongInput();
                }
            }

            else if (action.equals("C"))
            {
                etNum1.setHint("enter a number:");
                etNum1.setText("");

                etNum2.setHint("enter a number:");
                etNum2.setText("");

                tvResult.setText("");
            }
        }

        else
        {
            wrongInput();
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean isValidNum(String input)
    {
        return !((input.equals("")) ||
                (input.equals("-")) ||
                (input.equals(".")) ||
                (input.equals("+")) ||
                (input.equals("+.")) ||
                (input.equals("-.")));
    }

    public void wrongInput()
    {
        Toast.makeText(this, "illegal action!", Toast.LENGTH_SHORT).show();
    }

    public static String differentView(double term)
    {
        if (term % 1 == 0 && Math.abs(term) < 10000)
        {
            return String.valueOf((int) term);
        }

        if (Math.abs(term) < 10000 && Math.abs(term) >= 0.001)
        {
            return String.format("%.3f", term);
        }

        int exponent = 0;
        double coefficient = term;

        if (Math.abs(term) >= 1)
        {
            while (Math.abs(coefficient) >= 10)
            {
                coefficient /= 10;
                exponent++;
            }
        }
        else
        {
            while (Math.abs(coefficient) < 1 && coefficient != 0)
            {
                coefficient *= 10;
                exponent--;
            }
        }

        return String.format("%.3f * 10^%d", coefficient, exponent);
    }
}