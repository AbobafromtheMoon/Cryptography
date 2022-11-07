import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input your message");
        int message = scanner.nextInt();
        System.out.println("Input a simple pair of numbers p and q");
        int p = scanner.nextInt();
        int q = scanner.nextInt();

        if (IsNumberSimple(p) && IsNumberSimple(q) && p != q)
        {
            int n = p * q;
            int fi = (p - 1) * (q - 1);

            System.out.println("The value of fi = " + fi);

            int e, d = 0;

            for (e = 2; e < fi; e++)
                if (finding_e(e, fi) == 1) break; //проверка на взаимопростые e и fi и поиск подходящего е

            System.out.println("The value of e = " + e);

            for (int k = 0; k <= 1000000; k++)
            {
                int numerator = 1 + (k * fi); //наш числитель в d = (fi * k + 1)/e
                if (numerator % e == 0) //если делится без остатка, то находим d
                {
                    d = numerator / e;
                    break;
                }
            }
            System.out.println("The value of d = " + d);

            double c = (Math.pow(message, e)) % n; //формула для зашифровки сообщения
            System.out.println("Encrypted message: " + c);

            BigInteger N = BigInteger.valueOf(n); //конвертируем в BigInteger, так как работаем с большими числами
            BigInteger C = BigDecimal.valueOf(c).toBigInteger(); //иначе выведется NaN

            BigInteger M = (C.pow(d)).mod(N); //формула для дешифровки сообщения

            System.out.println("Decrypted message: " + M);
        }
        else System.out.println("Input an another pair of prime numbers");
    }
    public static boolean IsNumberSimple(int a) //проверка на простое число
    {
        for (int i = 2; i < a; i++)
            if (a % i == 0) return false;

        if (a == 2) return true;

        if (a < 2) return false;

        return true;
    }
    public static int finding_e(int e, int fi) //метод поиска е
    {
        if (e == 0) return fi;
        else return finding_e(fi % e, e);
    }
}
