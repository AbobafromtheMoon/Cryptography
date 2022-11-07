/*
    6 задание.
*/
public class Task_6
{
    String text = "Pbatenghyngvbaf! Vg'f n Pnrfne pvcure!";
    public String getInitialText_5() { return text;}

    byte[] Input = text.getBytes();
    byte[] Output = new byte[Input.length];

    public String Encrypt()
    {
        String result = "";
        int key;

        for (key = 1; key < 27; key++)
        {
            result += "\n";
            for (int i = 0; i < Input.length; i++)
            {
                byte current = Input[i];
                byte first = 0;

                if (current >= 97 && current <= 122)
                {
                    first = 97;
                    if (first != 0)
                    {
                        current = (byte) (first + (current - first + key + 26) % 26);
                        Output[i] = current;
                    }

                } else if (current >= 65 && current <= 90)
                {
                    first = 65;
                    if (first != 0)
                    {
                        current = (byte)(first + (current - first + key + 26) % 26);
                        Output[i] = current;
                    }
                } else if (current >= 32 && current <= 47)
                {
                    first = 32;
                    if (first != 0)
                    {
                        current = (byte) (first + (current - first + key + 13) % 13);
                        Output[i] = current;
                    }
                }
                result += (char) Output[i];
            }
        }
        return result;
    }
}

