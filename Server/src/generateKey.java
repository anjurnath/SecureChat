import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class generateKey {
	
	static byte[] rawVal;
	static byte[] symmKey = new byte[1000];
	static String symmKeyVal;
	public static void generateSymmKey(){
		try{
			Random rand = new Random();
			int val = rand.nextInt(15000);
			String keyVal = String.valueOf(val);
			//String keyVal = password;
			byte[] keyValBytes = keyVal.getBytes();
			
			symmKey = getRawVal(keyValBytes);
			symmKeyVal = new String(symmKey);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static byte[] getRawVal(byte[] seedVal) throws Exception{
		KeyGenerator gen = KeyGenerator.getInstance("DES");
		SecureRandom secRand = SecureRandom.getInstance("SHA1PRNG");
		secRand.setSeed(seedVal);
		gen.init(56, secRand);
		SecretKey secKey = gen.generateKey();
		rawVal = secKey.getEncoded();
		
		return rawVal;
	}
}
