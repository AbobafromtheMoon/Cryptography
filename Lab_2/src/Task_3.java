import java.nio.charset.Charset;
import java.util.*;

public class Task_3
{
    String text = "hello";
    public String getInitialText_2() { return text; }
    byte Input[] = text.getBytes();
    boolean flag = false;
    byte notepad[] = new byte[text.length()];

    public void notepad()
    {
        Random random = new Random();
        for (int i = 0; i < 1; i++ )
        {
            if (flag) break;
            random.nextBytes(notepad);
        }
    }
    public String Encrypt()
    {
        notepad();
        String res = "";
        byte result[] = new byte[Input.length];

        for (int i = 0; i < Input.length; i++)
        {
            result[i] = (byte) (Input[i] ^ notepad[i]);
            res += (char) result[i];
            flag = true;
        }
        return res;
    }

    public String Decrypt()
    {
        notepad();
        String res = "";

        String text = Encrypt();
        char Input[] = text.toCharArray();

        byte result[] = new byte[text.length()];

        for (int i = 0; i < text.length(); i++)
        {
            result[i] = (byte) (Input[i] ^ notepad[i]);
            res += (char) result[i];
        }
        return res;
    }
}