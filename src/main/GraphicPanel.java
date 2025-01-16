package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import javax.swing.JPanel;

import entity.Mesh;
import entity.Point;

public class GraphicPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1L;
	
	public double screenWidth = 960;
	public double screenHeight = 540;
	
	public int FPS = 60;
	public int activeFPS = FPS;
	
	Thread gThread;
	KeyHandler keyH = new KeyHandler();
	
	ArrayList<Mesh> meshes = new ArrayList<Mesh>();
	
	public double cameraX = 0;
	public double cameraZ = 256;
	public double cameraAngle = 0;
	
	Point[] points;
	
	public GraphicPanel() {
		
		this.setPreferredSize(new Dimension((int) screenWidth, (int) screenHeight));
		this.setBackground(Color.white);
		this.setDoubleBuffered(true);
		this.setFocusable(true);
		this.addKeyListener(keyH);
		
		points = new Point[] {	new Point(10,100,-10), new Point(-10,100,10),
								new Point(10,100,10), new Point(-10,100,-10),
								new Point(10,-100,-10), new Point(-10,-100,10),
								new Point(10,-100,10), new Point(-10,-100,-10)};
		
		meshes.add(new Mesh(points, 150, 0, 0, 1, Color.red, this));
		
		points = new Point[] {	new Point(25,25,-25), new Point(-25,25,25),
								new Point(25,25,25), new Point(-25,25,-25),
								new Point(25,-25,-25), new Point(-25,-25,25),
								new Point(25,-25,25), new Point(-25,-25,-25)};
					
		meshes.add(new Mesh(points, 50, 0, 0, 1, Color.blue, this));
		
		points = new Point[] {	new Point(0,25,10), new Point(0,25,-10),
								new Point(17.67,17.67,10), new Point(17.67,17.67,-10),
								new Point(25,0,10), new Point(25,0,-10),
								new Point(17.76,-17.67,10), new Point(17.67,-17.67,-10),
								new Point(0,-25,10), new Point(0,-25,-10),
								new Point(-17.67,17.67,10), new Point(-17.67,17.67,-10),
								new Point(-25,0,10), new Point(-25,0,-10),
								new Point(-17.76,-17.67,10), new Point(-17.67,-17.67,-10),};
					
		meshes.add(new Mesh(points, -50, 0, 0, 1, Color.green, this));
		
		points = new Point[] {	new Point(1,1,1), new Point(-2,2,2),
								new Point(3,3,3), new Point(-4,4,-4),
								new Point(5,-5,-5), new Point(-6,-6,6),
								new Point(7,-7,7), new Point(-8,-8,-8)};
	
		meshes.add(new Mesh(points, -150, 0, 0, 1, Color.white, this));
	}
	
	public void startGThread() {
		
		gThread = new Thread(this);
		gThread.start();
	}
	
	public void run() {

		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		long timer = 0;
		long drawCount = 0;
		
		while (gThread != null) {
			
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			timer += (currentTime - lastTime);
			
			lastTime = currentTime;
			
			if (delta >= 1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			
			if (timer >= 1000000000) {
				activeFPS = (int) drawCount;
				drawCount = 0;
				timer = 0;
			}
		}
	}
	
	public void update() {
		
		if (keyH.leftKeyDown) {
			cameraX += Math.cos(cameraAngle) * 3;
			cameraZ -= Math.sin(cameraAngle) * 3;
		}
		if (keyH.rightKeyDown) {
			cameraX -= Math.cos(cameraAngle) * 3;
			cameraZ += Math.sin(cameraAngle) * 3;
		}
		if (keyH.upKeyDown) {
			cameraX += Math.cos(cameraAngle + Math.PI/2) * 3;
			cameraZ -= Math.sin(cameraAngle + Math.PI/2) * 3;
		}
		if (keyH.downKeyDown) {
			cameraX -= Math.cos(cameraAngle + Math.PI/2) * 3;
			cameraZ += Math.sin(cameraAngle + Math.PI/2) * 3;
		}
		if (keyH.turnLeftKeyDown) {
			cameraAngle -= .025;
		}
		if (keyH.turnRightKeyDown) {
			cameraAngle += .025;
		}
		
		for (int i = 0; i < meshes.size(); i++) {
			meshes.get(i).update();
		}
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform defaultTransform = g2.getTransform();
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
							RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setColor(Color.black);
		g2.fillRect(0, 0, Main.round(screenWidth), Main.round(screenHeight));
		
		g2.setColor(Color.white);
		g2.drawString(String.valueOf(activeFPS), 1, 12);
		
		g2.translate(screenWidth/2, screenHeight/2);
		
		for (int i = 0; i < meshes.size(); i++) {
			meshes.get(i).draw(g2);
		}
		
		g2.setTransform(defaultTransform);
		
		g2.dispose();
	}
}
