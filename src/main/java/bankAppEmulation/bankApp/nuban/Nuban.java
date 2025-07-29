package bankAppEmulation.bankApp.nuban;

import bankAppEmulation.bankApp.exception.InputLengthException;

/* NUBAN = 10-digit account number generated using a checksum formula:

    The Format is XXXXXXXXXC (9-digit serial number + 1 checksum digit)

    The checksum digit is calculated using a modulus-10 algorithm with predefined weights

The process is like so:
    Multiply each digit of the 9-digit account number by its corresponding weight.

    Sum all the results.

    Modulus 10 of the sum.

    Subtract from 10.

    If result is 10, use 0.
*/
public class Nuban {
    private static final int[] WEIGHTS = {3, 7, 3, 3, 7, 3, 3, 7, 3};

    public static String generateNuban(String bankCode, String serial9Digit) {
        if (bankCode.length() != 3 || serial9Digit.length() != 9) {
            throw new InputLengthException("Input Length is Invalid");
        }
        String checksumBase = bankCode + serial9Digit;
        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(serial9Digit.charAt(i));
            sum += digit * WEIGHTS[i];
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        return serial9Digit + checkDigit;
    }

    public static boolean validateNuban(String accountNumber, String bankCode) {
        if (accountNumber.length() != 10 || bankCode.length() != 3) return false;
        String serial = accountNumber.substring(0, 9);
        String expected = generateNuban(bankCode, serial);
        return expected.equals(accountNumber);
    }
}

