package funStuff;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class visuals extends Frame {
	public static List<Particle> particles = new ArrayList<>();
	static double forceConstant = .5;
	
    public static void main(String[] args) {
    	//double randomValue = ;
    	
    	double greenOnGreen = randomValue();
    	System.out.println(greenOnGreen);
    	double yellowOnGreen = randomValue();
    	System.out.println(yellowOnGreen);
    	double blueOnGreen = randomValue();
    	System.out.println(blueOnGreen);
    	double redOnGreen = randomValue();
    	System.out.println(redOnGreen);
    	
    	double greenOnYellow = randomValue();
    	System.out.println(greenOnYellow);
    	double yellowOnYellow = randomValue();
    	System.out.println(yellowOnYellow);
    	double blueOnYellow = randomValue();
    	System.out.println(blueOnYellow);
    	double redOnYellow = randomValue();
    	System.out.println(redOnYellow);
    	
    	double greenOnBlue = randomValue();
    	System.out.println(greenOnBlue);
    	double yellowOnBlue = randomValue();
    	System.out.println(yellowOnBlue);
    	double blueOnBlue = randomValue();
    	System.out.println(blueOnBlue);
    	double redOnBlue = randomValue();
    	System.out.println(redOnBlue);
    	
    	double greenOnRed = randomValue();
    	System.out.println(greenOnRed);
    	double yellowOnRed = randomValue();
    	System.out.println(yellowOnRed);
    	double blueOnRed = randomValue();
    	System.out.println(greenOnRed);
    	double redOnRed = randomValue();
    	System.out.println(redOnRed);
    	
    	//400 is the max you can get without inducing too much lag
    	for (int i = 0; i < 600; i++) {
    		particles.add(Particle.createRandomParticle(greenOnGreen, yellowOnGreen, blueOnGreen, redOnGreen, 'g'));
    		particles.add(Particle.createRandomParticle(greenOnYellow, yellowOnYellow, blueOnYellow, redOnYellow, 'y'));
    		particles.add(Particle.createRandomParticle(greenOnBlue, yellowOnBlue, blueOnBlue, redOnBlue, 'b'));
    		particles.add(Particle.createRandomParticle(greenOnRed, yellowOnRed, blueOnRed, redOnRed, 'r'));    		
    	}
        new visuals();
        while(true) {
        	try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
    
    public static double randomValue() {
    	return 2*(forceConstant * (Math.random()-.5));
    	//return -0.2011023416280407;
    }
    
	public void addParticle(Particle particle) {
		particles.add(particle);
	}
    
    public visuals() {
        setSize(1500, 800);
        setVisible(true);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        Thread loop = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    repaint();
                    try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
            }
        });
        loop.start();
    }
    
    private Image buffer;

    @Override
    public void update(Graphics g) {
        if (buffer == null) {
            buffer = createImage(getWidth(), getHeight());
        }
        Graphics bufferGraphics = buffer.getGraphics();

        // Clear and draw everything on the buffer
        bufferGraphics.clearRect(0, 0, getWidth(), getHeight());
        paint(bufferGraphics);

        // Draw the buffer onto the screen
        g.drawImage(buffer, 0, 0, this);
    }
    
    public void paint(Graphics g) {    	
    	g.clearRect(0, 0, getWidth(), getHeight());
    	g.drawRect(50,50,1353,703);
    	
    	for(Particle particle : particles) {
    		particle.moveX(particles);
    		particle.moveY(particles);
    		particle.draw(g);
    	}
    }
}