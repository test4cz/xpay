package cn.exrick.service;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class DESUtil {
    
    /*
     * 生成密钥
     */
    public static byte[] initKey() throws Exception{
        KeyGenerator keyGen = KeyGenerator.getInstance("DES");
        keyGen.init(56);
        SecretKey secretKey = keyGen.generateKey();
        return secretKey.getEncoded();
    }

    
    /*
     * DES 加密
     */
    public static byte[] encrypt(byte[] data, byte[] key) throws Exception{
        SecretKey secretKey = new SecretKeySpec(key, "DES");
        
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] cipherBytes = cipher.doFinal(data);
        return cipherBytes;
    }
    
    
    /*
     * DES 解密
     */
    public static byte[] decrypt(byte[] data, byte[] key) throws Exception{
        SecretKey secretKey = new SecretKeySpec(key, "DES");
        
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] plainBytes = cipher.doFinal(data);
        return plainBytes;
    }
    public static String bytesToHexString(byte[] src){
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    //Test
    public static void main(String[] args) throws Exception {
        String DATA="327505053@qq.com_100_15908158601";

    byte[] desKey = DESUtil.initKey();
        System.out.println("DES KEY : " + bytesToHexString(desKey));
        byte[] desResult = DESUtil.encrypt(DATA.getBytes(), desKey);
//        System.out.println(DATA + ">>>DES 加密结果>>>" + BytesToHex.fromBytesToHex(desResult));
        System.out.println(bytesToHexString(desResult));
        byte[] desPlain = DESUtil.decrypt(desResult, desKey);
        System.out.println(DATA + ">>>DES 解密结果>>>" + new String(desPlain));
    }
}