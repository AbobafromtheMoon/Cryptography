import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main
{
    static String text = "";
    static String text2 = "";
    static String text3 = "";
    static String text4 = "";

    public static void main(String[] args) throws UnsupportedEncodingException
    {
        String s;
        boolean exit = false;
        int check;

        System.out.print("Введите номер задания: \n" + "1.DES \n" + "2.ГОСТ 28147-89 \n"
                + "3.RSA \n" + "4.Функция хэширования \n" + "5.ЭЦП \n" + "Выбор: ");
        Scanner scan = new Scanner(System.in);
        while (!exit)
        {
            if (exit) break;
            s = scan.nextLine();
            switch (s)
            {
                case "1":
                case "1.":
                case "1.DES":
                {
                    System.out.print("Введите исходный текст: ");
                    text = scan.nextLine();
                    System.out.print("Введите ключ: ");
                    text2 = scan.nextLine();
                    if (text.length() != 8 || text2.length() != 8) System.out.println("Введите все поля корректно!");
                    else
                    {
                        DES output = new DES();
                        System.out.println("Ответ: " + output.Encrypt());
                    }
                    System.out.println("Хотите продолжить? Введите 1, если да, введите 0, если нет.");
                    check = scan.nextInt();
                    exit = check != 1;
                    break;
                }
                case "2":
                case "2.":
                case "2.ГОСТ 28147-89":
                {
                    System.out.print("Введите исходный текст: ");
                    text = scan.nextLine();
                    System.out.print("Введите ключ: ");
                    text2 = scan.nextLine();
                    if (text.length() != 8 || text2.length() != 4) System.out.println("Введите все поля корректно!");
                    else
                    {
                        GOST output = new GOST();
                        System.out.println("Ответ: R0: " + output.Encrypt());
                    }
                    System.out.println("Хотите продолжить? Введите 1, если да, введите 0, если нет.");
                    check = scan.nextInt();
                    exit = check != 1;
                    break;
                }
                case "3":
                case "3.":
                case "3.RSA":
                {
                    System.out.print("Введите исходный текст: ");
                    text = scan.nextLine();
                    System.out.print("Введите p: ");
                    text2 = scan.nextLine();
                    System.out.print("Введите q: ");
                    text3 = scan.nextLine();
                    System.out.print("Введите секретный ключ d: ");
                    text4 = scan.nextLine();
                    if (text.length() != 3 || text2.length() == 0 || text3.length() == 0 || text4.length() == 0
                            || convertToInt(text2) == -1 || convertToInt(text3) == -1 || convertToInt(text4) == -1)
                        System.out.print("Заполните все поля корректно!");
                    else
                    {
                        RSA output = new RSA();
                        RSA n = new RSA();
                        RSA e = new RSA();
                        RSA d = new RSA();

                        System.out.println("Открытый ключ: (" + e.finding_e() + ", " + n.get_n() + ") " + "Закрытый ключ: (" + d.get_d() + ", " + n.get_n() + ") " +
                                "Ответ: " + output.Encrypt());
                    }
                    System.out.println("Хотите продолжить? Введите 1, если да, введите 0, если нет.");
                    check = scan.nextInt();
                    exit = check != 1;
                    break;
                }
                case "4":
                case "4.":
                case "4.Функция хэширования":
                {
                    System.out.print("Введите исходный текст: ");
                    text = scan.nextLine();
                    System.out.print("Введите p: ");
                    text2 = scan.nextLine();
                    System.out.print("Введите q: ");
                    text3 = scan.nextLine();
                    System.out.print("Введите H0: ");
                    text4 = scan.nextLine();
                    if (text2.length() == 0 || text3.length() == 0 || text4.length() == 0
                            || convertToInt(text2) == -1 || convertToInt(text3) == -1 || convertToInt(text4) == -1)
                        System.out.println("Заполните все поля корректно!");
                    else
                    {
                        Hash output = new Hash();
                        System.out.println("Ответ: " + output.Encrypt());
                    }
                    System.out.println("Хотите продолжить? Введите 1, если да, введите 0, если нет.");
                    check = scan.nextInt();
                    exit = check != 1;
                    break;
                }
                case "5":
                case "5.":
                case "5.ЭЦП":
                {
                    System.out.print("Введите хэш: ");
                    text = scan.nextLine();
                    System.out.print("Введите p: ");
                    text2 = scan.nextLine();
                    System.out.print("Введите q: ");
                    text3 = scan.nextLine();
                    System.out.print("Введите секретный ключ d: ");
                    text4 = scan.nextLine();
                    if (text.length() == 0 || text2.length() == 0 || text3.length() == 0 || text4.length() == 0
                            || convertToInt(text) == -1 || convertToInt(text2) == -1
                            || convertToInt(text3) == -1 || convertToInt(text4) == -1)
                        System.out.println("Заполните все поля корректно!");
                    else
                    {
                        EDS output = new EDS();
                        EDS e = new EDS();
                        EDS n = new EDS();
                        EDS d = new EDS();
                        System.out.println("Открытый ключ: (" + e.finding_e() + ", " + n.get_n() + ") " + "Закрытый ключ: (" + d.get_d() + ", " + n.get_n() + ") " +
                                "Ответ: " + output.Encrypt());
                    }
                    System.out.println("Хотите продолжить? Введите 1, если да, введите 0, если нет.");
                    check = scan.nextInt();
                    exit = check != 1;
                    break;
                }
            }
        }
    }
    public static String get_text() { return text; }
    public static String get_key() { return text2; }
    public static int get_p() { return convertToInt(text2); }
    public static int get_q() { return convertToInt(text3); }
    public static int get_other() { return convertToInt(text4); }
    public static int convertToInt(String text)
    {
        int summ = 0;
        for (int i = 0; i < text.length(); i++)
        {
            if (text.charAt(i) < '0' || text.charAt(i) > '9')
                return -1;
            summ *= 10;
            summ += text.charAt(i) - '0';
        }
        return summ;
    }
}