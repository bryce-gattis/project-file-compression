import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for Huffman Encoding.
 */
public class HuffmanSE2Test 
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
        HuffmanSE2.main(new String[] {"src/test/resources/abra.txt", "src/test/resources/abraOut.txt"});
        String result = BinaryDumpFile.main(new String[] {"src/test/resources/abraOut.txt", "8"});
        System.out.println(result);
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
        HuffmanSE2.main(new String[] {"src/test/resources/tinytinyTale.txt", "src/test/resources/tinytinyTaleOut.txt"});
        String result = BinaryDumpFile.main(new String[] {"src/test/resources/tinytinyTaleOut.txt", "8"});
        System.out.println(result);
        assertTrue(desiredResult.equals(result));
    }
}
