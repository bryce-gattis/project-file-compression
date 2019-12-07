/* Program: Final
 * Author: Bryce Gattis
 * Credit: Sedgewick for original code
 * Date: Fall 2019
 * Course: CS375 Software Engineering II
 * Compile: cd into src/main/java folder, javac SchubsL.java
 * Execute: cd into src/main/java folder, java SchubsL <inputFile>
 * Note: Compresses a file with LZW Encoding
 */
public class SchubsL {
    private static final int R = 256;        // number of input chars
    private static final int L = 4096;       // number of codewords = 2^W
    private static final int W = 12;         // codeword width
    
    public static void main( String[] args )
    {
        //if (args[0].equals("-")) compress();
        //else if (args[0].equals("+")) expand();
        //else throw new RuntimeException("Illegal command line argument");
        for(String input : args){
            compress(input);
        }

    }
    
    public static void compress(String inputFile) {
        BinaryStdInFile.initialize(inputFile);
        BinaryStdOutFile.initialize(inputFile + ".ll");
        String input = BinaryStdInFile.readString();
        TST<Integer> st = new TST<>();
        for (int i = 0; i < R; i++)
            st.put("" + (char) i, i);
        int code = R+1;  // R is codeword for EOF
        while (input.length() > 0) {
            String s = st.longestPrefixOf(input);  // Find max prefix match s.
            BinaryStdOutFile.write(st.get(s), W);      // Print s's encoding.
            int t = s.length();
            if (t < input.length() && code < L)    // Add s to symbol table.
                st.put(input.substring(0, t + 1), code++);
            input = input.substring(t);            // Scan past s in input.
        }
        BinaryStdOutFile.write(R, W);
        BinaryStdOutFile.close();
        BinaryStdInFile.close();
    }
}