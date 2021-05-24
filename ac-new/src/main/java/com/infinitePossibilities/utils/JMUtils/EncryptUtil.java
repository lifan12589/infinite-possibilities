package com.infinitePossibilities.utils.JMUtils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class EncryptUtil {
	
	public static byte[] encrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 正式执行加密操作
        return cipher.doFinal(src);
    }

    /**
     * @param password 密码
     * @param key      加密字符串
     * @return
     * @throws Exception 
     */
    public final static String encrypt(String password, String key) throws Exception {
        try {
            return byte2String(encrypt(password.getBytes(), key.getBytes("gbk")));
        } catch (Exception e) {
        	throw new Exception("加密失败");
        }
    }

    public static String byte2String(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }

    private final static String DES = "DES";

    /**
     * @param src 数据源
     * @param key 密钥，长度必须是8的倍数
     * @return
     * @throws Exception
     */
    public static byte[] decrypt(byte[] src, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(DES);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(DES);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);

        // 正式执行解密操作
        return cipher.doFinal(src);
    }

    public final static String decrypt(String data, String key) throws Exception {
        try {
            return new String(decrypt(String2byte(data.getBytes()), key.getBytes()),"gbk");
        } catch (Exception e) {
            throw new Exception("解密失败");
        }
    }

    public static byte[] String2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    public static void main(String[] args) {
        String encryptString = null;
		try {
			//encryptString = encrypt("{\"username\":\"2222222222\",\"password\":\"147165\",\"itype\":\"1\"}","kdl805jz");
				} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(encryptString);//输出：D993B450D30DC80B9268D68C0C0E60FF

        String desencryptString = null;
		try {
//			desencryptString = decrypt("A7745504035056F66F529E857C08C3637F7E1C8012A83B3CF9C909023E2E1824EE972569B2046419761CA83730DFF493D7A7D21AD3F0137560AF33B1294A96A53AD0E1B34A02629CBCE58A6AC144D106DAC09E69A1576205CF00280170C06ACBAB48BB6D330295D23966FB4778351BCCD25E79BF8536BFFA49ACCC1355207C1D8E117A26DE045CEE6AE9EE6DED33FCB789D51943183BB373F0882F6B2D5B30953CC1B871FEA90078A6B02AEBE8BB725111F2F59E3D6706392E006622FFFC6E364AA85ADB77418A21B896BD3F8FA8CD4962F0A30F477C9DA4361C057F8884A0F4C9C8EC59FD29E4E57B46664971E42D5FC501803700FD5D706E7402FEF47F8F842EADA6124CB925ED1A69FC214916595C37D3241108CCD72D5194C0A2DA4EB14A8FDA5D2C", "@Ew6DS6&*xN!43DR");
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(desencryptString);
    }

    //输出：is张三丰
	
}


