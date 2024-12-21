package funStuff;

import java.util.List;
import java.awt.*;

public class Particle {
	double x = 0;
	double y = 0;
	double xVel = 0;
	double yVel = 0;
	double gForce;
	double yForce;
	double bForce;
	double rForce;
	double friction = .6;
	double repulsiveForce = .5;
	char type;
	int forceConstant = 500;
	
	public Particle (double gForce, double yForce, double bForce, double rForce, char type) {
        this.gForce = gForce;
        this.yForce = yForce;
        this.bForce = bForce;
        this.rForce = rForce;
        this.type = type;
    }
	
	public static Particle createRandomParticle(double gForce, double yForce, double bForce, double rForce, char type) {
	    Particle p = new Particle(gForce, yForce, bForce, rForce, type);
	    p.x = Math.random() * 1400+50;
	    p.y = Math.random() * 750+50;
	    return p;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public char getType() {
		return type;
	}
	
	public double getDistance(Particle particle) {
		return Math.sqrt((particle.getX()-this.getX())*(particle.getX()-this.getX())+(particle.getY()-this.getY())*(particle.getY()-this.getY()));
	}
	
	public double getXDistance(Particle particle) {
		return x-particle.getX();
	}
	
	public double getYDistance(Particle particle) {
		return y-particle.getY();
	}
	
	public boolean isTouching(Particle particle) {
		if(getDistance(particle) < 2) {
			return true;
		}
		return false;
	}
	
	public double getNewXVel (List<Particle> particles) {
		long newXVel = 0;
		for(Particle particle : particles) {
			if(!this.isTouching(particle)) {
				if(particle.getType() == 'r') {
					newXVel += ((rForce * forceConstant)/(getDistance(particle)))*(getXDistance(particle)/getDistance(particle));
				} else if(particle.getType() == 'y') {
					newXVel += ((yForce * forceConstant)/(getDistance(particle)))*(getXDistance(particle)/getDistance(particle));
				} else if(particle.getType() == 'g') {
					newXVel += ((gForce * forceConstant)/(getDistance(particle)))*(getXDistance(particle)/getDistance(particle));
				} else {
					newXVel += ((bForce * forceConstant)/(getDistance(particle)))*(getXDistance(particle)/getDistance(particle));
				}
			} else {
				if (particle.getX() > x) {
					newXVel -= repulsiveForce;
				} else {
					newXVel += repulsiveForce;
				}
			}
		}
		return newXVel+xVel;
	}
	
	public double getNewYVel (List<Particle> particles) {
		long newYVel = 0;
		for(Particle particle : particles) {
			if(!this.isTouching(particle)) {
				if(particle.getType() == 'r') {
					newYVel += ((rForce * forceConstant)/(getDistance(particle)))*(getYDistance(particle)/getDistance(particle));
				} else if(particle.getType() == 'y') {
					newYVel += ((yForce*forceConstant)/(getDistance(particle)))*(getYDistance(particle)/getDistance(particle));
				} else if(particle.getType() == 'g') {
					newYVel += ((gForce*forceConstant)/(getDistance(particle)))*(getYDistance(particle)/getDistance(particle));
				} else {
					newYVel += ((bForce*forceConstant)/(getDistance(particle)))*(getYDistance(particle)/getDistance(particle));
				}
			} else {
				if (particle.getY() > y) {
					newYVel -= repulsiveForce;
				} else {
					newYVel += repulsiveForce;
				}
			}
		}
		return newYVel+yVel;	
	}
	
	public void moveX(List<Particle> particles) {
	    xVel = getNewXVel(particles);
	    xVel *= friction;

	    if ((x >= 50 && x <= 1450) || (x < 50 && xVel > 0) || (x > 1450 && xVel < 0)) {
	        x += xVel;
	    }

	    x = Math.max(50, Math.min(x, 1400));
	}

	public void moveY(List<Particle> particles) {
	    yVel = getNewYVel(particles);
	    yVel *= friction;

	    if ((y >= 50 && y <= 750) || (y < 50 && yVel > 0) || (y > 750 && yVel < 0)) {
	        y += yVel;
	    }

	    y = Math.max(50, Math.min(y, 750));
	}
	
	public void draw(Graphics g) {		
		if(type == 'r') {
			g.setColor(Color.RED);
		} else if (type == 'g') {
			g.setColor(Color.GREEN);
		} else if (type == 'y') {
			g.setColor(Color.YELLOW);
		} else if (type == 'b') {
			g.setColor(Color.BLUE);
		}
		g.fillOval((int) x, (int) y, 4, 4);
	}
}