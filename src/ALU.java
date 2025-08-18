import java.util.Arrays;
import java.util.Random;

/**
* Simulates the arithmetic and logic unit (ALU) of a processor. Follows the
* ARMv8 architecture, with the exception of the number of bits used for input
* and output values. Uses the BINARY_LENGTH constant from the Binary class as
* the nubmer of bits for inputs and output.
*
* The ALU must be implemented using logic operations (AND, OR, NOT) on each
* set of input bits because the goal of this assignment is to learn about how
* a computer processor uses logic gates to perform arithmetic. The Java
* arithmetic operations should not be used in this class.
*
* @author Alix Sun
*/
public class ALU {

    /** Number of bits used to represent an integer in this ALU */
    public static final int INT_LENGTH = 32;

    /** Input A: an INT_LENGTH bit binary value */
    private boolean[] inputA;

    /** Input B: an INT_LENGTH bit binary value */
    private boolean[] inputB;

    /** Output: an INT_LENGTH bit binary value */
    private static boolean[] output;

    /** ALU Control input */
    private int control;

    /** Zero flag */
    private boolean zeroFlag;

    /** Carry flag */
    private boolean carryFlag;

    /** Overflow flag */
    private boolean overflowFlag;

    /** Random generator */
    private Random random;

    /**
    * Constructor initializes inputs and output to random binary values,
    * intializes control to 15, initializes zero flag to false.
    * Inputs and output arrays should have length INT_LENGTH.
    */
    public ALU() {
        // PROGRAM 1: Student must complete this method
        inputA = new boolean[INT_LENGTH];
        inputB = new boolean[INT_LENGTH];
        output = new boolean[INT_LENGTH];
        random = new Random();

        boolean randomBoolean = random.nextBoolean();
        for (int i = 0; i < INT_LENGTH; i++) {
            inputA[i] = randomBoolean;
            inputB[i] = randomBoolean;
            output[i] = randomBoolean;
        }

        control = 15;
        zeroFlag = false;

    }

    /**
    * Sets the value of inputA.
    *
    * @param b The value to set inputA to
    *
    * @exception IllegalArgumentException if array b does not have length
    * INT_LENGTH
    */
    public void setInputA(boolean[] b) {
        // PROGRAM 1: Student must complete this method
        if (b.length > 0 && b.length <= INT_LENGTH) {
            inputA = Arrays.copyOf(b, b.length);
        } else if (b.length == 0){
            throw new IllegalArgumentException("The input array is empty");
        }
    }

    /**
    * Sets the value of inputB.
    *
    * @param b The value to set inputB to
    *
    * @exception IllegalArgumentException if array b does not have length INT_LENGTH
    */
    public void setInputB(boolean[] b) {
        // PROGRAM 1: Student must complete this method

        //Use Copyof method
        if (b.length > 0 && b.length <= INT_LENGTH) {
            inputB = Arrays.copyOf(b, b.length);

        } else if (b.length == 0){
            throw new IllegalArgumentException("The input array is empty");
        }

    }

    /**
    * Sets the value of the control line to one of the following values. Note
    * that we are not implementing all possible control line values.
    * 0 for AND;
    * 1 for OR;
    * 2 for ADD;
    * 6 for SUBTRACT;
    * 7 for PASS INPUT B.
    *
    * @param c The value to set the control to.
    * @exception IllegalArgumentException if c is not 0, 1, 2, 6, or 7.
    */
    public void setControl(int c) {
        // PROGRAM 1: Student must complete this method
        control = c;
    }

    /**
    * Returns a copy of the value in the output.
    *
    * @return The value in the output
    */
    public boolean[] getOutput() {
        // PROGRAM 1: Student must complete this method
        // return value is a placeholder, student should replace with correct return
        boolean [] newOutput = new boolean[output.length];

        for (int i = 0; i < newOutput.length; i++) {
            newOutput[i] = output[i];
        }
        return newOutput;
    }

    /**
    * Returns the value of the zero data member. The zero data member should
    * have been set to true if the result of the operation was zero.
    *
    * @return The value of the zeroFlag data member
    */
    public boolean getZeroFlag() {
        // PROGRAM 1: Student must complete this method
        // return value is a placeholder, student should replace with correct return
        return zeroFlag;
    }

    /**
    * Returns the value of the carryFlag data member. The carryFlag data member
    * is set to true if the ALU addition operation has a carry out of the most
    * significant bit.
    *
    * @return The value of the carryFlag data member
    */
    public boolean getCarryFlag() {
        // PROGRAM 1: Student must complete this method
        // return value is a placeholder, student should replace with correct return
        return carryFlag;
    }

    /**
    * Returns the value of the overflowFlag data member. The overflowFlag data
    * member is set to true if the ALU addition operation has a result that
    * is overflow when the operands are signed integers.
    *
    * @return The value of the overflowFlag data member
    */
    public boolean getOverflowFlag() {
        // PROGRAM 1: Student must complete this method
        // return value is a placeholder, student should replace with correct return
        return overflowFlag;
    }

