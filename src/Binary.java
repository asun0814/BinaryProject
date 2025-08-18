/**
* Methods for converting between binary and decimal.
*
* @author Alix Sun
*/
import java.util.HashMap;
import java.util.Objects;

public class Binary {

    /**
     * Constant defines the maximum length of binary numbers.
     */
    private static final int MAX_LENGTH = 32;


    /**
     * Converts a two's complement binary number to signed decimal
     *
     * @param b The two's complement binary number
     * @return The equivalent decimal value
     * @throws IllegalArgumentException Parameter array length is longer than MAX_LENGTH.
     */
    public static long binToSDec(boolean[] b) {
        // PROGRAM 1: Student must complete this method
        // return value is a placeholder, student should replace with correct return

        // Initialize the array of two's complement binary numbers, which is the
        // reversed array of b, the array after simple negation and the result sum
        // converting to decimal. And a boolean value to track the simple negation
        long decSum = 0;
        boolean flip = false;
        // Copy of b after simple negation. So we won't change b
        boolean [] newB = new boolean[b.length];

        // Example of throwing an IllegalArgumentException
        // Student must write code for the required exceptions in other methods.
        // If the exception condition is true, throw the exception
        if (b.length > MAX_LENGTH) {
            // If the condition is true, the exception will be thrown
            // and the method execution will stop.
            throw new IllegalArgumentException("parameter array is longer than " + MAX_LENGTH + " bits.");
        }

        // Make a copy of b
            for (int i = 0; i < b.length; i++) {
            newB[i] = b[i];
        }


        // Negative number, conduct simple negation
        if (b[b.length - 1] == true) {
            // Simple Negation
            for (int i = 0; i < b.length; i++) {
                // First time having a false (or 1 in binary)
                if (flip == false && newB[i] == true) {
                    flip = true;
                } else if (flip == true) {
                    newB[i] = !newB[i];
                }
            }
            for (int i = 0; i < newB.length; i++) {
                if (newB[i] == true) {
                    decSum = decSum - (long) (Math.pow(2, i));
                }
            }
        } else {
            // Positive number
            for (int i = 0; i < newB.length; i++) {
//                int power = newB.length - i - 1 ;
                if (newB[i] == true) {
                    decSum = decSum + (long) (Math.pow(2, i));
                }
            }
        }
        return decSum;
    }

    /**
     * Converts an unsigned binary number to unsigned decimal
     *
     * @param b The unsigned binary number
     * @return The equivalent decimal value
     * @throws IllegalArgumentException Parameter array length is longer than MAX_LENGTH.
     */
    public static long binToUDec(boolean[] b) {
        // PROGRAM 1: Student must complete this method
        // return value is a placeholder, student should replace with correct return

        // If the exception condition is true, throw the exception
        if (b.length > MAX_LENGTH) {
            // If the condition is true, the exception will be thrown
            // and the method execution will stop.
            throw new IllegalArgumentException("parameter array is longer than " + MAX_LENGTH + " bits.");
        }
        long decSum = 0;

        for (int i = 0; i < b.length; i++) {
            if (b[i] == true) {
                decSum = decSum + (long) Math.pow(2, i);
            }
        }
        return decSum;
    }

    /**
     * Converts a signed decimal nubmer to two's complement binary
     *
     * @param d    The decimal value
     * @param bits The number of bits to use for the binary number.
     * @return The equivalent two's complement binary representation.
     * @throws IllegalArgumentException Parameter is outside valid range that can be represented with the given number of bits.
     */
    public static boolean[] sDecToBin(long d, int bits) {
        // PROGRAM 1: Student must complete this method
        // Example of throwing an IllegalArgumentException
        // Student must write code for the required exceptions in other methods.
        // If the exception condition is true, throw the exception
        if (bits > MAX_LENGTH) {
            // If the condition is true, the exception will be thrown
            // and the method execution will stop.
            throw new IllegalArgumentException("parameter array is longer than " + MAX_LENGTH + " bits.");
        }
        boolean flip = false;
        boolean[] bins = new boolean[bits];

        // Initialize the boolean array
        for (int i = 0; i < bins.length; i++) {
            bins[i] = false;
        }
        // If the method execution reaches this point, the exception was
        // not thrown.
        // Write the rest of the method here.
        // When d is positive, it is the same process as converting unsigned decimal to binary
        if (d > 0) {
            return uDecToBin(d, bits);
        } else if (d < 0) {
            bins = uDecToBin((-1 * d), bits);
            // Simple negation
            for (int i = 0; i < bins.length; i++) {
                if (flip == false && bins[i] == true) {
                    flip = true;
                } else if (flip == true) {
                    bins[i] = !bins[i];
                }
            }
        } else {
            return bins;
        }
        return bins;
    }

