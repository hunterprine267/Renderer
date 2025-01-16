package entity;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import main.Main;

public class Point {

	double x, y, z;
	double rotx, roty, rotz;
	double origx, origy, origz;
	double px, py;
	double radius = 0;
	double angle = 0;
	public double multiplier = 0;
	
	public Point(double x, double y, double z) {
		setXYZ(x,y,z);
	}
	
	public void draw(Graphics2D g2) {
		
		Shape point = new Ellipse2D.Double(px - radius, py - radius, 2.0 * radius, 2.0 * radius);
		g2.fill(point);
	}
	
	public void update() {
		rotx = Main.rotatePoint(origx, origz, origx+x, origz+z, angle)[0];
		rotz = Main.rotatePoint(origx, origz, origx+x, origz+z, angle)[1];
		roty = origy+y;
		multiplier = 512 / (rotz);
		radius = 1 * multiplier;
		px = (rotx * multiplier);
		py = (roty * multiplier);
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
	
	public double getPX() {
		return px;
	}
	
	public double getPY() {
		return py;
	}
	
	public void setOrigX(double origx) {
		this.origx = origx;
	}
	
	public double getOrigX() {
		return origx;
	}
	
	public void setOrigY(double origy) {
		this.origy = origy;
	}
	
	public double getOrigY() {
		return origy;
	}

	public void setOrigZ(double origz) {
		this.origz = origz;
	}
	
	public double getOrigZ() {
		return origz;
	}
	
	public void setOrigXYZ(double origx, double origy, double origz) {
		this.origx = origx;
		this.origy = origy;
		this.origz = origz;
	}
	
	public void setAngle(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return angle;
	}
	
	public void setXYZ(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
}
