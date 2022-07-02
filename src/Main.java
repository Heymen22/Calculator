import java.util.Scanner;


public class Main {

    private static int arabicCounter = 0;

    public static void main(String[] args) throws Exception {
        String s = "LIX / X";
        System.out.println(calc(s));
    }

    public static String calc(String input) throws Exception {
        int result;
        Scanner scanner = new Scanner(input);

        int firstOperand = getOperand(scanner.next());
        String operator = scanner.next();
        int secondOperand = getOperand(scanner.next());

        if (scanner.hasNext()) {
            throw new Exception("формат математической операции не удовлетворяет заданию - " +
                    "два операнда и один оператор (+, -, /, *)");
        }


        result = switch (operator) {
            case "+" -> firstOperand + secondOperand;
            case "-" -> firstOperand - secondOperand;
            case "*" -> firstOperand * secondOperand;
            case "/" -> firstOperand / secondOperand;
            default -> throw new Exception("Неопределенный арифметический оператор");
        };
        if (arabicCounter == 0) {
            if (result < 1) {
                throw new Exception("Результат меньше еденицы и не может быть представлен римскими цифрами");
            }
            return Roman2ArabicConverter.toRoman(result);
        } else if (arabicCounter == 2) {
            return String.valueOf(result);
        } else {
            throw new Exception("Невозможно произвести вычисления над арабскими и римскими цифрами одновременно");
        }
    }

    private static int getOperand(String operandString) throws Exception {


        if (isArabicDigit(operandString)) {
            arabicCounter++;
            int operand = Integer.parseInt(operandString);

            if (operand < 1) {
                throw new Exception("Число меньше 1");
            }

            if (operand > 10) {
                throw new Exception("Число больше 10");
            }

            return operand;

        }

        if (isRomanDigit(operandString)) {
            int operand = Roman2ArabicConverter.toArabic(operandString.toUpperCase());
            if (operand < 1) {
                throw new Exception("Число меньше 1");
            }

            if (operand > 10) {
                throw new Exception("Число больше 10");
            }

            return operand;
        }

        throw new Exception("Операнда не является числом. Операнда: " + operandString);

    }

    private static boolean isArabicDigit(String string) {
        return string.matches("\\d+");
    }

    private static boolean isRomanDigit(String string) {
        return string.toUpperCase().matches("^(M{0,3})(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])$");
    }

}