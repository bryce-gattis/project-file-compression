# project-file-compression
File compression with LZW and Huffman encoding.

Installation:

git clone
cd project-file-compression
mvn test

Algorithm Theory:

Huffman Encoding:

Was devised by David Huffman in 1952 for compressing text data, many variations are still used today. This algorithm scans the file, counts the occurences of each character, builds a tree to represent each character and it's occurences (most frequently occuring at the top), creates a 'code' from the tree for each character, and then encodes the data with that 'code'. 
To decompress, Huffman must read the file for the Huffman tree, and then for each bit of coded information, walk through the decoding tree to find the decoded character.

Pros:
- More common symbols will take up less space when compressed
Cons:
- Reads the file 2 times
- Has to store the Huffman tree in the file

LZW:

Was devised by Abraham Lempel, Jacob Ziv, and Terry Welch in 1984 and is an improved implementation of LZ78 algorithm published by Lempel and Ziv in 1978. Still widely used for Unix file compression and GIFs. This algorithm holds a dictionary of the currently encoded sequences of characters, and builds upon the dictionary dynamically as it reads the file. For each set of input characters, it looks in the dictionary for that subset of symbols, and if it's not found, then it stores the current subset of symbols into the dictionary, and reduces the size of the subset by one, and finds a match in the dictionary, and puts it in output. The last input character that was not printed to output is used as the next starting point.
To decompress, LZW just does the exact same thing, but in reverse order, to build the exact same dictionary that was created when encoding the original input.

Pros:
- Don't have to store dictionary for encoding/decoding (builds dynamically)
Cons:
- Dictionary has to be built twice (more time/work)
- Implementation of the algorithm can be pretty complicated
