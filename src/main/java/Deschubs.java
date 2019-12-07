/* Program: Final
 * Author: Bryce Gattis
 * Credit: Sedgewick for original code
 * Date: Fall 2019
 * Course: CS375 Software Engineering II
 * Compile: cd into src/main/java folder, javac Deschubs.java
 * Execute: cd into src/main/java folder, java Deschubs <inputFile>
 * Note: Decompresses file(s) with either the Huffman or LZW decoder (deduced by the file extension)
 */

public class Deschubs {
    
    public static void main( String[] args )
    {
        for (String input : args) {
            String type = input.substring(input.length() - 3);
            if(".hh".equals(type)){
                expandHuffman(input);
            }
            else {
                expandLZW(input);
            }  
        }
    }

    public static boolean logging = true;
    private static class Node implements Comparable<Node> {
        private final char ch;
        private final int freq;
        private final Node left, right;
        
        Node(char ch, int freq, Node left, Node right) {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
        
        private boolean isLeaf(){
            assert (left == null && right == null) || (left != null && right != null);
            return (left == null && right == null);
        }
        
        @Override
        public int compareTo(Node that){
            return this.freq - that.freq;
        }
    }
    public static void err_print(String msg){
        if(logging)
            System.err.print(msg);
    }
    public static void err_println(String msg){
        if(logging)
            System.err.println(msg);
    }
    public static void expandHuffman(String inputFile) {
        BinaryStdInFile.initialize(inputFile);
        String outputPath = inputFile.substring(0, inputFile.length() - 3);
        BinaryStdOutFile.initialize(outputPath);
        Node root = readTrie();
        
        int length = BinaryStdInFile.readInt();
        
        for(int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = BinaryStdInFile.readBoolean();
                if (bit) x = x.right;
                else x = x.left;
            }
            BinaryStdOutFile.write(x.ch);
        }
        BinaryStdOutFile.flush();
    }
    
    private static Node readTrie() {
        boolean isLeaf = BinaryStdInFile.readBoolean();
        if (isLeaf) {
            char x = BinaryStdInFile.readChar();
            err_println(":t " + x);
            return new Node(x, -1, null, null);
        }
        else {
            err_print("f");
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }
    private static final int R = 256;        // number of input chars
    private static final int L = 4096;       // number of codewords = 2^W
    private static final int W = 12;         // codeword width

    public static void expandLZW(String inputFile) {
        BinaryStdInFile.initialize(inputFile);
        String outputPath = inputFile.substring(0, inputFile.length() - 3);
        BinaryStdOutFile.initialize(outputPath);
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