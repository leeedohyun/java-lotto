package calculator;

public class CalculatorMain {
    public static void main(String[] args) {
        new CalculatorController(new InputView(), new OutputView()).calculate();
    }
}
