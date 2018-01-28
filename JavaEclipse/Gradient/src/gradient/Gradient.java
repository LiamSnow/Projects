package gradient;

import processing.core.PApplet;

import java.util.Arrays;
import java.util.Random;

public class Gradient extends PApplet {
	int width = 3840;
	int height = 2160;

	boolean randomColor = true;
	boolean Sdraw = !randomColor;
	
	float[] color1 = {139,0,139};
	float[] color2 = {0,255,0};
	
	float colorR = color1[0];
	float colorG = color1[1];
	float colorB = color1[2];
	float Rdist  = color2[0];
	float Gdist  = color2[1];
	float Bdist  = color2[2];
	
	int y = 0;
	
	float speed = 1f;
	
	public void setup() {
		size(width,height);
        background(255,255,255);
        
	    if (randomColor) {	
        	Random rand = new Random();
	    	int colorser = rand.nextInt(255) + 0;
	    	color1[0] = colorser;
	    	
	    	rand = new Random();
	    	colorser = rand.nextInt(255) + 0;
	    	color1[1] = colorser;
	    	
	    	rand = new Random();
	    	colorser = rand.nextInt(255) + 0;
	    	color1[2] = colorser;
	    	
	    	rand = new Random();
	    	colorser = rand.nextInt(255) + 0;
	    	color2[0] = colorser;
	    	
	    	rand = new Random();
	    	colorser = rand.nextInt(255) + 0;
	    	color2[1] = colorser;
	    	
	    	rand = new Random();
	    	colorser = rand.nextInt(255) + 0;
	    	color2[2] = colorser;	    	
	    	}
	    
	    Sdraw = true;
	    
	    colorR = color1[0];
		colorG = color1[1];
		colorB = color1[2];
		Rdist = color2[0] - color1[0];
		Gdist = color2[1] - color1[1];
		Bdist = color2[2] - color1[2];
	}

	public void draw() {
		if (Sdraw && y < height) {
			main();																																																																							main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();		main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();		main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();		main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();		main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();			main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();main();
		} 
	}
	
	public void mousePressed() {
		restart();
	}
	public void keyPressed() {
		restart();
	}
	
	public void main() {
		noStroke();
		fill(colorR,colorG,colorB);
		rect(0,y,width,speed);
		colorR += ((Rdist / height) * speed);
		colorG += ((Gdist / height) * speed);
		colorB += ((Bdist / height) * speed);
		y += speed;
	}
	
	public void restart() {
		Sdraw = false;
		y = 0;
		
		Random rand = new Random();
    	int colorser = rand.nextInt(255) + 0;
    	color1[0] = colorser;
    	
    	rand = new Random();
    	colorser = rand.nextInt(255) + 0;
    	color1[1] = colorser;
    	
    	rand = new Random();
    	colorser = rand.nextInt(255) + 0;
    	color1[2] = colorser;
    	
    	rand = new Random();
    	colorser = rand.nextInt(255) + 0;
    	color2[0] = colorser;
    	
    	rand = new Random();
    	colorser = rand.nextInt(255) + 0;
    	color2[1] = colorser;
    	
    	rand = new Random();
    	colorser = rand.nextInt(255) + 0;
    	color2[2] = colorser;	    	
    
		Sdraw = true;
    
    	colorR = color1[0];
		colorG = color1[1];
		colorB = color1[2];
		Rdist = color2[0] - color1[0];
		Gdist = color2[1] - color1[1];
		Bdist = color2[2] - color1[2];
	}
}


