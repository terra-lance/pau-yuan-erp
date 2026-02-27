package com.terrase.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

@SuppressWarnings("deprecation")
public final class EncryptionUtil {
	private static byte[] keyBytes;
	private static String keyString;
	private static long seedRandom = 0x23f1345773abab37L;

	static {
		keyString = "Terra/Empire";
		try {
			keyBytes = keyString.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}

	public static byte[] getKeyBytes() {
		return keyBytes;
	}

	public static String getKeyString() {
		return keyString;
	}

	/**
	 * Encrypt data using AES algorithm
	 * 
	 * @param key
	 *            Key to use for encryption, null to use default key.
	 * @param plain
	 *            Data to encrypt
	 * @return Return encrypted data
	 **/
	public static byte[] encrypt(byte[] key, byte[] plain) throws NoSuchAlgorithmException, InvalidKeyException,
			InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		if (key == null) {
			key = getKeyBytes();
		}
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

		byte[] digested = sha256.digest(key);
		digested = Arrays.copyOf(digested, 16);

		SecretKeySpec skeySpec = new SecretKeySpec(digested, "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		byte[] ivBytes = new byte[cipher.getBlockSize()];
		random.setSeed(seedRandom);
		random.nextBytes(ivBytes);
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

		return cipher.doFinal(plain);
	}

	/**
	 * Encrypt string using AES algorithm.
	 * 
	 * @param key
	 *            Key to use for encryption, null to use default key.
	 * @param plain
	 *            String to encrypt.
	 * @return Return encrypted string.
	 **/
	public static String encrypt(String key, String plain)
			throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException,
			InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {

		byte[] keyBytes = key != null ? key.getBytes("UTF-8") : getKeyBytes();
		byte[] plainBytes = plain.getBytes("UTF-8");

		return Base64.encodeBase64String(encrypt(keyBytes, plainBytes));
	}

	/**
	 * Decrypt data using AES algorithm.
	 * 
	 * @param key
	 *            Key to use for decryption, null to use default key.
	 * @param encrypted
	 *            Data to decrypt.
	 * @return Return decrypted data.
	 **/
	public static byte[] decrypt(byte[] key, byte[] encrypted) throws IllegalBlockSizeException, BadPaddingException,
			InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {

		if (key == null) {
			key = getKeyBytes();
		}
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");

		byte[] digested = sha256.digest(key);
		digested = Arrays.copyOf(digested, 16);
		SecretKeySpec skeySpec = new SecretKeySpec(digested, "AES");

		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
		byte[] ivBytes = new byte[cipher.getBlockSize()];
		random.setSeed(seedRandom);
		random.nextBytes(ivBytes);
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

		return cipher.doFinal(encrypted);
	}

	/**
	 * Decrypt string using AES algorithm.
	 * 
	 * @param key
	 *            Key to use for decryption, null to use default key.
	 * @param encrypted
	 *            String to decrypt.
	 * @return Return decrypted string.
	 **/
	public static String decrypt(String key, String encrypted)
			throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException,
			InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {

		byte[] keyBytes = key != null ? key.getBytes("UTF-8") : getKeyBytes();
		byte[] encryptedBytes = Base64.decodeBase64(encrypted);

		return new String(decrypt(keyBytes, encryptedBytes), "UTF-8");
	}
}
