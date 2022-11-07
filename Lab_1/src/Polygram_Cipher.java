/*
    4 задание.
 */
public class Polygram_Cipher
{
    String Alphabet = "abcdefghijklmnopqrstuvwxyz";
    char[] alphabet = Alphabet.toCharArray();
    String text = "hello";
    char[] Input = text.toCharArray();
    String key = new String();
    public String getInitialText_3() { return text;}

    private char[][] Matrix(String key)
    {
        char[][] matrix = new char[5][5];
        char[] keyChars = key.toCharArray();
        int count = 0;

        for(int i = 0; i < keyChars.length; i++)
        {
            for(int j = 0; j < alphabet.length; j++)
            {
                if(keyChars[i] == alphabet[j])
                {
                    alphabet[j] = '\0';
                    count++;
                }
            }
        }

        char[] temp = new char[alphabet.length - count];
        count = 0;

        for (int i = 0; i < alphabet.length;i++)
        {
            if(alphabet[i] != '\0')
            {
                temp[count] = alphabet[i];
                count++;
            }
        }

        count = 0;
        int tempC = 0;

        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                if(count != keyChars.length)
                {
                    matrix[i][j] = keyChars[count];
                    count++;
                }else
                {
                    matrix[i][j] = temp[tempC];
                    tempC++;
                }
            }
        }
        return matrix;
    }
    public String Encrypt() // ключ не должен содержать одинаковые символы
    {
        char[][] matrix = Matrix(key);
        int size = 0;

        if(Input.length % 2 == 0)
        {
            size = Input.length / 2;
        }else
        {
            size = (Input.length / 2) + 1;
        }
        char[][] blockInput = new char[size][2];
        int count = 0;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                if(count < Input.length)
                {
                    blockInput[i][j] = Input[count];
                    count++;
                }
            }
        }
        String result = "";
        for(int i = 0; i < size; i++)
        {
            for(int h = 0; h < 2; h++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        if (blockInput[i][h]== matrix[j][k]) {
                            if (k - 1 < 0) {
                                if (j - 1 < 0) {
                                    if(h == 0)
                                    {
                                        result += String.valueOf(matrix[4][4]);
                                    }else
                                    {
                                        result += String.valueOf(matrix[j][k + 1]);
                                    }
                                } else {
                                    if(h == 0)
                                    {
                                        result += String.valueOf(matrix[j - 1][4]);
                                    }else {
                                        result += String.valueOf(matrix[j][k + 1]);
                                    }
                                }
                            } else if (k + 1 > 4) {
                                if (j + 1 > 4) {
                                    if(h == 0)
                                    {
                                        result += String.valueOf(matrix[j][k - 1]);
                                    }else
                                    {
                                        result += String.valueOf(matrix[0][0]);
                                    }
                                } else {
                                    if(h == 0)
                                    {
                                        result += String.valueOf(matrix[j][k - 1]);
                                    }else
                                    {
                                        result += String.valueOf(matrix[j + 1][0]);
                                    }
                                }
                            } else {
                                if(h == 0)
                                {
                                    result += String.valueOf(matrix[j][k - 1]);
                                }else
                                {
                                    result += String.valueOf(matrix[j][k + 1]);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
    public String Decrypt()
    {
        String text = Encrypt();
        char[][] matrix = Matrix(key);
        char[] input = text.toCharArray();

        int size = 0;
        if (input.length % 2 == 0) {
            size = input.length / 2;
        } else {
            size = (input.length / 2) + 1;
        }
        char[][] blockInput = new char[size][2];
        int count = 0;

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < 2; j++)
            {
                if(count < input.length)
                {
                    blockInput[i][j] = input[count];
                    count++;
                }
            }
        }
        String result = "";
        for(int i = 0; i < size; i++)
        {
            for(int h = 0; h < 2; h++) {
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        if (blockInput[i][h]== matrix[j][k]) {
                            if (k - 1 < 0) {
                                if (j - 1 < 0) {
                                    if(h == 1)
                                    {
                                        result += String.valueOf(matrix[4][4]);
                                    }else
                                    {
                                        result += String.valueOf(matrix[j][k + 1]);
                                    }
                                } else {
                                    if(h == 1)
                                    {
                                        result += String.valueOf(matrix[j - 1][4]);
                                    }else {
                                        result += String.valueOf(matrix[j][k + 1]);
                                    }
                                }
                            } else if (k + 1 > 4) {
                                if (j + 1 > 4) {
                                    if(h == 1)
                                    {
                                        result += String.valueOf(matrix[j][k - 1]);
                                    }else
                                    {
                                        result += String.valueOf(matrix[0][0]);
                                    }
                                } else {
                                    if(h == 1)
                                    {
                                        result += String.valueOf(matrix[j][k - 1]);
                                    }else
                                    {
                                        result += String.valueOf(matrix[j + 1][0]);
                                    }
                                }
                            } else {
                                if(h == 0)
                                {
                                    result += String.valueOf(matrix[j][k + 1]);
                                }else
                                {
                                    result += String.valueOf(matrix[j][k - 1]);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}


