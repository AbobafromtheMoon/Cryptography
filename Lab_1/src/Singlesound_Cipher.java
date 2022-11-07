/*
    3 задание.
*/
public class Singlesound_Cipher
{
    String Alphabet = "abcdefghijklmnopqrstuvwxyz !";
    char[] alphabet = Alphabet.toCharArray();
    String text = "i want to pass my exams";
    char[] Input = text.toCharArray();
    public String getInitialText_2() { return text;}
    private String[] GenerationDigramm(String Alphabet)
    {
        String[] digrammAlphabet = new String[Alphabet.length() * Alphabet.length()];
        int count = 0;

        for(int i = 0; i < alphabet.length; i++)
        {
            for (int j = 0; j < alphabet.length; j++)
            {
                digrammAlphabet[count] = String.valueOf(alphabet[i]) + String.valueOf(alphabet[j]);
                count++;
            }
        }
        return digrammAlphabet;
    }
    private int[][] MatchDigramm(String Alphabet, String[] digrammAlphabet)
    {
        int[][] match = new int[Alphabet.length()][3];
        int count = 0;
        for(int i = 0; i < alphabet.length; i++)
        {
            count = i;
            for(int j = 0; j < 3; j++)
            {
                match[i][j] = count;
                count += alphabet.length;
            }
        }
        return match;
    }
    private String[] digrammAlphabet = GenerationDigramm(Alphabet);
    private int[][] match = MatchDigramm(Alphabet, digrammAlphabet);

    public String Encrypt()
    {
        String result = "";

        int count = 0;

        for(int i = 0; i < Input.length; i++)
        {
            for (int j = 0; j < alphabet.length; j++)
            {
                if (Input[i] == alphabet[j])
                {
                    result += digrammAlphabet[match[j][count]];
                    count++;
                    if(count > 2) count = 0;
                }
            }
        }
        return result;
    }

    public String Decrypt()
    {
        String text = Encrypt();
        char Input[] = text.toCharArray();
        String[] cur = new String[Input.length/2];

        String result = "";

        for(int i = 0; i < Input.length; i += 2)
            cur[i/2] = String.valueOf(Input[i]) + String.valueOf(Input[i + 1]);

        for(int i = 0; i < cur.length; i++)
        {
            for(int j = 0; j < digrammAlphabet.length; j++)
            {
                if (cur[i].equals(digrammAlphabet[j]))
                {
                    for (int h = 0; h < 26; h++)
                    {
                        for (int k = 0; k < 3; k++)
                        {
                            if (match[h][k] == j)
                                result += String.valueOf(alphabet[match[h][0]]);
                        }
                    }
                }
            }
        }
        return result;
    }

}
