/* Program: Huffman Encoding
 * Author: Bryce Gattis
 * Date: Fall 2019
 * Course: CS375 Software Engineering II
 * Compile: cd into src/main/java folder, javac com\mycompany\huffmanse2\*.java
 * Execute: cd into src/main/java folder, java com.mycompany.huffmanse2.HuffmanSE2 ..\..\test\resources\abra.txt ..\..\test\resources\abraOut.txt  
 * Note: Compresses a file
 */
public class HuffmanSE2 
{
    public static void main( String[] args )
    {
        //if (args[0].equals("-")) compress();
        //else if (args[0].equals("+")) expand();
        //else throw new RuntimeException("Illegal command line argument");
        String input = args[0];
        String output = args[1];
        compress(input, output);
    }
    private static final int R = 256;
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
    
    public static void compress(String inputFile, String outputFile){
        BinaryStdInFile.initialize(inputFile);
        BinaryStdOutFile.initialize(outputFile);
        String s = BinaryStdInFile.readString();
        char[] input = s.toCharArray();
        
        int[] freq = new int[R];
        for(int i = 0; i < input.length; i++)
            freq[input[i]]++;
        
        Node root = buildTrie(freq);
        
        String[] st = new String[R];
        buildCode(st, root, "");
        
        writeTrie(root);
        err_println("writeTrie");
        
        BinaryStdOutFile.write(input.length);
        err_println("writing input length " + input.length);
        
        err_println("happily encoding... ");
        for(int i = 0; i < input.length; i++){
            String code = st[input[i]];
            err_print("Char " + input[i] + " ");
            for(int j = 0; j < code.length(); j++) {
                if(code.charAt(j) == '0') {
                    BinaryStdOutFile.write(false);
                    err_print("0");
                }
                else if(code.charAt(j) == '1') {
                    BinaryStdOutFile.write(true);
                    err_print("1");
                }
                else throw new RuntimeException("Illegal State");
            }
            err_println("");
        }
       BinaryStdOutFile.flush();
    }
    
    private static Node buildTrie(int[] freq) {
        MinPQ<Node> pq = new MinPQ<Node>();
        for(char i = 0; i < R; i++)
            if(freq[i] > 0)
                pq.insert(new Node(i, freq[i], null, null));
        while(pq.size() > 1) {
            Node left = pq.delMin();
            Node right = pq.delMin();
            Node parent = new Node('\0', left.freq + right.freq, left, right);
            err_println("buildTrie parent " + left.freq + " " + right.freq);
            pq.insert(parent);
        }  
        return pq.delMin();
    }
    
    private static void writeTrie(Node x) {
        if(x.isLeaf()) {
            BinaryStdOutFile.write(true);
            BinaryStdOutFile.write(x.ch);
            err_println("T" + x.ch);
            return;
        }
        BinaryStdOutFile.write(false);
        err_print("F");
        
        writeTrie(x.left);
        writeTrie(x.right);
    }
    
    private static void buildCode(String[] st, Node x, String s) {
        if(!x.isLeaf()) {
            buildCode(st, x.left, s + '0');
            buildCode(st, x.right, s + '1');
        }
        else{
            st[x.ch] = s;
            err_println("buildCode " + x.ch + " " + s);
        }
    }
    
    public static void expand() {
        Node root = readTrie();
        
        int length = BinaryStdIn.readInt();
        
        for(int i = 0; i < length; i++) {
            Node x = root;
            while (!x.isLeaf()) {
                boolean bit = BinaryStdIn.readBoolean();
                if (bit) x = x.right;
                else x = x.left;
            }
            BinaryStdOut.write(x.ch);
        }
        BinaryStdOut.flush();
    }
    
    private static Node readTrie() {
        boolean isLeaf = BinaryStdIn.readBoolean();
        if (isLeaf) {
            char x = BinaryStdIn.readChar();
            err_println(":t " + x);
            return new Node(x, -1, null, null);
        }
        else {
            err_print("f");
            return new Node('\0', -1, readTrie(), readTrie());
        }
    }
}
