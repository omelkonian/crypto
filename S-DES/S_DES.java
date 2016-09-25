
public class S_DES  {
 
  public int K1, K2; //the two 8-bit sub-keys
  
  public static final int P10[] = { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6}; //permutation table for initial 10-bit key
  public static final int P10max = 10; 
 
  public static final int P8[] = { 6, 3, 7, 4, 8, 5, 10, 9}; //permutation table for the sub-keys
  public static final int P8max = 10;
 
  public static final int IP[] = { 2, 6, 3, 1, 4, 8, 5, 7}; //initial permutation table 
  public static final int IPmax = 8;
  
  public static final int E_P[] = { 4, 1, 2, 3, 2, 3, 4, 1}; //expansion/permutation table for using at the start of fK
  public static final int E_Pmax = 4;

  public static final int S0[][] = {  //the first S-Box
    { 1, 0, 3, 2},
 
    { 3, 2, 1, 0},
 
    { 0, 2, 1, 3},
 
    { 3, 1, 3, 2}
  };
  public static final int S1[][] = 	{  //the second S-Box
    { 0, 1, 2, 3},
 
    { 2, 0, 1, 3},
 
    { 3, 0, 1, 2},
 
    { 2, 1, 0, 3}
  };
  public static final int P4[] = { 2, 4, 3, 1}; //permutation table for using inside of fK
  public static final int P4max = 4;
  
  public static final int IPI[] = { 4, 1, 3, 5, 7, 2, 8, 6}; //Inverted initial permutation table 
  public static final int IPImax = 8;
  	
  /**
   * constructor for S_DES class (produces the two sub-keys)
   * @param K the 10-bit key 
   */
  public S_DES( int key)		{    //class constructor(just produces the sub-keys)
     key = permute( key, P10, P10max);
     
     int rightKey = (key >> 5) & 0x1F;  
     int leftKey = key & 0x1F;			
 
     rightKey = ((rightKey & 0xF) << 1) | ((rightKey & 0x10) >> 4);
     leftKey = ((leftKey & 0xF) << 1) | ((leftKey & 0x10) >> 4);
 
     K1 = permute( (rightKey << 5) | leftKey, P8, P8max);
 
     rightKey = ((rightKey & 0x7) << 2) | ((rightKey & 0x18) >> 3);
     leftKey = ((leftKey & 0x7) << 2) | ((leftKey & 0x18) >> 3);
 
     K2 = permute( (rightKey << 5) | leftKey, P8, P8max);
  }
  
  /**
   * permutes a given set of bits using a given permutation table
   * @param init 	the initial set of bits
   * @param P		the permutation table to use
   * @param pMax	size of the permutation table
   * @return	the resulting set of bits after permutation
   */
  public static int permute( int init, int P[], int pMax)		{
	   int res = 0;
	   
	   for( int i = 0; i < P.length; ++i) {
	     res <<= 1;
	     res |= (init >> (pMax - P[i])) & 1; 
	   }
	   
	   return res;
  }
  
  /**
   * the irreversible function F
   * @param rightHalve	the right-most 4 bits of the plaintext 
   * @param subKey		the 8-bit sub-key of the original 10-bit key
   * @return	output of F
   */
  public static int F( int rightHalve, int subKey)	{
    int t = permute( rightHalve, E_P, E_Pmax) ^ subKey;
    int t0 = (t >> 4) & 0xF;
    int t1 = t & 0xF;
 
    t0 = S0[ ((t0 & 0x8) >> 2) | (t0 & 1) ][ (t0 >> 1) & 0x3 ];
    t1 = S1[ ((t1 & 0x8) >> 2) | (t1 & 1) ][ (t1 >> 1) & 0x3 ];
    t = permute( (t0 << 2) | t1, P4, P4max);
 
    return t; 
  }
  
  /**
   * function F with XOR
   * @param plaintext
   * @param key
   * @return output of fK
   */
  public static int fK( int plaintext, int key)	{  
    int Left = (plaintext >> 4) & 0xF;
    int Right = plaintext & 0xF;
 
    return ((Left ^ F(Right,key)) << 4) | Right;
  }
  
  /**
   * the switch function
   * @param l 	the initial set of bits
   * @return	the resulting set of bits after switch
   */
  public static int SW( int l)	{	  
    return ((l & 0xF) << 4) | ((l >> 4) & 0xF);
  }
  
  /**
   * SDES encryption
   * @param plaintext	the 8-bit plaintext to be encrypted
   * @return	the resulting 8-bit ciphertext
   */
  public byte encrypt( int plaintext)	{
	plaintext = permute( plaintext, IP, IPmax);
	plaintext = fK( plaintext, K1);
	plaintext = SW( plaintext);
	plaintext = fK( plaintext, K2);
	plaintext = permute( plaintext, IPI, IPImax);
 
    return ((byte) plaintext);
  }
  
  /**
   * SDES decryption
   * @param ciphertext	the 8-bit ciphertext to be decrypted
   * @return	the resulting 8-bit original plaintext
   */
  public byte decrypt( int ciphertext)	{
	ciphertext = permute( ciphertext, IP, IPmax);
	ciphertext = fK( ciphertext, K2);
	ciphertext = SW( ciphertext);
	ciphertext = fK( ciphertext, K1);
	ciphertext = permute( ciphertext, IPI, IPImax);
    return (byte) ciphertext;
  }
  
  /**
   * print a set of bits in bit form
   * @param bits	the bits in int form
   * @param bitsNo	the number of bits
   */
  public static void print( int bits, int bitsNo)	{
    int mask = 1 << (bitsNo-1);
    
    while( mask > 0) {
      System.out.print( ((bits & mask) == 0) ? '0' : '1');
      mask >>= 1;
    }
  }  
  
} 
 
  
