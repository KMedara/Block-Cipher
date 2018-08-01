
import java.math.*;

public class BlockCipher {
	/*
	 * Kevin Medara CS455 Cryptography and Network Security 2/22/17 Midterm
	 * Project Programming Question 2 - BlockCipher.java Encodes a string of
	 * ascii characters to hex format using a block cipher. After the given
	 * initial vector each 8 bit block will be encrypted using the previous 8
	 * bits. Program displays the final encrypted hex message.
	 */

	/* Function to perform Exclusive or */
	public static String xor_encrypt(String m, String k) {

		if (m == null || k == null)
			return null;
		char[] keys = k.toCharArray();
		char[] mesg = m.toCharArray();
		char[] newmsg = new char[m.length()];
		// System.out.println(keys + " " + mesg);

		for (int i = 0; i < m.length(); i++) {
			if (mesg[i] == keys[i % keys.length]) {
				newmsg[i] = '0';
			} else {
				newmsg[i] = '1';
			}
		}
		mesg = null;
		keys = null;
		String temp = new String(newmsg);
		return new String(temp.getBytes());

	}// xor_encrypt

	public static void main(String[] args) {
		String message = "HTTP/1.1"; // original message
		String key = "10001001"; // initial vector
		String messageBin = ""; // original message in binary
		String encryptedBin = ""; // encrypted binary message
		String encryptedHex = ""; // encrypted hex message

		System.out.println("Encrypting \"" + message + "\" using initial vector \"" + key + "\".");

		// convert original message to binary
		// --------------------------------------------------
		byte[] bytes = message.getBytes();
		StringBuilder binary = new StringBuilder();

		/*
		 * see if highest bit is set, if so add a 1, otherwise add a 0. then
		 * move allbinary string to hex string javabinary string to hex string
		 * java bits on original # to left until all bits are tested
		 */
		for (byte b : bytes) {
			int val = b;
			for (int i = 0; i < 8; i++) {
				messageBin += ((val & 128) == 0 ? 0 : 1);
				val <<= 1;
			}
			messageBin += ' ';
		}

		// ---------------------------------------------------
		/*
		 * split binary into array of bytes (delimiter is at least one white
		 * space)
		 */
		String[] pt = messageBin.split("\\s+");

		// XOR in 8 bit blocks beginning with initial vector

		/* cipher text block array */
		String[] ct = new String[8];
		ct[0] = xor_encrypt(pt[0], key);
		encryptedBin += ct[0];//add to encrypted binary string

		// current 8 bit block becomes key for next block
		for (int i = 1; i < pt.length; i++) {
			ct[i] = xor_encrypt(pt[i], ct[i - 1]);
			encryptedBin += ct[i].toString();// add rest to binary string

		}

		for (int i = 0; i < encryptedBin.length(); i++) {
			try {
				encryptedHex = new BigInteger(encryptedBin, 2).toString(16);
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());

			}
		}
		
		// System.out.println(encryptedHex);

		// ---------------------------------------------------------------------------
		// Please fill in your code here!!
		// end convert to hex
		System.out.println("Encrypted Message:\n" + encryptedHex);
		// --------------------------------------------------------------------------------
	}// end main()
}// end BlockCipher.java