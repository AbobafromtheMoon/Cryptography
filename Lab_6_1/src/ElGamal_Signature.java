import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;
import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class ElGamal_Signature
{
    static
    {
        Security.addProvider(new BouncyCastleProvider());
    }
    public static byte[] Decrypt(byte[] text, byte[] key) throws Exception
    {
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(key); //получаем закрытый ключ
        KeyFactory keyFactory = KeyFactory.getInstance("ElGamal"); //выбираем алгоритм Эль Гамаля
        PrivateKey privateKey = keyFactory.generatePrivate(pkcs8KeySpec); //генерируем закрытый ключ
        Cipher cipher = Cipher.getInstance("ElGamal", "BC"); //выбираем алгоритм Эль Гамаля
        cipher.init(Cipher.DECRYPT_MODE, privateKey); //инициализируем дешифрование с закрытым ключом
        return cipher.doFinal(text); //возвращаем дешифрованное сообщение
    }
    public static byte[] Encrypt(byte[] text, byte[] key) throws Exception
    {
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(key); //получаем открытый ключ
        KeyFactory keyFactory = KeyFactory.getInstance("ElGamal"); //выбираем алгоритм Эль Гамаля
        PublicKey publicKey = keyFactory.generatePublic(x509KeySpec); //генерируем открытый ключ
        Cipher cipher = Cipher.getInstance("ElGamal", "BC"); //выбираем алгоритм Эль Гамаля
        cipher.init(Cipher.ENCRYPT_MODE, publicKey); //инициализируем шифрование с открытым ключом
        return cipher.doFinal(text); //возвращаем зашифрованное сообщение
    }
    public static KeyPair ElGamal_KeyPair() throws Exception
    {
        SecureRandom secureRandom = new SecureRandom(); //используем метод секретного рандома
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ELGAMAL", "BC"); //выбираем алгоритм Эль Гамаля
        keyPairGenerator.initialize(256, secureRandom); //инициализируем ключи определенной длины
        return keyPairGenerator.generateKeyPair(); //возвращаем пару ключей
    }

    public static void main(String[] args) throws Exception
    {
        KeyPair keyPair = ElGamal_KeyPair();

        String text = "Hello world!" ;
        byte[] Input = text.getBytes();

        System.out.println("Your message: " + text);
        System.out.println();
        byte[] encodedData = Encrypt(Input, keyPair.getPublic().getEncoded());
        System.out.println("Signature: " + new String(Hex.encode(encodedData)));
        byte[] decodedData = Decrypt(encodedData, keyPair.getPrivate().getEncoded());

        String output = new String(decodedData);
        System.out.println();
        System.out.println("Signature is verified?");

        if (text.equals(output)) System.out.println("True");
        else System.out.println("False");
    }
}