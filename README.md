# Block-Cipher
Very simple symmetric block encryption algorithm

Consider a very simple symmetric block encryption algorithm in which 8-bit blocks of plaintext m(i) are encrypted using 8-bit key c(i-1), i.e. we have encryption of current block c(i) which is calculated as follows:

c(i) = m(i) c(i-1)

in which  means exclusive OR operation, m(i) is the current 8-bit message of one block and c(i-1) is the previous 8-bit ciphertext.
 
Develop a program to implement the encryption algorithm above and to encrypt the text message HTTP/1.1 using an Initial Vector (IV), i.e. c(0) = 10001001 and length for each block is 8-bit. 
