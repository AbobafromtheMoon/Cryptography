/*
    2 задание.
    Идея: реализовать шифр Атбаш - то шифр подстановки с одним конкретным ключом,
    где все буквы поменяны местами, то есть от A до Z и от Z до A. Чтобы зашифровать сообщение,
    найдите букву, которую вы хотите зашифровать, в исходной строке, затем замените ее буквой ключа.
*/
public class Simple_Wildcard_Cipher
{
    String Alphabet = "abcdefghijklmnopqrstuvwxyz !";
    String Key = reverseAlphabet();

    public String reverseAlphabet() //метод переворота алфавита для формирования ключа
    {
        String reverse = "";
        for (int i = Alphabet.length() - 1; i >= 0; i--)
            reverse += Alphabet.charAt(i);
        return reverse;
    }

    byte key[] = Key.getBytes();
    byte alphabet[] = Alphabet.getBytes();
    String text = "cyber security is amazing!";
    byte Input[] = text.getBytes();

    public String getInitialText_1() { return text;}

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
        return result;
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
        return result;
    }
}
