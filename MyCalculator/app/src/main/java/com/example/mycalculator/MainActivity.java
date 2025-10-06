package com.example.mycalculator;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btn_clear, btn_plus, btn_equal, btn_minus, btn_mult, btn_div, btn_dot;
    TextView text_display;

    // This is to evaluate the math expression

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        btn3 = (Button) findViewById(R.id.btn3);
        btn4 = (Button) findViewById(R.id.button5);
        btn5 = (Button) findViewById(R.id.button6);
        btn6 = (Button) findViewById(R.id.button7);
        btn7 = (Button) findViewById(R.id.button9);
        btn8 = (Button) findViewById(R.id.button10);
        btn9 = (Button) findViewById(R.id.button11);
        btn0 = (Button) findViewById(R.id.btn0);
        btn_plus = (Button) findViewById(R.id.btn_plus);
        btn_equal = (Button) findViewById(R.id.btn_equal);
        btn_clear = (Button) findViewById(R.id.btn_clear);
        btn_minus = (Button) findViewById(R.id.button8);
        btn_mult = (Button) findViewById(R.id.button12);
        btn_div = (Button) findViewById(R.id.button16);
        btn_dot = (Button) findViewById(R.id.btn_dot);
        text_display = (TextView) findViewById(R.id.textview_input_display);

        setClickListeners();
    }

    private void setClickListeners() {
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        btn5.setOnClickListener(this);
        btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);
        btn8.setOnClickListener(this);
        btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);
        btn_plus.setOnClickListener(this);
        btn_equal.setOnClickListener(this);
        btn_clear.setOnClickListener(this);
        btn_minus.setOnClickListener(this);
        btn_mult.setOnClickListener(this);
        btn_div.setOnClickListener(this);
        btn_dot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                addNumber("1");
                break;
            case R.id.btn2:
                addNumber("2");
                break;
            case R.id.btn3:
                addNumber("3");
                break;
            case R.id.button5:
                addNumber("4");
                break;
            case R.id.button6:
                addNumber("5");
                break;
            case R.id.button7:
                addNumber("6");
                break;
            case R.id.button9:
                addNumber("7");
                break;
            case R.id.button10:
                addNumber("8");
                break;
            case R.id.button11:
                addNumber("9");
                break;
            case R.id.btn0:
                addNumber("0");
                break;
            case R.id.btn_plus:
                addNumber("+");
                break;
            case R.id.button8:
                addNumber("-");
                break;
            case R.id.button12:
                addNumber("x");
                break;
            case R.id.button16:
                addNumber("/");
                break;
            case R.id.btn_dot:
                addNumber(".");
                break;
            case R.id.btn_equal:
                String result = null;
                try {
                    result = evaluate(text_display.getText().toString());
                    text_display.setText(result);
                } catch (Exception e) {
                    text_display.setText("Error");
                }
                break;
            case R.id.btn_clear:
                clear_display();
                break;
        }
    }

    private String evaluate(String expression) throws Exception {
        expression = expression.replace("x", "*");
        Expression e = new ExpressionBuilder(expression).build();
        double result = e.evaluate();
        BigDecimal decimal = new BigDecimal(result);
        return decimal.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
    }

    private void addNumber(String number) {
        text_display.setText(text_display.getText() + number);
    }

    private void clear_display() {
        text_display.setText("");
    }
}