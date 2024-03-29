import java.io.UnsupportedEncodingException;

public class DES
{
    String text = Main.get_text();
    String key = Main.get_key();
    public String Encrypt() throws UnsupportedEncodingException
    {
        int[] text_int = new int[64];
        byte[] byte_text = text.getBytes("CP1251");
        byte[] byte_key = key.getBytes("CP1251");

        for (int i = 0; i < 8; i++)
        {
            text_int[i * 8] = (byte_text[i] + 256 & 0x80) / 0x80;
            text_int[i * 8 + 1] = (byte_text[i] + 256 & 0x40) / 0x40;
            text_int[i * 8 + 2] = (byte_text[i] + 256 & 0x20) / 0x20;
            text_int[i * 8 + 3] = (byte_text[i] + 256 & 0x10) / 0x10;
            text_int[i * 8 + 4] = (byte_text[i] + 256 & 0x8) / 0x8;
            text_int[i * 8 + 5] = (byte_text[i] + 256 & 0x4) / 0x4;
            text_int[i * 8 + 6] = (byte_text[i] + 256 & 0x2) / 0x2;
            text_int[i * 8 + 7] = (byte_text[i] + 256 & 0x1);
        }
        int[] L0 = {58, 50, 42, 34, 26, 18, 10, 2,
                60, 52, 44, 36, 28, 20, 12, 4,
                62, 54, 46, 38, 30, 22, 14, 6,
                64, 56, 48, 40, 32, 24, 16, 8};

        for (int i = 0; i < 32; i++)
           L0[i] = text_int[L0[i] - 1];

        int[] R0 = {57, 49, 41, 33, 25, 17, 9, 1,
                59, 51, 43, 35, 27, 19, 11, 3,
                61, 53, 45, 37, 29, 21, 13, 5,
                63, 55, 47, 39, 31, 23, 15, 7};

        for (int i = 0; i < 32; i++)
            R0[i] = text_int[R0[i] - 1];

        int[] R_48 = new int[48];
        int count = 0;
        for (int i = 0; i < 8; i++)
        {
            R_48[i * 6 + 1] = R0[i * 4];
            R_48[i * 6 + 2] = R0[i * 4 + 1];
            R_48[i * 6 + 3] = R0[i * 4 + 2];
            R_48[i * 6 + 4] = R0[i * 4 + 3];
        }

        for (int i = 0; i < 48; i++)
        {
            if (i == 0)
            {
                R_48[i] = R0[31];
                count++;
            }
            else if (i == 5 || i == 11 || i == 17 || i == 23 || i == 29 || i == 35 || i == 41)
            {
                R_48[i] = R0[i - count];
                count++;
                R_48[i + 1] = R0[i - count];
                count++;
            }
            else if (i == 47)
            {
                R_48[i] = R0[0];
                count++;
            }
        }
        int[] key_int = new int[56];
        for (int i = 0; i < 8; i++)
        {
            key_int[i * 7] = (byte_key[i] + 256 & 0x80) / 0x80;
            key_int[i * 7 + 1] = (byte_key[i] + 256 & 0x40) / 0x40;
            key_int[i * 7 + 2] = (byte_key[i] + 256 & 0x20) / 0x20;
            key_int[i * 7 + 3] = (byte_key[i] + 256 & 0x10) / 0x10;
            key_int[i * 7 + 4] = (byte_key[i] + 256 & 0x08) / 0x08;
            key_int[i * 7 + 5] = (byte_key[i] + 256 & 0x04) / 0x04;
            key_int[i * 7 + 6] = (byte_key[i] + 256 & 0x02) / 0x02;
        }
        count = 0;
        int[] round_key = new int[48];
        for (int i = 0; i < 56; i++)
            if (i % 8 != 7 && count < 48)
            {
                round_key[count] = key_int[i];
                count++;
            }
        for (int i = 0; i < 48; i++)
            R_48[i] ^= round_key[i];

        int[][] S1 = {{14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7},
                {0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8},
                {4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0},
                {15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}};
        int[][] S2 = {{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
                {3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5},
                {0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15},
                {13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9}};
        int[][] S3 = {{10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8},
                {13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
                {13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7},
                {1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}};
        int[][] S4 = {{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
                {13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9},
                {10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
                {3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14}};
        int[][] S5 = {{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
                {14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
                {4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
                {11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}};
        int[][] S6 = {{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
                {10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
                {9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
                {4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}};
        int[][] S7 = {{4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1},
                {13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
                {1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
                {6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}};
        int[][] S8 = {{13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
                {1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2},
                {7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8},
                {2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11}};
        int[][][] S = {S1, S2, S3, S4, S5, S6, S7, S8};
        int[] R_S = new int[32];
        for (int i = 0; i < 8; i++)
        {
            R_S[i * 4] = S[i][R_48[i * 6] * 2 + R_48[i * 6 + 5]][R_48[i * 6 + 1] * 2 * 2 * 2 + R_48[i * 6 + 2] * 2 * 2 + R_48[i * 6 + 3] * 2 + R_48[i * 6 + 4]];
            R_S[i * 4 + 1] = (R_S[i * 4] & 4) / 4;
            R_S[i * 4 + 2] = (R_S[i * 4] & 2) / 2;
            R_S[i * 4 + 3] = (R_S[i * 4] & 1);
            R_S[i * 4] = (R_S[i * 4] & 8) / 8;
        }
        int[] P = {16, 7, 20, 21, 29, 12, 28, 17,
                1, 15, 23, 26, 5, 18, 31, 10,
                2, 8, 24, 14, 32, 27, 3, 9,
                19, 13, 30, 6, 22, 11, 4, 25};
        for (int i = 0; i < 32; i++)
            P[i] = R_S[P[i] - 1];

        for (int i = 0; i < 32; i++)
            P[i] ^= L0[i];

        for (int i = 0; i < 32; i++)
        {
            text_int[i] = R0[i];
            text_int[i + 32] = P[i];
        }
        int[] Result = {40, 8, 48, 16, 56, 24, 64, 32,
                39, 7, 47, 15, 55, 23, 63, 31,
                38, 6, 46, 14, 54, 22, 62, 30,
                37, 5, 45, 13, 53, 21, 61, 29,
                36, 4, 44, 12, 52, 20, 60, 28,
                35, 3, 43, 11, 51, 19, 59, 27,
                34, 2, 42, 10, 50, 18, 58, 26,
                33, 1, 41, 9, 49, 17, 57, 25};

        for (int i = 0; i < 64; i++)
            Result[i] = text_int[Result[i] - 1];

        String res = "";
        for (int i = 0; i < 64; i++)
        {
            res += Result[i];
            if ((i + 1) % 8 == 0) res += " ";
        }
        return res;
    }
}
