package cp213;

import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Iman Chaudhry 
 * @version 2023-05-23
 */
public class SerialNumber {

    /**
     * Determines if a string contains all digits.
     *
     * @param str The string to test.
     * @return true if str is all digits, false otherwise.
     */
    public static boolean allDigits(final String str) {
	
	for (int i=0; i<str.length(); i++) {
	    if (!Character.isDigit(str.charAt(i))) {
		return false;
	    } 
	}
	return true;
    }

    /**
     * Determines if a string is a good serial number. Good serial numbers are of
     * the form 'SN/nnnn-nnn', where 'n' is a digit.
     *
     * @param sn The serial number to test.
     * @return true if the serial number is valid in form, false otherwise.
     */
    public static boolean validSn(final String sn) {

	if (sn.length() == 11 && sn.startsWith("SN/") && "-".equals(sn.substring(7, 8))) {
	    String sub1 = sn.substring(3,7);
	    String sub2 = sn.substring(8);
	    
	    for (int i=0; i<sub1.length(); i++) {
		if (!Character.isDigit(sub1.charAt(i))) {
		    return false;
		}
	    }
	    
	    for (int j=0; j<sub2.length(); j++) {
		if (!Character.isDigit(sub2.charAt(j))) {
		    return false;
		}
	    }
	    
	    return true;
	    
	}
	
	return false;
    }

    /**
     * Evaluates serial numbers from a file. Writes valid serial numbers to
     * good_sns, and invalid serial numbers to bad_sns. Each line of fileIn contains
     * a (possibly valid) serial number.
     *
     * @param fileIn  a file already open for reading
     * @param goodSns a file already open for writing
     * @param badSns  a file already open for writing
     */
    public static void validSnFile(final Scanner fileIn, final PrintStream goodSns, final PrintStream badSns) {
	
	while (fileIn.hasNextLine()) {
	    String line = fileIn.nextLine();
	    if (validSn(line.trim())) {
		goodSns.println(line.trim());
	    } else {
		badSns.println(line.trim());
	    }
	}

	return;
    }
}
