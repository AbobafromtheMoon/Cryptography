import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String s; //нужно для switch()
        boolean exit = false; //проверка продолжения цикла
        int check; //"желание" пользователя

        XOR output = new XOR();
        Disposable_Notepad output_1 = new Disposable_Notepad();
        Task_3 output_2 = new Task_3();

        System.out.println("What task do you want to do? Please, choose Task_1 or Task_3");

        for (int i = 0; i < 4; i++)
        {
            if (exit == true) break;
            s = scan.next();

            switch (s) {
                case "Task_1":
                     XOR text = new XOR();

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

                    Disposable_Notepad text_1 = new Disposable_Notepad();

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

                    Task_3 text_2 = new Task_3();

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
                default:
                    System.out.println("Invalid Task. Input again");
            }
        }
    }
}