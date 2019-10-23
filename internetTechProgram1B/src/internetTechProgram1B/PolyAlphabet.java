package internetTechProgram1B;

public class PolyAlphabet {
	
	String plaintext;
	
	public PolyAlphabet() {
		
	}
	
	public String encrypt(String plaintext) {
		StringBuffer str = new StringBuffer();
		
		//traverse through whole string
		for (int i = 0; i < plaintext.length(); i++) {
			//this is for punctuation and numbers
			if (!Character.isLetter(plaintext.charAt(i)) || Character.isDigit(plaintext.charAt(i))) {
				str.append(plaintext.charAt(i));
				continue;
			}
			
			//ciphers for C1, shift of 5
			if (i % 5 == 0 || i % 5 == 3) {
				//this is for spaces
				if (Character.isWhitespace(plaintext.charAt(i))) {
					str.append(plaintext.charAt(i));
					continue;
				}
				
				if (Character.isUpperCase(plaintext.charAt(i))) {
					char ch = (char)(((int)plaintext.charAt(i) + 5 - 65) % 26 + 65);
					str.append(ch);

				} else {
					char ch = (char)(((int)plaintext.charAt(i) + 5 - 97) % 26 + 97);
					str.append(ch);

				}
			} else {
				//ciphers for C2, shift of 19
				if (Character.isWhitespace(plaintext.charAt(i))) {
					str.append(plaintext.charAt(i));
					continue;
				}
				if (Character.isUpperCase(plaintext.charAt(i))) {
					char ch = (char)(((int)plaintext.charAt(i) + 19 - 65) % 26 + 65);
					str.append(ch);

				} else {
					char ch = (char)(((int)plaintext.charAt(i) + 19 - 97) % 26 + 97);
					str.append(ch);

				}
			}
		}
		
		String cipherText = str.toString();
		return cipherText;
	}
	
}
