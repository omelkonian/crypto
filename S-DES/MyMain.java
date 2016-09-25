
public class MyMain {

	public static void main( String args[]) throws Exception	{
		
    if( args.length != 2)	{
      System.err.println( "pass parameters in the command line like this 	----->	 MyMain 10_bit_key 8_bit_plaintext");
      System.exit(1);
    }
 
    int Key = Integer.parseInt( args[0], 2);	//first argument is the 10-bit key
	S_DES myDes = new S_DES( Key);				//call S_DES constructor
 
    int plaintext = Integer.parseInt( args[1], 2);	//second argument is the 8-bit plaintext
    int ciphertext = myDes.encrypt( plaintext);		//encrypt the plaintext
 
	System.out.print("\n	Ciphertext: ");
	S_DES.print( ciphertext, 8);
	System.out.print("\n");
	
	plaintext = myDes.decrypt( ciphertext);			//decrypt the ciphertext
 
	System.out.print("\n	Plaintext: ");
	S_DES.print( plaintext, 8);
	System.out.print("\n \n");
  }
}
