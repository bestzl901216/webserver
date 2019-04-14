package com.ricardo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;

/**
 * @author Ricardo
 * @date 2019/4/10
 */
@Slf4j
public class AesUtils {

    /**
     * 加密算法
     */
    private static final String ENCRYPT_ALGORITHM = "AES";

    /**
     * 加密算法/加密模式/填充类型
     * 本例采用AES加密，ECB加密模式，PKCS5Padding填充
     */
    private static final String CIPHER_MODE = "AES/ECB/PKCS5Padding";

    /**
     * 设置加密字符集
     * 本例采用 UTF-8 字符集
     */
    private static final String CHARACTER = "UTF-8";

    /**
     * 设置加密密码处理长度。
     * 不足此长度补0；
     */
    private static final int PWD_SIZE = 16;

    /**
     * BASE64加密
     *
     * @param clearText 明文，待加密的内容
     * @param password  密码，加密的密码
     * @return 返回密文，加密后得到的内容。加密错误返回null
     */
    public static String encryptBase64(String clearText, String password) {
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("未设置密钥");
        }
        if (StringUtils.isBlank(clearText)) {
            return "";
        }
        try {
            // 1 获取加密密文字节数组
            byte[] cipherTextBytes = encrypt(clearText.getBytes(CHARACTER), pwdHandler(password));
            // 2 对密文字节数组进行BASE64 encoder 得到 BASE6输出的密文 3 返回BASE64输出的密文
            BASE64Encoder base64Encoder = new BASE64Encoder();
            return base64Encoder.encode(cipherTextBytes);
        } catch (Exception e) {
            log.error("加密失败", e);
            throw new RuntimeException("加密失败");
        }
    }

    /**
     * BASE64解密
     *
     * @param cipherText 密文，带解密的内容
     * @param password   密码，解密的密码
     * @return 返回明文，解密后得到的内容。解密错误返回null
     */
    public static String decryptBase64(String cipherText, String password) {
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("未设置密钥");
        }
        if (StringUtils.isBlank(cipherText)) {
            return "";
        }
        try {
            // 1 对 BASE64输出的密文进行BASE64 decode 得到密文字节数组
            BASE64Decoder base64Decoder = new BASE64Decoder();
            byte[] cipherTextBytes = base64Decoder.decodeBuffer(cipherText);
            // 2 对密文字节数组进行解密 得到明文字节数组
            byte[] clearTextBytes = decrypt(cipherTextBytes, pwdHandler(password));
            // 3 根据 CHARACTER 转码，返回明文字符串
            return new String(clearTextBytes, CHARACTER);
        } catch (Exception e) {
            log.error("解密失败", e);
            throw new RuntimeException("解密失败");
        }
    }

    //======================>HEX<======================

    /**
     * HEX加密
     *
     * @param clearText 明文，待加密的内容
     * @param password  密码，加密的密码
     * @return 返回密文，加密后得到的内容。加密错误返回null
     */
    public static String encryptHex(String clearText, String password) {
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("未设置密钥");
        }
        if (StringUtils.isBlank(clearText)) {
            return "";
        }
        try {
            // 1 获取加密密文字节数组
            byte[] cipherTextBytes = encrypt(clearText.getBytes(CHARACTER), pwdHandler(password));
            // 2 对密文字节数组进行 转换为 HEX输出密文 3 返回 HEX输出密文
            return byte2hex(cipherTextBytes);
        } catch (Exception e) {
            log.error("加密失败", e);
            throw new RuntimeException("加密失败");
        }
    }

    /**
     * HEX解密
     *
     * @param cipherText 密文，带解密的内容
     * @param password   密码，解密的密码
     * @return 返回明文，解密后得到的内容。解密错误返回null
     */
    public static String decryptHex(String cipherText, String password) {
        if (StringUtils.isBlank(password)) {
            throw new RuntimeException("未设置密钥");
        }
        if (StringUtils.isBlank(cipherText)) {
            return "";
        }
        try {
            // 1 将HEX输出密文 转为密文字节数组
            byte[] cipherTextBytes = hex2byte(cipherText);
            // 2 将密文字节数组进行解密 得到明文字节数组
            byte[] clearTextBytes = decrypt(cipherTextBytes, pwdHandler(password));
            // 3 根据 CHARACTER 转码，返回明文字符串
            return new String(clearTextBytes, CHARACTER);
        } catch (Exception e) {
            log.error("解密失败", e);
            throw new RuntimeException("解密失败");
        }
    }

    /**
     * 密码处理方法
     * 如果加解密出问题，
     * 请先查看本方法，排除密码长度不足补"0",导致密码不一致
     *
     * @param password 待处理的密码
     * @return 密钥字节数组
     */
    private static byte[] pwdHandler(String password) {
        if (password == null) {
            password = "";
        }
        StringBuilder sb = new StringBuilder(PWD_SIZE);
        sb.append(password);
        while (sb.length() < PWD_SIZE) {
            sb.append("0");
        }
        if (sb.length() > PWD_SIZE) {
            sb.setLength(PWD_SIZE);
        }

        try {
            return sb.toString().getBytes(CHARACTER);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("编码类型错误");
        }
    }

    //======================>原始加密<======================

    /**
     * 原始加密
     *
     * @param clearTextBytes 明文字节数组，待加密的字节数组
     * @param pwdBytes       加密密码字节数组
     * @return 返回加密后的密文字节数组，加密错误返回null
     */
    private static byte[] encrypt(byte[] clearTextBytes, byte[] pwdBytes) {
        try {
            // 1 获取加密密钥
            SecretKeySpec keySpec = new SecretKeySpec(pwdBytes, ENCRYPT_ALGORITHM);
            // 2 获取Cipher实例
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            // 3 初始化Cipher实例。设置执行模式以及加密密钥
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            // 4 执行 5 返回密文字符集
            return cipher.doFinal(clearTextBytes);
        } catch (Exception e) {
            log.error("原始加密失败", e);
            throw new RuntimeException("原始加密失败");
        }
    }

    /**
     * 原始解密
     *
     * @param cipherTextBytes 密文字节数组，待解密的字节数组
     * @param pwdBytes        解密密码字节数组
     * @return 返回解密后的明文字节数组，解密错误返回null
     */
    private static byte[] decrypt(byte[] cipherTextBytes, byte[] pwdBytes) {
        try {
            // 1 获取解密密钥
            SecretKeySpec keySpec = new SecretKeySpec(pwdBytes, ENCRYPT_ALGORITHM);
            // 2 获取Cipher实例
            Cipher cipher = Cipher.getInstance(CIPHER_MODE);
            // 3 初始化Cipher实例。设置执行模式以及加密密钥
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            // 4 执行 5 返回明文字符集
            return cipher.doFinal(cipherTextBytes);
        } catch (Exception e) {
            log.error("原始解密失败", e);
            throw new RuntimeException("原始解密失败");
        }
    }

    /**
     * 字节数组转成16进制字符串
     **/
    private static String byte2hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        String tmp;
        for (byte e : bytes) {
            // 整数转成十六进制表示
            tmp = Integer.toHexString(e & 0XFF);
            if (tmp.length() == 1) {
                sb.append("0");
            }
            sb.append(tmp);
        }
        return sb.toString().toUpperCase();
    }

    /**
     * 将hex字符串转换成字节数组
     **/
    private static byte[] hex2byte(String str) {
        final int minLength = 2;
        if (str == null || str.length() < minLength) {
            return new byte[0];
        }
        str = str.toLowerCase();
        int l = str.length() / 2;
        byte[] result = new byte[l];
        for (int i = 0; i < l; ++i) {
            String tmp = str.substring(2 * i, 2 * i + 2);
            result[i] = (byte) (Integer.parseInt(tmp, 16) & 0xFF);
        }
        return result;
    }

}

