import java.util.*;

public class Main {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String s; //нужно для switch()
        boolean exit = false; //проверка продолжения цикла
        int check; //"желание" пользователя

        Transposition_Cipher output = new Transposition_Cipher();//объект класса 1 задания для вывода
        Simple_Wildcard_Cipher output_1 = new Simple_Wildcard_Cipher();
        Singlesound_Cipher output_2 = new Singlesound_Cipher();
        Polygram_Cipher output_3 = new Polygram_Cipher();
        Polyalphabetic_Cipher output_4 = new Polyalphabetic_Cipher();
        Task_6 output_5 = new Task_6();
        ROT13 output_6 = new ROT13();

        System.out.println("What task do you want to do? Please, choose Task_1 to _7");

        for (int i = 0; i < 7; i++)
        {
            if (exit == true) break;
            s = scan.next();
            switch(s)
            {
                case "Task_1":
                    Transposition_Cipher text = new Transposition_Cipher();

                    System.out.println("Our message: " + text.getInitialText());
                    System.out.println();

                    System.out.println("Encrypted text: " + output.Encrypt());
                    System.out.println();

                    System.out.println("Decrypted text: " + output.Decrypt());
                    System.out.println();

                    System.out.println("Do you want to  repeat? Please, input 1, if you want to continue, and 0, if you want to quit");
                    check = scan.nextInt();
                    if (check == 1) exit = false;
                    else exit = true;
                    break;

                case "Task_2":

                    Simple_Wildcard_Cipher text_1 = new Simple_Wildcard_Cipher();

                    System.out.println("Our message: " + text_1.getInitialText_1());
                    System.out.println();

                    System.out.println("Encrypted text: " + output_1.Encrypt());
                    System.out.println();

                    System.out.println("Decrypted text: " + output_1.Decrypt());
                    System.out.println();

                    System.out.println("Do you want to  repeat? Please, input 1, if you want to continue, and 0, if you want to quit");
                    check = scan.nextInt();
                    if (check == 1) exit = false;
                    else exit = true;
                    break;

                case "Task_3":

                    Singlesound_Cipher text_2 = new Singlesound_Cipher();

                    System.out.println("Our message: " + text_2.getInitialText_2());
                    System.out.println();

                    System.out.println("Encrypted text: " + output_2.Encrypt());
                    System.out.println();

                    System.out.println("Decrypted text: " + output_2.Decrypt());
                    System.out.println();

                    System.out.println("Do you want to  repeat? Please, input 1, if you want to continue, and 0, if you want to quit");
                    check = scan.nextInt();
                    if (check == 1) exit = false;
                    else exit = true;
                    break;

                case "Task_4":

                    Polygram_Cipher text_3 = new Polygram_Cipher();

                    System.out.println("Our message: " + text_3.getInitialText_3());
                    System.out.println();

                    System.out.println("Encrypted text: " + output_3.Encrypt());
                    System.out.println();

                    System.out.println("Decrypted text: " + output_3.Decrypt());
                    System.out.println();

                    System.out.println("Do you want to  repeat? Please, input 1, if you want to continue, and 0, if you want to quit");
                    check = scan.nextInt();
                    if (check == 1) exit = false;
                    else exit = true;
                    break;

                case "Task_5":

                    Polyalphabetic_Cipher text_4 = new Polyalphabetic_Cipher();

                    System.out.println("Our message: " + text_4.getInitialText_4());
                    System.out.println();

                    System.out.println("Encrypted text: " + output_4.Encrypt());
                    System.out.println();

                    System.out.println("Decrypted text: " + output_4.Decrypt());
                    System.out.println();

                    System.out.println("Do you want to  repeat? Please, input 1, if you want to continue, and 0, if you want to quit");
                    check = scan.nextInt();
                    if (check == 1) exit = false;
                    else exit = true;
                    break;

                case "Task_6":

                    Task_6 text_5 = new Task_6();

                    System.out.println("Our message: " + text_5.getInitialText_5());
                    System.out.println();

                    System.out.println("Encrypted text: " + output_5.Encrypt());
                    System.out.println();

                    System.out.println("Do you want to  repeat? Please, input 1, if you want to continue, and 0, if you want to quit");
                    check = scan.nextInt();
                    if (check == 1) exit = false;
                    else exit = true;
                    break;

                case "Task_7":

                    ROT13 text_6 = new ROT13();

                    System.out.println("Our message: " + text_6.getInitialText_6());
                    System.out.println();

                    System.out.println("Encrypted text: " + output_6.Encrypt());
                    System.out.println();

                    System.out.println("Decrypted text: " + output_6.Decrypt());
                    System.out.println();

                    System.out.println("Do you want to  repeat? Please, input 1, if you want to continue, and 0, if you want to quit");
                    check = scan.nextInt();
                    if (check == 1) exit = false;
                    else exit = true;
                    break;

                default: System.out.println("Invalid Task. Input again");
            }
        }
    }
}