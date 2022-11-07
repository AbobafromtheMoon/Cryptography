public class XOR
{
    String text = "i want to pass my exams for 5";
    public String getInitialText() { return text; }
    String Key = "CYBER";
    byte key[] = Key.getBytes();

    public String Encrypt()
    {
        String result = "";
        char[] transposition = new char[text.length()];

        for (int i = 0; i < text.length(); i++)
        {
            transposition[i] = (char) (text.charAt(i) ^ key[i % key.length]);
            result += transposition[i];
        }
        return result;
    }
    public String Decrypt()
    {
        String result = "";
        String text = Encrypt();
        char transposition[] = new char[text.length()];

        for (int i = 0; i < text.length(); i++)
        {
            transposition[i] = (char) (text.charAt(i) ^ key[i % key.length]);
            result += transposition[i];
        }
        return result;
    }
}
