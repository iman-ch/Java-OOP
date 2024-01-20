package cp213;

/**
 * @author Iman Chaudhry 
 * @version 2023-05-23
 */
public class Cipher {
    // Constants
    public static final String ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final int ALPHA_LENGTH = ALPHA.length();

    /**
     * Encipher a string using a shift cipher. Each letter is replaced by a letter
     * 'n' letters to the right of the original. Thus for example, all shift values
     * evenly divisible by 26 (the length of the English alphabet) replace a letter
     * with itself. Non-letters are left unchanged.
     *
     * @param s string to encipher
     * @param n the number of letters to shift
     * @return the enciphered string in all upper-case
     */
    public static String shift(final String s, final int n) {

	String encode = "";
	
	for (int i = 0; i < s.length(); i++) {
	    char ch = s.charAt(i);
	    if (Character.isLetter(ch)) {
		int index = ALPHA.indexOf(Character.toUpperCase(ch)); // alpha uppercase so to check index have to convert
		int newindex = (index + n) % ALPHA_LENGTH; // mod len ensure wrap around to beginning of alphabet if index >= len
		char newchar = ALPHA.charAt(newindex);
		if (Character.isLowerCase(ch)) {
		    encode += Character.toLowerCase(newchar); // convert to lowercase if it was previously lowercase
		} else {
		    encode += newchar;
		}
	    } else {
		encode += ch; // char is not letter
	    }
	}
	    
	return encode;
    }

    /**
     * Encipher a string using the letter positions in ciphertext. Each letter is
     * replaced by the letter in the same ordinal position in the ciphertext.
     * Non-letters are left unchanged. Ex:
     *
     * <pre>
    Alphabet:   ABCDEFGHIJKLMNOPQRSTUVWXYZ
    Ciphertext: AVIBROWNZCEFGHJKLMPQSTUXYD
     * </pre>
     *
     * A is replaced by A, B by V, C by I, D by B, E by R, and so on. Non-letters
     * are ignored.
     *
     * @param s          string to encipher
     * @param ciphertext ciphertext alphabet
     * @return the enciphered string in all upper-case
     */
    public static String substitute(final String s, final String ciphertext) {

	String encode = "";
	
	for (int i = 0; i < s.length(); i++) {
	    char ch = s.charAt(i);
	    if (Character.isLetter(ch)) {
		int index = ALPHA.indexOf(Character.toUpperCase(ch));
		encode += ciphertext.charAt(index);
	    } else {
		encode += s.charAt(i);

	    }
	}
	
	encode = encode.toUpperCase();
	return encode;
    }

}