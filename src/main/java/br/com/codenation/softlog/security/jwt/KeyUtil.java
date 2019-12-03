package br.com.codenation.softlog.security.jwt;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public final class KeyUtil {

    private KeyUtil() {
    }

    public static PrivateKey getPrivateKey(String pem) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decode(clean(pem)));

        return getKeyFactory().generatePrivate(spec);
    }

    public static PublicKey getPublicKey(String pem) throws InvalidKeySpecException, NoSuchAlgorithmException {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decode(clean(pem)));

        return getKeyFactory().generatePublic(spec);
    }

    private static byte[] decode(String pem) {
        return Base64.getDecoder().decode(pem);
    }

    private static KeyFactory getKeyFactory() throws NoSuchAlgorithmException {
        return KeyFactory.getInstance("RSA");
    }

    private static String clean(String pem) {
        return pem
            .replace("-----BEGIN PRIVATE KEY-----", "")
            .replace("-----END PRIVATE KEY-----", "")
            .replace("-----BEGIN PUBLIC KEY-----", "")
            .replace("-----END PUBLIC KEY-----", "");
    }
}