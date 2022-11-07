import java.math.BigInteger;

public class EDS
{
    int r = Main.convertToInt(Main.get_text());
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

        BigInteger s = (BigInteger.valueOf(r).pow(d)).mod(BigInteger.valueOf(n));

        if (((s.pow(e)).mod(BigInteger.valueOf(n))).intValue() != r) return ("Ошибка: подпись не подтверждена!");

        return String.valueOf(s);
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
