package filepainter;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Painter {
	static JFrame frame;
	static JPanel panel;
	static BufferedImage image;
	static InputStream input = null;

	public static void main(String[] args) {
		// Get file from the first (zeroth) argument in the command line when running file
		String filename = args[0];
		File file = new File(filename);

		// Get size of file in bytes
		double numBytes = file.length();
		double numPixels = numBytes/3;
		
		// Set width and height (for the frame &etc...) based on the size of the file
		double doubWidth = Math.sqrt(numPixels);
		double doubHeight = Math.sqrt(numPixels);
		int width = (int)doubWidth;
		int height = (int)doubHeight;
		
		try {
			input = new BufferedInputStream(new FileInputStream(file));
		}
		catch (FileNotFoundException ex) {
			System.out.println("error");
		}
		

		
		// Set info for the frame and panel
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(width, height + 20));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(200, 200);

		panel = new JPanel(){
			public void paint(Graphics g) {
				g.drawImage(image, 0, 0, null);
			}
		};

		panel.setPreferredSize(new Dimension(width, height));

		frame.setLayout(new BorderLayout());
		frame.add(panel, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
		
		
		// Image stuff
		image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();

		int [] sourcePixels = new int[width * height * 3];
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
//				byte r = (byte)(255 * (width - j) / width); 	//0xFF
//				byte b = (byte)(255 * j / width); 				//0b11111111
//				byte g = (byte)(255 * i / height);		
				
				byte r = (byte)getOneByte();
				byte g = (byte)getOneByte();
				byte b = (byte)getOneByte();
				
				sourcePixels[3*(i * width + j)] = b;
				sourcePixels[3*(i * width + j) + 1] = g;
				sourcePixels[3*(i * width + j) + 2] = r;
				
//				System.out.printf("Pixel = (%d, %d, %d)\n", r, g, b);

			}
		}
		
		System.out.println("Number of total bytes = " + numBytes);
		System.out.println("Number of pixels = " + numPixels);
		System.out.println("Width = " + width);
		System.out.println("Height = " + height);

		try{
			System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
		}
		catch(ArrayStoreException ex){
			System.out.println("error in arraycopy");
		}
		panel.repaint();

	}

	/*
	 * Use the read() method of the FileInputStream class to read in any file one char at a time.
	 * It will return just one integer (based on the ASCII conversion table) for each byte(...?)
	 * 
	 */
	static int getOneByte(){
		try{
			return input.read();
		}
		catch(IOException ex){
			System.out.println("error");
		}
		return -1;
	}

}


/*
*
* //The following code is just a different way of defining the sourcePixels[]
* //It could be that our code will end up needing to look more like this than the
* //above code.
*         String x = "This is the day that the Lord has made. Let us rejoice and be glad in it.";
*         int len = x.length();
*         for(int i = 0; i < height; i++){
*             for(int j = 0; j < width; j++){
*                 int index = i* width + j;
*                 byte R = (byte)x.charAt(index % len);
*                 byte B = (byte)x.charAt(index % len);
*                 byte G = (byte)x.charAt(index % len);
*                 sourcePixels[3*(i * width + j)] = R;
*                 sourcePixels[3*(i * width + j) + 1] = B;   
*                 sourcePixels[3*(i * width + j) + 2] = G;
*             }
*         }
*        
*/


