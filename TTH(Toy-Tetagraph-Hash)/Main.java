public class Main {

	public static void main(String[] args) {
		int[] runningTotal = {0, 0, 0, 0};
		Main main = new Main();
		
		String block1 	= "AAREAIAAAAAAAAAG";
		String block2 	= "AANEALAAAAAAAAAD";
		String block3 	= "DIAIHAAAAAAAAAAA";
		
		block1 = main.TTH(block1, runningTotal);
		block2 = main.TTH(block2, runningTotal);
		block3 = main.TTH(block3, runningTotal);
		
		int[] runningTotal2 = {0, 0, 0, 0};
		
		/*
		String block11 = "ILEAVETWENTYMILL";
		String block22 = "IONDOLLARSTOMYFR";
		String block33 = "IENDLYCOUSINBILL";
		
		block11 = main.TTH(block11, runningTotal2);
		block22 = main.TTH(block22, runningTotal2);
		block33 = main.TTH(block33, runningTotal2);
		System.out.println("\n FINAL OUTPUT is " + block11 + " " + block22 + " " + block33 );
		*/
		System.out.println(block1 + " " + block2 + " " + block3 );
}
	
	
	public String TTH(String input, int[] runningTotal)	{
		//System.out.println("-------TTH--------");
		System.out.println("INPUT: " + input);
		System.out.println("	Running total is " + runningTotal[0] + " " + runningTotal[1]+ " " + runningTotal[2]+ " " + runningTotal[3]);
		//ROUND 1
		int[] temp = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int col1 = runningTotal[0], col2 = runningTotal[1], col3 = runningTotal[2], col4 = runningTotal[3];
		for (int i = 0; i < input.length(); i++)	{
			temp[i]=input.charAt(i) - 65;
			if ((i == 0) || (i == 4) || (i == 8) || (i == 12))	{
				col1 += temp[i];
			}
			else if ((i == 1) || (i == 5) || (i == 9) || (i == 13))	{
				col2 += temp[i];
			}
			else if ((i == 2) || (i == 6) || (i == 10) || (i == 14)) {
				col3 += temp[i];
			}
			else	{
				col4 += temp[i];
			}
		}
		col1 = col1 % 26;
		col2 = col2 % 26;
		col3 = col3 % 26;
		col4 = col4 % 26;
		System.out.println("	After Round1 \n 		Running total is " + col1 + " " + col2 + " " + col3 + " " + col4);
		//ROUND 2
		String row1 = input.substring(0, 4), tempR1 = row1.substring(0, 1);
		String newR1 = row1.substring(1, 4).concat(tempR1);
		String row2 = input.substring(4, 8), tempR2 = row2.substring(0, 2);
		String newR2 = row2.substring(2, 4).concat(tempR2);
		String row3 = input.substring(8, 12), tempR3 = row3.substring(0, 3);
		String newR3 = row3.substring(3, 4).concat(tempR3);
		String row4 = input.substring(12, 16);
		StringBuffer buffer = new StringBuffer(row4);
		row4 = buffer.reverse().toString();
		String newR4 = row4.substring(0, 2);
		newR4 += row4.substring(3,4) + row4.substring(2,3);
		input = newR1 + newR2 + newR3 + newR4;
		
		input = input.replace("ABCD", "BCDA");
		input = input.replace("EFGH", "GHEF");
		input = input.replace("IJKL", "LIJK");
		input = input.replace("MNOP", "NMPO");
		int[] temp2 = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
		int colU1 = 0, colU2 = 0, colU3 = 0, colU4 = 0;
		for (int i = 0; i < input.length(); i++)	{
			temp2[i]=input.charAt(i) - 65;
			if ((i == 0) || (i == 4) || (i == 8) || (i == 12))	{
				colU1 += temp2[i];
			}
			else if ((i == 1) || (i == 5) || (i == 9) || (i == 13))	{
				colU2 += temp2[i];
			}
			else if ((i == 2) || (i == 6) || (i == 10) || (i == 14)) {
				colU3 += temp2[i];
			}
			else	{
				colU4 += temp2[i];
			}
		}
		colU1 = colU1 % 26;
		colU2 = colU2 % 26;
		colU3 = colU3 % 26;
		colU4 = colU4 % 26;
		System.out.println("	After Round2 \n 		Running total is " + colU1 + " " + colU2 + " " + colU3 + " " + colU4);
		//COMBINE
		col1 = (col1 + colU1) % 26;
		col2 = (col2 + colU2) % 26;
		col3 = (col3 + colU3) % 26;
		col4 = (col4 + colU4) % 26;
		//CONVERT TO CHARS
		String output = new String();
		output +=(char) (col1 + 65);
		output +=(char) (col2 + 65);
		output +=(char) (col3 + 65);
		output +=(char) (col4 + 65);
		//System.out.println(output);
		runningTotal[0] = col1;
		runningTotal[1] = col2;
		runningTotal[2] = col3;
		runningTotal[3] = col4;
		System.out.println("	After combining the two rounds \n 		Running total is " + runningTotal[0] + " " + runningTotal[1]+ " " + runningTotal[2]+ " " + runningTotal[3]);
		System.out.println("OUTPUT: " + output);
		return (output);
	}
	}