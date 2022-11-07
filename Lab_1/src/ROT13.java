/*
    7 задание.
    Каждая буква смещается на 13 мест.
    Шифрование файла программой ROTI3 дважды восстанавливает первоначальный файл.
 */
public class ROT13
{
    String Alphabet = "abcdefghijklmnopqrstuvwxyz";
    byte alphabet[] = Alphabet.getBytes();

    int key = 13;
    String text = "iwanttopassmyexams";
    byte Input[] = text.getBytes();
    public String getInitialText_6() { return text;}

    public String Encrypt()
    {
        String result = "";
        char[] transposition = new char[text.length()];
        for (int i = 0; i < text.length(); i++)
        {
            transposition[i] = (char) alphabet[(Alphabet.indexOf(Input[i]) + key) % Alphabet.length()];
            result += transposition[i];
        }
        return result;
    }

    public String Decrypt()
    {
        String result = "";
        String text = Encrypt();

        byte Input[] = text.getBytes();
        char[] transposition = new char[text.length()];
        for (int i = 0; i < text.length(); i++)
        {
            transposition[i] = (char) alphabet[(Alphabet.indexOf(Input[i]) + key) % Alphabet.length()];
            result += transposition[i];
        }
        return result;
    }
}