    /**
    * Activates the ALU so that the ALU performs the operation specified by
    * the control data member on the two input values. When this method is
    * finished, the output data member contains the result of the operation.
    *
    * @exception RuntimeException if the control data member is not set to
    * a valid operation code.
    */
    public void activate() {
        // PROGRAM 1: Student must complete this method
        switch(control){
            case 0:
                and();
                break;
            case 1:
                or();
                break;
            case 2:
                add();
                break;
            case 6:
                sub();
                break;
            case 7:
                passB();
                break;

        }




    }

    /**
    * Performs the bitwise AND operation: output = inputA AND inputB
    */
    private void and() {
        // PROGRAM 1: Student must complete this method
        for (int i = 0; i < output.length; i++) {
            output[i] = inputA[i] && inputB[i];
        }
    }

    /**
    * Performs the bitwise OR operation: output = inputA OR inputB
    */
    private void or() {
        // PROGRAM 1: Student must complete this method
        for (int i = 0; i < output.length; i++) {
            output[i] = inputB[i] || inputA[i];
        }
    }

    /**
    * Performs the addition operation using ripple-carry addition of each bit:
    * output = inputA + inputB
    */
    private void add() {
        // PROGRAM 1: Student must complete this method
        // This method must use the addBit method for bitwise addition.
        // Carryflag, overflow flag, zero flag
        boolean carryIn = false;
        // Initialize the zero flag, carry flag, and the overflow flag
        zeroFlag = true;
        carryFlag = false;
        overflowFlag = false;
        // Convert the signed binary to decimal to determine the occurrence of an overflow
        long decInputA = 0;
        long decInputB = 0;
        long decOutput = 0;

        for (int i = 0; i < output.length; i++) {
            output[i] = addBit(inputA[i], inputB[i], carryIn)[0];
//            System.out.println("This is the current carry in: " + carryIn);
            carryIn = addBit(inputA[i], inputB[i], carryIn)[1];

//            System.out.println("This is the first input " + inputB[i] + "This is the second input: " + inputA[i]
//                    + "This is the carry out/ carry in for the next round: " + carryIn);
//            System.out.println("This is the sum: " + output[i]);
        }
        // Check whether there's any remaining carry-out
        if (carryIn == true){
            carryFlag = true;
        }

        decInputA = Binary.binToSDec(inputA);
        decInputB = Binary.binToSDec(inputB);
        decOutput = Binary.binToSDec(output);


        // When the two input have the same sign
        if ((decInputA * decInputB) > 0) {
            // Overflow occurs if the result has a different sign
            if ((decOutput * decInputA) < 0 || (decOutput * decInputB) < 0) {
                overflowFlag = true;
            }
        } else if ((decInputA * decInputB) < 0) {
            // When the numbers have different sign, overflow occurs when the result is not smaller than inputA or B
            if ((decOutput > (decInputA + decInputB))) {
                overflowFlag = true;
            }
        }








    }

    /**
    * Performs the subtraction operation using a ripple-carry adder:
    * output = inputA - inputB
    * In order to perform subtraction, set the first carry-in to 1 and invert
    * the bits of inputB.
    */
    private void sub() {
        // PROGRAM 1: Student must complete this method
        // This method must use the addBit method for bitwise subtraction.
        boolean [] minusInputB = new boolean[inputB.length];
        long decMInputB = 0;

        // A flipped boolean array of inputB, representing -inputB
        for (int i = 0; i < inputB.length; i++) {
            minusInputB[i] = !inputB[i];
        }

        // Plus one to the flipped result to achieve two's complement
        decMInputB = Binary.binToSDec(minusInputB) + 1;
        minusInputB = Binary.sDecToBin(decMInputB, INT_LENGTH);

        setInputB(minusInputB);
        add();



    }

    /**
    * Copies inputB to the output: output = inputB
    */
    private void passB() {
        // PROGRAM 1: Student must complete this method
        zeroFlag = true;
        output = Arrays.copyOf(inputB, inputB.length);

        for (int i = 0; i < output.length; i++) {
            if (output[i] == true) {
                zeroFlag = false;
            }
        }

    }

    /**
     * The xor operator
     *
     * @param a Represents an input bit
     * @param b Represents an input bit
     * @return A boolean value represents the result
     */
    public static boolean xor(boolean a, boolean b) {
        return (a || b) && !(a && b);
    }

    /**
    * Simulates a 1-bit adder.
    *
    * @param a Represents an input bit
    * @param b Represents an input bit
    * @param c Represents the carry in bit
    * @return An array of length 2, index 0 holds the output bit and index 1
    * holds the carry out
    */
    private boolean[] addBit(boolean a, boolean b, boolean c) {
        // Write my own xor
        boolean [] result = new boolean[2];

        // PROGRAM 1: Student must complete this method
        result[0] = xor(xor(a, b), c);
        result[1] = (a && b) || (xor(a, b)) && c ;

        // If there is one bit that is true(1), the sum is not all zero, therefore zeroFlag is false
        if (result[0] == true) {
            zeroFlag = false;
        }

        // This method may only use the Java logic operations && (logical and),
        // || (logical or), and ! (logical not). Do not use any Java arithmetic
        // operators in this method.

        // return value is a placeholder, student should replace with correct return
        return result;
    }

}


