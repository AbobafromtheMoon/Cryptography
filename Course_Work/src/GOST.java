import java.io.UnsupportedEncodingException;

public class GOST
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
        int[] R0 = new int[32];
        int[] L0 = new int[32];
        for (int i = 0; i < 32; i++)
        {
            L0[i] = text_int[i];
            R0[i] = text_int[i + 32];
        }
        int[] X0 = new int[32];
        for (int i = 0; i < 4; i++)
        {
            X0[i * 8] = (byte_key[i] + 256 & 0x80) / 0x80;
            X0[i * 8 + 1] = (byte_key[i] + 256 & 0x40) / 0x40;
            X0[i * 8 + 2] = (byte_key[i] + 256 & 0x20) / 0x20;
            X0[i * 8 + 3] = (byte_key[i] + 256 & 0x10) / 0x10;
            X0[i * 8 + 4] = (byte_key[i] + 256 & 0x8) / 0x8;
            X0[i * 8 + 5] = (byte_key[i] + 256 & 0x4) / 0x4;
            X0[i * 8 + 6] = (byte_key[i] + 256 & 0x2) / 0x2;
            X0[i * 8 + 7] = (byte_key[i] + 256 & 0x1);
        }
        int[] f = new int[32];
        for (int i = 31; i >= 0; i--)
        {
            f[i] += R0[i];
            f[i] += X0[i];
            for (; f[i] > 1; f[i] -= 2)
                if (i != 0) f[i - 1]++;
        }
        int[][] table = {{1, 13, 4, 6, 7, 5, 14, 4},
                {15, 11, 11, 12, 13, 8, 11, 10},
                {13, 4, 10, 7, 10, 1, 4, 9},
                {0, 1, 0, 1, 1, 13, 12, 2},
                {5, 3, 7, 5, 0, 10, 6, 13},
                {7, 15, 2, 15, 8, 3, 13, 8},
                {10, 5, 1, 13, 9, 4, 15, 0},
                {4, 9, 13, 8, 15, 2, 10, 14},
                {9, 0, 3, 4, 14, 14, 2, 6},
                {2, 10, 6, 10, 4, 15, 3, 11},
                {3, 14, 8, 9, 6, 12, 8, 1},
                {14, 7, 5, 14, 12, 7, 1, 12},
                {6, 6, 9, 0, 11, 6, 0, 7},
                {11, 8, 12, 3, 2, 0, 7, 15},
                {8, 2, 15, 11, 5, 9, 5, 5},
                {12, 12, 14, 2, 3, 11, 9, 3}};

        int[] after_table = new int[32];
        for (int i = 0; i < 8; i++)
        {
            after_table[i * 4] = table[f[i * 4] * 2 * 2 * 2 + f[i * 4 + 1] * 2 * 2 + f[i * 4 + 2] * 2 + f[i * 4 + 3]][i];
            after_table[i * 4 + 1] = (after_table[i * 4] & 4) / 4;
            after_table[i * 4 + 2] = (after_table[i * 4] & 2) / 2;
            after_table[i * 4 + 3] = (after_table[i * 4] & 1);
            after_table[i * 4] = (after_table[i * 4] & 8) / 8;
        }
        for (int i = 0; i < 11; i++)
        {
            int shift = after_table[0];
            System.arraycopy(after_table, 1, after_table, 0, 31);
            after_table[31] = shift;
        }
        for (int i = 0; i < 32; i++)
        {
            L0[i] ^= after_table[i];
        }
        String result = "";
        for (int i = 0; i < 32; i++)
        {
            result += R0[i];
            if ((i + 1) % 4 == 0) result += " ";
        }
        result += "\n       R1: ";
        for (int i = 0; i < 32; i++)
        {
            result += L0[i];
            if ((i + 1) % 4 == 0) result += " ";
        }
        return result;
    }
}