    /**
     * Converts an unsigned decimal number to binary
     *
     * @param d    The decimal value
     * @param bits The number of bits to use for the binary number.
     * @return The equivalent binary representation.
     * @throws IllegalArgumentException Parameter is outside valid range that can be represented with the given number of bits.
     */
    public static boolean[] uDecToBin(long d, int bits) {
        // PROGRAM 1: Student must complete this method
        // return value is a placeholder, student should replace with correct return
        // Example of throwing an IllegalArgumentException
        // Student must write code for the required exceptions in other methods.
        // If the exception condition is true, throw the exception

        if (bits > MAX_LENGTH) {
            // If the condition is true, the exception will be thrown
            // and the method execution will stop.
            throw new IllegalArgumentException("parameter array is longer than " + MAX_LENGTH + " bits.");
        }
        // If the method execution reaches this point, the exception was
        // not thrown.
        // Assign values to the elements of the binary number array and the new array
        boolean[] bins = new boolean[bits];
        int[] powerOfTwo = new int[MAX_LENGTH];
        // Initialize the boolean array
        for (int i = 0; i < bins.length; i++) {
            bins[i] = false;
        }
        // Throw an exception for negative parameter
        if (d < 0) {
            throw new IllegalArgumentException("The parameter d cannot be negative, d is an unsigned decimal");
        } else if (d > ((int) (Math.pow(2, MAX_LENGTH) - 1))) {
            throw new IllegalArgumentException("The parameter d is too big to be represented by the maximum bits, " +
                    "the maximum bits is " + MAX_LENGTH);
        }

        boolean found = false;
        // The index found d as a power of 2
        int index = 0;
        // The index shows the required bits number
        int index2 = 0;
        // An array of numbers that are power of 2
        for (int i = 0; i < powerOfTwo.length; i++) {
            powerOfTwo[i] = (int) (Math.pow(2, i));
            // When the number is a power of 2
            if ((int) d == powerOfTwo[i]) {
                index = i;
                found = true;
            } else if ((int) d > powerOfTwo[i]) {
                index2 = i;
            }
        }

        // If the parameter bits is not enough to represent the decimal
        int usedBits = index2 + 1;
        if (bits - index2 < 0) {
            throw new IllegalArgumentException("The parameter bits is too small for your decimal, there should be at " +
                    "least " + usedBits + " bits.");
        }

        //Calculate the decimal
        if (found) {
            bins[index] = true;
        } else if (d != 0) {
            // If the number is not a power of 2
            for (int i = powerOfTwo.length - 1; i > 0; i--) {
                if (d >= powerOfTwo[i]) {
                    d = d - powerOfTwo[i];
                    bins[i] = true;
                }
            }
            // Odd number
            if (d == 1) {
                bins[0] = true;
            }
        } else {
            return bins;
        }
        return bins;
    }

    /**
     * Returns a string representation of the binary number. Uses an underscore
     * to separate each group of 4 bits.
     *
     * @param b The binary number
     * @return The string representation of the binary number.
     */
    public static String toString(boolean[] b) {
        // PROGRAM 1: Student must complete this method
        // return value is a placeholder, student should replace with correct return
        String[] numbers = new String[b.length];
        String[] reverseNum = new String[numbers.length];
        String result = "";

        // Assign values to the numbers array and concatenate each element into a string
        for (int i = 0; i < b.length; i++) {
            if (b[i] == false) {
                numbers[i] = "0";
            } else {
                numbers[i] = "1";
            }
        }
        for (int i = 0; i < reverseNum.length; i++) {
            reverseNum[i] = numbers[numbers.length - i - 1];
            if (i > 0 && i % 4 == 0) {
                result += "_";
            }
            result += reverseNum[i];
        }

        return result;
    }

    /**
     * Returns a hexadecimal representation of the unsigned binary number. Uses
     * an underscore to separate each group of 4 characters.
     *
     * @param b The binary number
     * @return The hexadecimal representation of the binary number.
     */
    public static String toHexString(boolean[] b) {
        // PROGRAM 1: Student must complete this method
        // return value is a placeholder, student should replace with correct return
//        boolean [] binaryB = new boolean[b.length];
//        String [] numbers = new String[binaryB.length];
        HashMap<String, String> binToHex = new HashMap<>();
        String result = "";
        String hexString = "";

        // Setup the hashmap, the key is the four-digit binary number, the value is the matching hexadecimal
        binToHex.put("0000", "0");
        binToHex.put("000", "0");
        binToHex.put("00", "0");
        binToHex.put("0", "0");

        binToHex.put("0001", "1");
        binToHex.put("001", "1");
        binToHex.put("01", "1");
        binToHex.put("1", "1");

        binToHex.put("0010", "2");
        binToHex.put("010", "2");
        binToHex.put("10", "2");

        binToHex.put("0011", "3");
        binToHex.put("011", "3");
        binToHex.put("11", "3");

        binToHex.put("0100", "4");
        binToHex.put("100", "4");

        binToHex.put("0101", "5");
        binToHex.put("101", "5");

        binToHex.put("0110", "6");
        binToHex.put("110", "6");

        binToHex.put("0111", "7");
        binToHex.put("111", "7");

        binToHex.put("1000", "8");

        binToHex.put("1001", "9");
        binToHex.put("1010", "A");
        binToHex.put("1011", "B");
        binToHex.put("1100", "C");
        binToHex.put("1101", "D");
        binToHex.put("1110", "E");
        binToHex.put("1111", "F");

        // Represent the binary as a string first
        result = toString(b);

            // Split by "_" every four bits
            String[] groups = result.split("_");

            for (int i = 0; i < groups.length; i++) {
                // Convert to hexadecimal
                // Add underscore between each 4 bits
                hexString += binToHex.get(groups[i]);

                if (hexString.length() == 4 && hexString.length() % 4 == 0) {
                    hexString += "_";
                } else if (hexString.length() > 8 && hexString.length() % 4 == 0) {
                    hexString += "_";
                }
            }
            return hexString;
        }

    }
