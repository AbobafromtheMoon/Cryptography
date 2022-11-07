import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

public class RSA
{
    String text = Main.get_text();
    int p = Main.get_p();
    int q = Main.get_q();
    int d = Main.get_other();
    int n = p * q;
    int fi = (p - 1) * (q - 1);
    public String Encrypt()
    {
        if (d >= fi) return ("Нарушено условие: d < f(n)!");
        for (int i = 2; i <= d; i++)
            if (d % i == 0 && fi % i == 0)
                return ("Нарушено условие: d взаимно просто с f(n)!");

        int e = finding_e();
        if (e == 0) return ("Ошибка: не получилось найти е!");

        int[] text_int = new int[3];

        for (int i = 0; i < 3; i++)
        {
            if (text.charAt(i) >= 'А' && text.charAt(i) <= 'Е')
                text_int[i] = text.charAt(i) - 'А' + 1;
            else if (text.charAt(i) >= 'а' && text.charAt(i) <= 'е')
                text_int[i] = text.charAt(i) - 'а' + 1;
            else if (text.charAt(i) >= 'Ж' && text.charAt(i) <= 'Я')
                text_int[i] = text.charAt(i) - 'А' + 2;
            else if (text.charAt(i) >= 'ж' && text.charAt(i) <= 'я')
                text_int[i] = text.charAt(i) - 'а' + 2;
            else if (text.charAt(i) == 'Ё' || text.charAt(i) == 'ё')
                text_int[i] = 7;
            else return ("Ошибка: в исходном тексте введены неверные символы!");
        }
        String result = "(";
        for (int i = 0; i < 3; i++)
        {
            BigInteger current = BigInteger.valueOf(text_int[i]);
            current = current.pow(e).mod(BigInteger.valueOf(n));
            text_int[i] = current.intValue();

            result += text_int[i];

            if (i != 2) result += ", ";
            else result += ")";
        }
        return result;
    }
    public int finding_e()
    {
        int e = 0;
        for (int k = 1; k < fi; k++)
            if ((d * k) % fi == 1)
            {
                e = k;
                break;
            }
        if (e == 0) return 0;
        else return e;
    }
    public int get_n()
    {
        return n;
    }
    public int get_d()
    {
        return d;
    }
}
