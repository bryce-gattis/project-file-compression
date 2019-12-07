import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for Huffman Encoding.
 */
public class HuffmanTest 
{
    @Test
    public void abraTest()
    {
        String desiredResult = "01010000\n" +
                               "01001010\n" +
                               "00100010\n" +
                               "01000011\n" +
                               "01000011\n" +
                               "01010100\n" +
                               "10101000\n" +
                               "01000000\n" +
                               "00000000\n" +
                               "00000000\n" +
                               "00000001\n" +
                               "10001111\n" +
                               "10010110\n" +
                               "10001111\n" +
                               "10010100\n" +
                               "120 bits";
        Huffman.main(new String[] {"src/test/resources/abra.txt", "src/test/resources/abraOut.txt"});
        String result = BinaryDumpFile.main(new String[] {"src/test/resources/abraOut.txt", "8"});
        assertTrue(desiredResult.equals(result));
    }
    @Test
    public void tinyTaleTest()
    {
        String desiredResult =  "00010110\n" +
                                "01010101\n" +
                                "11011110\n" +
                                "11011111\n" +
                                "00100000\n" +
                                "00101110\n" +
                                "01100101\n" +
                                "11001001\n" +
                                "00001010\n" +
                                "10110001\n" +
                                "01011010\n" +
                                "01000101\n" +
                                "10011010\n" +
                                "11010000\n" +
                                "10110110\n" +
                                "11011000\n" +
                                "01101110\n" +
                                "10000000\n" +
                                "00000000\n" +
                                "00000000\n" +
                                "00000110\n" +
                                "01110111\n" +
                                "11010010\n" +
                                "11011100\n" +
                                "01111110\n" +
                                "01000011\n" +
                                "01011000\n" +
                                "10011101\n" +
                                "00111100\n" +
                                "00111110\n" +
                                "11110100\n" +
                                "00100011\n" +
                                "01111101\n" +
                                "00101101\n" +
                                "11000111\n" +
                                "11100100\n" +
                                "00100100\n" +
                                "01110100\n" +
                                "10011101\n" +
                                "00111100\n" +
                                "00111110\n" +
                                "11110100\n" +
                                "00100101\n" +
                                "01000000\n" +
                                "352 bits";
        Huffman.main(new String[] {"src/test/resources/tinytinyTale.txt", "src/test/resources/tinytinyTaleOut.txt"});
        String result = BinaryDumpFile.main(new String[] {"src/test/resources/tinytinyTaleOut.txt", "8"});
        assertTrue(desiredResult.equals(result));
    }
    @Test
    public void HuffmanFullTest() throws IOException
    {
        String desiredResult = "00100100\n" +
                               "00001011\n" +
                               "10100001\n" +
                               "01101000\n" +
                               "10110111\n" +
                               "11001001\n" +
                               "10000101\n" +
                               "10110010\n" +
                               "11000011\n" +
                               "01110011\n" +
                               "00100101\n" +
                               "01001011\n" +
                               "11001010\n" +
                               "01001111\n" +
                               "01001000\n" +
                               "01011001\n" +
                               "01101101\n" +
                               "00100000\n" +
                               "00000000\n" +
                               "00000000\n" +
                               "00000100\n" +
                               "10011011\n" +
                               "11110100\n" +
                               "01000011\n" +
                               "01000100\n" +
                               "11001111\n" +
                               "10100111\n" +
                               "11010010\n" +
                               "01000101\n" +
                               "11010101\n" +
                               "00001110\n" +
                               "11100111\n" +
                               "10101101\n" +
                               "10101001\n" +
                               "11010011\n" +
                               "01110101\n" +
                               "10011001\n" +
                               "10000000\n" +
                               "304 bits";
        File test0 = new File("src/test/resources/HuffmanFullTest.txt");
        String str = "Hello this is a test && it's easy***";
        BufferedWriter writer = new BufferedWriter(new FileWriter(test0));
        writer.write(str);
        writer.close();
        Huffman.compress("src/test/resources/HuffmanFullTest.txt",
                         "src/test/resources/HuffmanFullTestCompressed.txt");
        String result = BinaryDumpFile.main(new String[] {"src/test/resources/HuffmanFullTestCompressed.txt", "8"});
        Huffman.expand("src/test/resources/HuffmanFullTestCompressed.txt",
                       "src/test/resources/HuffmanFullTest.txt");
        assertTrue(desiredResult.equals(result));
    }
    @Test
    public void LZWTest() 
    {
        LZW.compress("src/test/resources/LZWFile.txt", "src/test/resources/LZWFileCompressed.txt");
        LZW.expand("src/test/resources/LZWFileCompressed.txt", "src/test/resources/LZWFile.txt");
    }
}
