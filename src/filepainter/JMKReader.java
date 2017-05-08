package filepainter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class JMKReader {
	static InputStream input = null;

	public static void main(String[] args){
		String filename = args[0];
		File file = new File(filename);
		
		double numBytes = file.length();
		double numPixels = numBytes/3;
		
		double doubWidth = Math.sqrt(numPixels);
		int width = (int)doubWidth;
		double doubHeight = Math.sqrt(numPixels);
		int height = (int)doubHeight;
		
		try {
			input = new BufferedInputStream(new FileInputStream(file));
		}
		catch (FileNotFoundException ex) {
			System.out.println("error");
		}
		
		for(int i=0; i<height; i++){
			for(int j=0; j<width; j++){

				int r = getOneByte();
				int g = getOneByte();
				int b = getOneByte();

				System.out.printf("Pixel = (%d, %d, %d)\n", r, g, b);	
			}
		}
		
		System.out.println("File length = " + numBytes);
	}


	static int getOneByte(){
		try {
			return input.read();
		}
		catch (IOException ex) {
			System.out.println("error");
		}
		return -1;
	}

	static int[] getThreeBytes(){
		return new int[] {getOneByte(), getOneByte(), getOneByte()};
	}
}
