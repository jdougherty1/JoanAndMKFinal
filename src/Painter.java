
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Painter {
    static JFrame frame;
    static JPanel panel;
    static BufferedImage image;
   
    public static void main(String[] args) {
        int width = 800;
        int height = 800;
       
        frame = new JFrame();
        frame.setPreferredSize(new Dimension(width, height + 20));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(100, 100);
       
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
       
       
       
        image = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
       
        byte[] sourcePixels = new byte[width * height * 3];
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                byte R = (byte)(0xFF * (width - j) / width);
                byte B = (byte)(0b11111111 * j / width);
                byte G = (byte)(255 * i / height);
                //Need to alter the following (3 lines of) code in order to complete project
                //We should not need to alter much else
                sourcePixels[3*(i * width + j)] = B;
                sourcePixels[3*(i * width + j) + 1] = G;
                sourcePixels[3*(i * width + j) + 2] = R;
            }
        }   
       
        //The following code is just a different way of defining the sourcePixels[]
        //It could be that our code will end up needing to look more like this than the
        //  above code.
//        String x = "This is the day that the Lord has made. Let us rejoice and be glad in it.";
//        int len = x.length();
//        for(int i = 0; i < height; i++){
//            for(int j = 0; j < width; j++){
//                int index = i* width + j;
//                byte R = (byte)x.charAt(index % len);
//                byte B = (byte)x.charAt(index % len);
//                byte G = (byte)x.charAt(index % len);
//                sourcePixels[3*(i * width + j)] = R;
//                sourcePixels[3*(i * width + j) + 1] = B;
//                sourcePixels[3*(i * width + j) + 2] = G;
//            }
//        }
//       
       
        System.arraycopy(sourcePixels, 0, targetPixels, 0, sourcePixels.length);
       
        panel.repaint();

    }

}
