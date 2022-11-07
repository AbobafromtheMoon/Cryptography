public class Hash
{
    String text = Main.get_text();
    int p = Main.get_p();
    int q = Main.get_q();
    int H0 = Main.get_other();
    public String Encrypt()
    {
        int n = p * q;
        for (int i = 0; i < text.length(); i++)
        {
            if (text.charAt(i) >= 'А' && text.charAt(i) <= 'Е')
                H0 = (int) Math.pow(H0 + text.charAt(i) - 'А' + 1, 2) % n;
            else if (text.charAt(i) >= 'а' && text.charAt(i) <= 'е')
                H0 = (int) Math.pow(H0 + text.charAt(i) - 'а' + 1, 2) % n;
            else if (text.charAt(i) >= 'Ж' && text.charAt(i) <= 'Я')
                H0 = (int) Math.pow(H0 + text.charAt(i) - 'А' + 2, 2) % n;
            else if (text.charAt(i) >= 'ж' && text.charAt(i) <= 'я')
                H0 = (int) Math.pow(H0 + text.charAt(i) - 'а' + 2, 2) % n;
            else if (text.charAt(i) == 'Ё' || text.charAt(i) == 'ё')
                H0 = (int) Math.pow(H0 + 7, 2) % n;
            else return ("Ошибка: в исходном тексте введены неверные символы!");
        }
        return String.valueOf(H0);
    }
}
