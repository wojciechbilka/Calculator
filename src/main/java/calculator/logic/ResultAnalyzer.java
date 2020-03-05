package calculator.logic;

import java.math.BigDecimal;
import java.util.*;

public class ResultAnalyzer {
    String result;
    List<MathematicalOperation> operations;
    List<BigDecimal> numbers;

    public ResultAnalyzer(String result) {
        operations = new ArrayList<>();
        numbers = new ArrayList<>();
        this.result = result;
    }

    public ResultAnalyzer() {
        operations = new ArrayList<>();
        numbers = new ArrayList<>();
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void parse() {
        String[] numbersArray = result.split("[+\\-*/]");
        String operations = result.replaceAll("[0-9,]", "");
        String[] operationsArray = result.split("([0-9]+,[0-9]+)|([0-9]+)");


        System.out.println(Arrays.toString(numbersArray));
        System.out.println(Arrays.toString(operationsArray));
        System.out.println(operations);


    }
    public static void calculate(Scanner scanner) {
        try {
            System.out.println("Podałeś pierwszą liczbę");
            BigDecimal val1 = scanner.nextBigDecimal();
            System.out.println("Podaj drugą liczbę");
            BigDecimal val2 = scanner.nextBigDecimal();
            System.out.println("Podaj znak operacji (+, -, *, /)");
            String operation = scanner.next();
            switch (operation) {
                case "+":
                    System.out.println(MathematicalOperation.ADD.calculate(val1, val2));
                    break;
                case "-":
                    System.out.println(MathematicalOperation.SUBTRACT.calculate(val1, val2));
                    break;
                case "*":
                    System.out.println(MathematicalOperation.MULTIPLY.calculate(val1, val2));
                    break;
                case "/":
                    System.out.println(MathematicalOperation.DIVIDE.calculate(val1, val2));
                    break;
                default:
                    System.out.println("Niepoprawny znak operacji!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Wyjątek " + e);
        }
    }

}
