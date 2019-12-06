# project-file-compression
File compression with LZW and Huffman encoding.

Huffman Encoding:

Was devised by David Huffman in 1952 for compressing text data, many variations are still used today. This algorithm scans the file, counts the occurences of each character, builds a tree to represent each character and it's occurences (most frequently occuring at the top), creates a 'code' from the tree for each character, and then encodes the data with that 'code'. 
To decompress, Huffman must read the file for the Huffman tree, and then for each bit of coded information, walk through the decoding tree to find the decoded character.

Pros:
Cons:

LZW:


