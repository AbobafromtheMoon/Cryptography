import java.security.SecureRandom;

public class Disposable_Notepad
{
    String Alphabet = ".?!,АБВГДЕЁЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯабвгдеёжзийклмнопрстуфхцчшщъыьэюя ";
    String text = "Хочу закрыть сессию на отлично!";
    public String getInitialText_1() { return text; }
    String notepad = randomString(text.length(), Alphabet);

    public int finder(char symbol)
    {
        for (int i = 0; i < Alphabet.length(); i++)
            if (Alphabet.charAt(i) == symbol)
                return i;
        return -1;
    }
    public String randomString(int length, String Alphabet)
    {
        SecureRandom random = new SecureRandom();
        StringBuilder s = new StringBuilder(length);

        for (int i = 0; i < length; i++)
            s.append(Alphabet.charAt(random.nextInt(Alphabet.length())));
        return s.toString();
    }

    public String Encrypt()
    {
        String result = "";
        char[] transposition = new char[text.length()];

        for (int i = 0; i < text.length(); i++)
        {
            transposition[i] = Alphabet.charAt((finder(text.charAt(i)) + finder(notepad.charAt(i))) % Alphabet.length());
            result += transposition[i];
        }
        return result;

    }
    public String Decrypt()
    {
        String text = Encrypt();
        String result = "";
        char[] transposition = new char[text.length()];
        for (int i = 0; i < text.length(); i++)
        {
            transposition[i] = Alphabet.charAt((finder(text.charAt(i)) + Alphabet.length() - finder(notepad.charAt(i))) % Alphabet.length());
            result += transposition[i];
        }
        return result;
    }
}
