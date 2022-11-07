import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import java.security.*;
import java.util.*;
/*
    Сначала хотела делать через switch() case, но в процессе написания ЛР выяснила, что в каждом
    блоке case будет один и тот же код, поэтому я сделала возможность многократного ввода и условие на
    соответствие названий алгоритма
 */

public class Main
{
    static
    {
        Security.addProvider(new BouncyCastleProvider()); //"регистрируем" провайдера один раз, чтобы не делать этого
                                                          //много раз в методах
    }
    public static void Encrypt(String text, String algorithm) throws NoSuchAlgorithmException, NoSuchProviderException
    {
        byte Input[] = text.getBytes(); //переводим введенный текст в байты
        MessageDigest hash = MessageDigest.getInstance(algorithm, BouncyCastleProvider.PROVIDER_NAME); //выбор хэш-функции
        byte result[] = hash.digest(Input); //результат после хэширования

        int limit = 50; //предел длины нашего сообщения
        if (text.codePointCount(0, text.length()) > limit)
            text = text.substring(0, text.offsetByCodePoints(0, limit)); //сообщение обрезается до длины предела
        else text = text; //сообщение остается прежним

        System.out.println("Algorithm: " + algorithm + "\n" + "Your message: " + text + "\n" + "Hash: "
        + new String(Hex.encode(result))); //вывод нашего захэшированного сообщения
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, choose an algorithm: \n" + "GOST3411\n" + "RIPEMD320\n" + "MD5\n" + "SHA-256\n"
                + "RIPEMD128\n" + "RIPEMD160\n" + "SHA-512\n" + "SHA-384\n" + "RIPEMD256\n" + "SHA-224\n");
        System.out.println("Input the selected algorithm ");

        boolean exit = false;
        int check;

        for (int k = 0; k < 20; k++) //запускаем возможность многократного ввода
        {
            String choice = scanner.next(); //выбор алгоритма хэширования
            System.out.println();

            if (choice.equals("GOST3411") || choice.equals("RIPEMD320") || choice.equals("MD5") || choice.equals("SHA-256")
                    || choice.equals("RIPEMD128") || choice.equals("RIPEMD160") || choice.equals("SHA-512") || choice.equals("SHA-384")
                    || choice.equals("RIPEMD256") || choice.equals("SHA-224"))
            {
                for (int i = 0; i < 3; i++) //таск для 3 одинаковых сообщений
                    Encrypt("3 similar messages", choice);
                System.out.println();

                for (int i = 1; i < 4; i++) //таск для 3 сообщений, отличающихся одним символом
                    Encrypt(i + " messages that differ by one character", choice);
                System.out.println();

                String text1 = "A message whose size does not exceed 1 MB"; //таск для сообщения < 1 мб
                if (text1.getBytes().length < 1024)
                    Encrypt(text1, choice);
                System.out.println();

                byte message[] = new byte[2048]; //таск для сообщений > 1 мб и < 3 мб
                new Random().nextBytes(message);
                Encrypt(new String(message), choice);
                System.out.println();

                byte big_message[] = new byte[15360]; //таск для сообщений > 10 мб
                new Random().nextBytes(big_message);
                Encrypt(new String(big_message), choice);
                System.out.println();

                System.out.println("Do you want to  repeat? Please, input 1, if you want to continue, and 0, if you want to quit");
                check = scanner.nextInt();
                if (check == 1) exit = false;
                else
                {
                    exit = true;
                    break;
                }
            }
        }
    }
}
