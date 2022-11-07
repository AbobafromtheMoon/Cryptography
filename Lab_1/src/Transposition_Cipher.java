/*
    1 задание.
    Идея: создается своего рода матрица, где в первой строчке указана введенная строка с упорядоченными
    индексами. Во второй строке указывается ключ перестановки, который указывает место символов в
    зашифрованном блоке текста.
 */
public class Transposition_Cipher
{
    int[] key = {4, 2, 1 , 3, 5, 6}; //значение ключа
    public String getInitialText() { return text;} //вывод сообщения
    public String check_length(String text, int[] key) //метод, дополняющий строку недостающими символами для кратности ключу
    {
        if (text.length() % key.length == 0) //если кратно, то возвращаем строку без изменений
            return text;
        else //если нет, то дополняем строку символами
        {
            while (text.length() % key.length != 0)
                text += '0';
            return text;
        }
    }
    String text = "hello";
    byte Input[] = text.getBytes();

    public String Encrypt()
    {
        text = check_length(text, key);
        String result = "";

        Input = text.getBytes();
        String search = "";

        for (int i = 0; i < text.length(); i += key.length)
        {

            char[] transposition = new char[key.length];

            for (int j = 0; j < key.length; j++)
                transposition[key[j] - 1] = (char) Input[i + j]; //доделать строчку

            for (int j = 0; j < key.length; j++)
            {
                result += transposition[j];
                if (transposition[j] == '0')
                {
                    search += "0";
                    result = result.replace(search, "");
                    search = "";
                }
            }
        }
        return result;
    }
    public String Decrypt()
    {
        String result = "";

        String text_1 = Encrypt();

        while (text_1.length() % key.length != 0)
            text_1 += '0';

        byte Input_1[] = text_1.getBytes();

        String search = "";


        for (int i = 0; i < text.length(); i += key.length)
        {
            char[] transposition = new char[key.length];

            for (int j = 0; j < key.length; j++)
                transposition[j] = (char) Input_1[i + key[j] - 1];

            for (int j = 0; j < key.length; j++)
            {
                result += transposition[j];
                if (transposition[j] == '0')
                {
                    search += "0";
                    result = result.replace(search, "");
                    search = "";
                }
            }
        }
        return result;
    }
}
