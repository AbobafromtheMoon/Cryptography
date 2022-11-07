import javax.xml.bind.DatatypeConverter;
import java.security.*;

public class RSA_Signature
{
    //реализация цифровой подписи с использованием алгоритма хэширования MD5 и алгоритмом RSA
    public static byte[] Digital_Signature(byte[] text, PrivateKey key) throws Exception
    {
        Signature signature = Signature.getInstance("MD5withRSA"); //создаем подпись, выбирая алгоритм хэширования MD5 и RSA
        signature.initSign(key); //инициализируем объект для подписи с использованием закрытого ключа
        signature.update(text); //обновляем подпись для введенного текста
        return signature.sign(); //возвращаем подпись
    }
    //генерируем закрытый и открытый ключ при помощи алгоритма RSA
    public static KeyPair RSA_KeyPair() throws Exception
    {
        SecureRandom secureRandom = new SecureRandom(); //используем метод секретного рандома
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA"); //выбираем алгоритм RSA
        keyPairGenerator.initialize(512, secureRandom);  //инициализируем ключи определенной длины
        return keyPairGenerator.generateKeyPair(); //возвращаем пару ключей
    }
    //верификация подписи
    public static boolean Verifying(byte[] text, byte[] Verify, PublicKey key) throws Exception
    {
        Signature signature = Signature.getInstance("MD5withRSA"); //создаем подпись, выбирая алгоритм хэширования MD5 и RSA
        signature.initVerify(key); //инициализиурем объект для верификации с использованием открытого ключа
        signature.update(text); //обновляем подпись для введенного текста
        return signature.verify(Verify); //возвращаем результат проверки
    }
    public static void main(String args[]) throws Exception
    {
        String text = "Hi there!";
        System.out.println("Your message: " + text);
        System.out.println();

        KeyPair keyPair = RSA_KeyPair();
        byte[] signature = Digital_Signature(text.getBytes(), keyPair.getPrivate());

        System.out.println("Secret key: " + keyPair.getPrivate());
        System.out.println();
        System.out.println("Public key: " + keyPair.getPublic());
        System.out.println();
        System.out.println("Signature Value:\n" + DatatypeConverter.printHexBinary(signature));
        System.out.println();
        System.out.println("Signature is verified?: " + Verifying(text.getBytes(), signature, keyPair.getPublic()));
    }
}