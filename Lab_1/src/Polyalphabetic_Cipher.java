/*
    5 задание.
    Идея: шифрование будет производиться последовательно через 2 шифра: Атбаш и ROT13
 */
public class Polyalphabetic_Cipher
{
    String Alphabet = "abcdefghijklmnopqrstuvwxyz";
    byte alphabet[] = Alphabet.getBytes();
    String text = "iwanttopassmyexams";
    byte Input[] = text.getBytes();
    String Key = reverseAlphabet();
    byte key[] = Key.getBytes();
    int key_1 = 13;
    public String reverseAlphabet()
    {
        String reverse = "";
        for (int i = Alphabet.length() - 1; i >= 0; i--)
            reverse += Alphabet.charAt(i);
        return reverse;
    }
    public String getInitialText_4() { return text; }

    public String Encrypt()
    {
        String result = "";

        for (int i = 0; i < text.length(); i++)
        {
            char[] transposition = new char[text.length()];

            int index = Alphabet.indexOf(Input[i]);
            transposition[i] = (char) key[index];

            result += transposition[i];
        }
        String subtotal = result;

        char[] transposition = new char[subtotal.length()];
        byte[] Input_1 = subtotal.getBytes();
        String result_1 = "";

        for (int i = 0; i < subtotal.length(); i++)
        {
            transposition[i] = (char) alphabet[(Alphabet.indexOf(Input_1[i]) + key_1) % Alphabet.length()];
            result_1 += transposition[i];
        }
        return result_1;
    }
    public String Decrypt()
    {
        String result = "";
        String text = Encrypt();
        byte Input[] = text.getBytes();

        for (int i = 0; i < text.length(); i++)
        {
            char[] transposition = new char[text.length()];

            int index = Key.indexOf(Input[i]);
            transposition[i] = (char) alphabet[index];

            result += transposition[i];
        }
        String subtotal = result;

        char[] transposition = new char[subtotal.length()];
        byte[] Input_1 = subtotal.getBytes();
        String result_1 = "";

        for (int i = 0; i < subtotal.length(); i++)
        {
            transposition[i] = (char) alphabet[(Alphabet.indexOf(Input_1[i]) + key_1) % Alphabet.length()];
            result_1 += transposition[i];
        }
        return result_1;
    }
}
