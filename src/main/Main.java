package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Main {
	
	public static GraphicPanel gPanel;
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("render");
		
		gPanel = new GraphicPanel();
		
		window.setSize((int) gPanel.screenWidth, (int) gPanel.screenHeight);
		window.add(gPanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gPanel.startGThread();
	}
	
	public static BufferedImage getImage(String path) {
		
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(new File("res/" + path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return image;
	}
	
	public static int round(double num) {
		return (int) Math.round(num);
	}
	
	public static double[] rotatePoint(double centerX, double centerY, double pointX, double pointY, double rot) {
		
		double newX = (centerX + (pointX-centerX)*Math.cos(rot) - (pointY-centerY)*Math.sin(rot));
		double newY = (centerY + (pointX-centerX)*Math.sin(rot) + (pointY-centerY)*Math.cos(rot));
		
		return new double[] {newX, newY};
	}
}
