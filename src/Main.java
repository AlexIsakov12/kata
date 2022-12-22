import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

     static class romanNumbers {
        static String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
        };

     }
     static boolean isRoman (String digit) {
         boolean flag = false;
         for (int i = 0; i < romanNumbers.roman.length; i++) {
             if (romanNumbers.roman[i] == digit) {
                 flag = true;
                 break;
             }
         }
         return flag;
     }
     static boolean isArabic(String digit) {
         try {
             switch (digit) {
                 case "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" -> {
                     return true;
                 }
                 default -> {
                     return false;
                 }
             }
         } catch (InputMismatchException e) {
             System.out.println("Exception" + e);
         }
         return false;
     }
     static int romanToArabic(String r) {
         try {
             switch (r) {
                 case "I":
                     return 1;
                 case "II":
                     return 2;
                 case "III":
                     return 3;
                 case "IV":
                     return 4;
                 case "V":
                     return 5;
                 case "VI":
                     return 6;
                 case "VII":
                     return 7;
                 case "VIII":
                     return 8;
                 case "IX":
                     return 9;
                 case "X":
                     return 10;
             }
         } catch (InputMismatchException e) {
             System.out.println("Неверный формат данных!");
         }

         return -1;
    }

    public static String calc(String input) {
        int result = 0;

        String[] splitInput = input.split(" ");

        if (splitInput.length != 3) {
            System.out.println("There should be 3 parameters: digit + operator + digit!");
            throw new InputMismatchException();
        }
        String firstDigit = splitInput[0];
        String operation = splitInput[1];
        String secondDigit = splitInput[2];

        if (isArabic(firstDigit) && isArabic(secondDigit)) {
            switch (operation) {
                case "+" -> result = Integer.parseInt(firstDigit) + Integer.parseInt(secondDigit);
                case "-" -> result = Integer.parseInt(firstDigit) - Integer.parseInt(secondDigit);
                case "/" -> {
                    try {
                        result = Integer.parseInt(firstDigit) / Integer.parseInt(secondDigit);
                    } catch (ArithmeticException e) {
                        System.out.println("Exception " + e);
                        System.out.println("Parameter can't be zero!");
                    }
                }
                case "*" -> result = Integer.parseInt(firstDigit) * Integer.parseInt(secondDigit);
                default -> {
                    System.out.println("Incorrect operation sign!");
                    throw new InputMismatchException();
                }
            }
            return Integer.toString(result);
        } else if (romanToArabic(firstDigit) != -1 && romanToArabic(secondDigit) != -1) {
            switch (operation) {
                case "+" -> result = romanToArabic(firstDigit) + romanToArabic(secondDigit);
                case "-" -> {
                    if (romanToArabic(firstDigit) - romanToArabic(secondDigit) > 1) {
                        result = romanToArabic(firstDigit) - romanToArabic(secondDigit);
                    } else {
                        System.out.println("Numbers less than One (I) doesn't exist in Roman numeral system");
                        throw new ArithmeticException();
                    }
                }
                case "/" -> {
                    try {
                        result = romanToArabic(firstDigit) / romanToArabic(secondDigit);
                    } catch (ArithmeticException e) {
                        System.out.println("Exception " + e);
                        System.out.println("Parameter can't be zero!");
                    }
                }
                case "*" -> result = romanToArabic(firstDigit) * romanToArabic(secondDigit);
                default -> {
                    System.out.println("Incorrect operation sign!");
                    throw new InputMismatchException();
                }
            }

                return romanNumbers.roman[result];
            } else if ((isArabic(firstDigit) && isArabic(secondDigit) == false) ||
                (isArabic(secondDigit) && isArabic(firstDigit) == false)){
                System.out.println("You must input either both arabic numbers or both roman!");
                throw new InputMismatchException();
            } else if (isRoman(firstDigit) == false && (isRoman(secondDigit) == false)) {
                System.out.println("Incorrect input!");
                throw new InputMismatchException();
        }
            else {
                System.out.println("Numbers must be in range from 1 to 10 inclusive!");
                throw new InputMismatchException();
        }
        }



    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Welcome to the Calculator app!");
        System.out.println();
        System.out.print("Input two arabic/roman digits in the form of 'digit' '+-*/' 'digit': ");
        String input = console.nextLine();


        System.out.println(calc(input));
    }
}

