package cp213;

/**
 * @author Iman Chaudhry 
 * @version 2023-05-23
 */
public class Strings {
    // Constants
    public static final String VOWELS = "aeiouAEIOU";

    /**
     * Determines if string is a "palindrome": a word, verse, or sentence (such as
     * "Able was I ere I saw Elba") that reads the same backward or forward. Ignores
     * case, spaces, digits, and punctuation in the string parameter s.
     *
     * @param string a string
     * @return true if string is a palindrome, false otherwise
     */
    public static boolean isPalindrome(final String string) {

	String letters = string.replaceAll("[\\W]", "");
	String reverse = "";
	
	for (int i=letters.length()-1; i >= 0; i--) {
	    reverse += letters.charAt(i);
	
	if (letters.toLowerCase().equals(reverse.toLowerCase())) {
	    return true;
	    
	    }
	    
	}
		

	return false;
    }
    
    /**
     * Determines if name is a valid Java variable name. Variables names must start
     * with a letter or an underscore, but cannot be an underscore alone. The rest
     * of the variable name may consist of letters, numbers and underscores.
     *
     * @param name a string to test as a Java variable name
     * @return true if name is a valid Java variable name, false otherwise
     */
    public static boolean isValid(final String name) {
	if (name.length() == 0 || name.equals("_")) {
	    return false;
	}
	
	if (!Character.isLetter(name.charAt(0)) && name.charAt(0) != '_') {
	    return false;
	}	    
	
	for (int i = 0; i < name.length(); i++) {
	    char ch = name.charAt(i);
	    if (!Character.isLetterOrDigit(ch) && ch != '_') {
		return false;
	    }
	}

	return true;
    }

    /**
     * Converts a word to Pig Latin. The conversion is:
     * <ul>
     * <li>if a word begins with a vowel, add "way" to the end of the word.</li>
     * <li>if the word begins with consonants, move the leading consonants to the
     * end of the word and add "ay" to the end of that. "y" is treated as a
     * consonant if it is the first character in the word, and as a vowel for
     * anywhere else in the word.</li>
     * </ul>
     * Preserve the case of the word - i.e. if the first character of word is
     * upper-case, then the new first character should also be upper case.
     *
     * @param word The string to convert to Pig Latin
     * @return the Pig Latin version of word
     */
    public static String pigLatin(String word) {
	
	String pl = "";
	if (VOWELS.indexOf(word.charAt(0)) > -1) {
	    pl = word + "way";
	    
	} else {
	    boolean capital = false;
	    int index = 0;
	    boolean not_done = true;
	    String temp_word = "";
	    if (Character.isUpperCase(word.charAt(0))) {
		capital = true;
	    }
	    while (index < word.length() && not_done) {
		if (VOWELS.indexOf(word.charAt(index)) > -1){
		    not_done = false;
		}
		else {
		    temp_word += Character.toLowerCase(word.charAt(index));
		    index += 1;
		    }
		
		pl = word.substring(index) + temp_word + "ay";
		if (capital) {
		    pl = pl.substring(0, 1).toUpperCase() + pl.substring(1); 
		    
		}
		
	    }
	    
	    
	}

	return pl;
    }

}
