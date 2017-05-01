import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;

public class DecimalToHex {

	public static void main(String args[]){
		private static String vocab = null;
		static BufferedReader vocabReader = null;

		/**
		 * Set the name of the file we should get our words from.
		 * That is, the vocab filename
		 */
		public static void setVocab(String filename){
			vocab = filename;
			buildDataStructuresForNewFile();
		}


		Scanner input = new Scanner( System.in );
		System.out.print("Enter a decimal number : ");
		int num =input.nextInt();

		// calling method toHexString()

		String str = Integer.toHexString(num);
		System.out.println("Method 1: Decimal to hexadecimal: "+str);
	}

}
