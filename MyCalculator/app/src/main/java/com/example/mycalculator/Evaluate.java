package com.example.mycalculator;

import java.math.BigDecimal;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class Evaluate {

    public BigDecimal evaluate(String expression) {
        try {
            Expression exp = new ExpressionBuilder(expression).build();
            double result = exp.evaluate();
            return new BigDecimal(result);
        } catch (Exception e) {
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
