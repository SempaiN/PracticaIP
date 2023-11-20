package iesserpis.practicaip;

import Domain.Poi;
import org.bson.Document;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Tools {
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);
    public static String[] welcomeText = {
            "Welcome to my application",
            "Greetings to my app",
            "Hello and welcome to my software",
            "Step into my program",
            "Enter my application",
            "Feel at home in my app",
            "Explore my software"
    };

    public static void welcome() {
        int num = random.nextInt(6);
        System.out.println(welcomeText[num]);
    }

    public static String getString() {
        return scanner.nextLine();
    }

    public static String readDate() {
        boolean isValid = false;
        String str = "";
        do {
            try {
                str = scanner.nextLine();
                if (str != null && !str.isEmpty()) {

                    LocalDate.parse(str, DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT));
                    isValid = true;
                } else
                    isValid = true;

            } catch (DateTimeParseException e) {
                System.out.println("!! Please, enter a valid date! [ex. 2005-12-31]");
            }
        } while (!isValid);

        if (str != null && !str.isEmpty())
            return str;
        else
            return "";

    }

    public static int getInt() {
        boolean isValid = false;
        int num = 0;

        do {
            try {
                num = scanner.nextInt();
                scanner.nextLine();
                if (num < 0) {
                    throw new Exception();
                }
                isValid = true;
            } catch (Exception ex) {
                System.out.println("Introduce un número entero válido y positivo.");
                scanner.nextLine();
            }
        } while (!isValid);

        return num;
    }


    public static boolean isValid(List<Poi> pois, int poid) {
        for (int i = 0; i < pois.size(); i++) {
            if (pois.get(i).getPoid() == poid) {
                return false;
            }
        }
        return true;
    }

    public static Double getDouble(Boolean allowBlank) {
        boolean isValid = false;
        String input;
        Double value = 0.0;
        do {
            try {
                input = scanner.nextLine();
                if (!(input.isEmpty()) || (!allowBlank)) {
                    value = Double.parseDouble(input);
                }
                isValid = true;
            } catch (Exception ex) {
                System.out.println("Please, enter a Double ex: 3.1414");
            }
        } while (!isValid);
        return value;
    }

}
