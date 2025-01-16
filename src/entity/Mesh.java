package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;

import main.GraphicPanel;
import main.Main;

public class Mesh {
	
	double x, y, z;
	double rotx, roty, rotz;
	Point[] points;
	Color color;
	double angle = 0;
	double scale = 1;
	
	GraphicPanel gp;

	public Mesh(Point[] points, double x, double y, double z, double scale, Color color, GraphicPanel gp) {
		this.points = points;
		setXYZ(x,y,z);
		this.color = color;
		this.gp = gp;
		this.scale = scale;
		
		for (int i = 0; i < points.length; i++) {
			Point point = points[i];
			point.setXYZ(point.getX() * scale, point.getY() * scale, point.getZ() * scale);
		}
	}

	public void draw(Graphics2D g2) {
		g2.setColor(color);
		
		for (int i = 0; i < points.length; i++) {
			for (int j = 0; j < points.length; j++) {
				try {
					Shape line = new Line2D.Double(points[i].getPX(), points[i].getPY(), points[j].getPX(), points[j].getPY());
					g2.draw(line); 
				} catch (Exception e) {}
			}
			points[i].draw(g2);
		}
	}
	
	public void update() {
		angle += .01;
		rotx = Main.rotatePoint(-gp.cameraX, -gp.cameraZ, x, z, gp.cameraAngle)[0];
		rotz = Main.rotatePoint(-gp.cameraX, -gp.cameraZ, x, z, gp.cameraAngle)[1];
		roty = y;
		
		for (int i = 0; i < points.length; i++) {
			points[i].setAngle(angle + gp.cameraAngle);
			points[i].setOrigXYZ(rotx + gp.cameraX, roty, rotz + gp.cameraZ);
			points[i].update();
		}
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public double getX() {
		return x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getY() {
		return y;
	}

	public void setZ(double z) {
		this.z = z;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setXYZ(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
