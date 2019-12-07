/************************************
 * Compilation:  javac SchubsL.java
 Execution:    java SchubsL input.txt   (compress)
 Execution:    java SchubsL input.txt   (expand)
 Dependencies: BinaryIn.java BinaryOut.java
 
 Compress or expand binary input from file input using LZW.
 ***********************************/
public class SchubsL {
    private static final int R = 256;        // number of input chars
    private static final int L = 4096;       // number of codewords = 2^W
    private static final int W = 12;         // codeword width
    
    public static void compress(String inputFile, String outputFile) {
        BinaryStdInFile.initialize(inputFile);
        BinaryStdOutFile.initialize(outputFile);
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
    public static void expand(String inputFile, String outputFile) {
        BinaryStdInFile.initialize(inputFile);
        BinaryStdOutFile.initialize(outputFile);
        String[] st = new String[L];
        int i; // next available codeword value
        // initialize symbol table with all 1-character strings
        for (i = 0; i < R; i++)
            st[i] = "" + (char) i;
        st[i++] = "";                        // (unused) lookahead for EOF
        int codeword = BinaryStdInFile.readInt(W);
        String val = st[codeword];
        while (true) {
            BinaryStdOutFile.write(val);
            codeword = BinaryStdInFile.readInt(W);
            if (codeword == R) break;
            String s = st[codeword];
            if (i == codeword) s = val + val.charAt(0);   // special case hack
            if (i < L) st[i++] = val + s.charAt(0);
            val = s;
        }
        BinaryStdInFile.close();
        BinaryStdOutFile.close();
    }
}