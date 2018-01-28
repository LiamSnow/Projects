package drawingprogram;

import java.awt.Toolkit;
import java.awt.event.MouseWheelEvent;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import javax.media.nativewindow.util.Dimension;
import javax.swing.SwingUtilities;

import com.jogamp.newt.event.KeyEvent;
import com.jogamp.newt.event.MouseEvent;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;


public class DrawingProgram extends PApplet {
	
	float lastX;
	float lastY;
	
	boolean useLastMouse;
	
	boolean wheelMoving;
	boolean wheelForward;
	
	float[] brushColor = {0,0,0,255};
	
	int wheelNotches;
	
	String brushedColor;
	String[] splitStr = new String[2];
	
	boolean inText;
	boolean inSize;
	boolean inColor;
	
	boolean clicked;
	boolean topClicked;
	boolean topClickedR;
	boolean topMiddleClick;
	boolean mainMiddleClick;
	
	float[] multi1 = {255,0,0};
	float[] multi2 = {0,255,0};
	float[] multi3 = {0,0,255};
	boolean M1active = true;
	boolean M2active = true;
	boolean M3active = true;
	
 	boolean paintSelected;
	boolean pencilSelected = true;
	boolean gradeSelected;
	boolean multiSelected;
	boolean eraseSelected;
	
	boolean resetingSelected;
	
	float colorR;
	float colorG;
	float colorB;
	float Rdist;
	float Gdist;
	float Bdist;
	double gradeSpeed = 0.01;
	boolean gradeForward = true;
	
	float[] gradeBrushOne = {0,0,255};
	float[] gradeBrushTwo = {0,255,0};
	
	PImage pencil;
	PImage bucket;
	PImage gradi;
	PImage multI;
	PImage eraseT;
	
	float[] baseColor = {255,255,230};
	
	int size = 10;
	String STS = new String("10");

	java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int fullW = (int) screenSize.getWidth();
	int fullH = (int) screenSize.getHeight();
	
	public void setup() {
		
		size(fullW,fullH);
		background(baseColor[0],baseColor[1],baseColor[2]);
		pencil = loadImage("pencil.png");
		bucket = loadImage("Paint_Bucket-512.png");
		gradi  = loadImage("pencilGrad.png");
		multI  = loadImage("multiPencil.png");
		eraseT = loadImage("eraseT.png");
		gradeBrushDist();
		frameRate(60);
		setVisible(true);
	}

	public void draw() {
		drawingControls();
		drawTop();
		topClicked();
		topClickedR();
		topMiddleClick();
		mainMiddleClick();
		maxColor();
		scrollyStuff();
		sizeMin();
	}
	
	public void mousePressed(java.awt.event.MouseEvent e) {
 
		if (SwingUtilities.isLeftMouseButton(e)) {	
			if (mouseY > height/8) {clicked = true;}
			else {topClicked = true;}
		}
		else if (SwingUtilities.isRightMouseButton(e)) {
			if (mouseY > height/8) {}
			else {topClickedR = true;}
		}
		else if (SwingUtilities.isMiddleMouseButton(e)) {
			if (mouseY > height/8) {mainMiddleClick = true;}
			else {topMiddleClick = true;}
		}
		if (inText) {exitST();}
	}
	
	public void mouseReleased() {
		clicked = false;
		topClicked = false;
		topClickedR = false;
		topMiddleClick = false;
		useLastMouse = false;
		mainMiddleClick = false;
	}
	
	public void mouseWheelMoved(MouseWheelEvent e) {
		wheelMoving = true;
		wheelNotches = e.getWheelRotation();
		if (wheelNotches < 0) {wheelForward = true;}
		else {wheelForward = false;}
	}
	
	public void topClicked() {
		if (topClicked) {	
			if (mouseX >= 10 && mouseX <= width/20 && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] += 1;
			}
			
			if (mouseX >= 30 + width/20 && mouseX <= 30 + (2 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[1] += 1;
			}
			
			if (mouseX >= 50 + 2 * (width/20) && mouseX <= 50 + (3 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[2] += 1;
			}
			
			if (mouseX >= 70 + 3 * (width/20) && mouseX <= 70 + (4 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] -= 1;
				brushColor[1] -= 1;
				brushColor[2] -= 1;
			}
	
			if (mouseX >= 90 + 4 * (width/20) && mouseX <= 90 + (5 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] += 1;
				brushColor[1] += 1;
				brushColor[2] += 1;
			}
			
			if (mouseX >= 130 + (6 * (width/20)) && mouseX <= 130 + (7 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[3] += 1;
			}
			
			if (mouseX >= width - 30 - (width/20) && mouseX <= (width - 30 - (width/20)) + width/20 && mouseY >= 10 && mouseY <= height/10) {
				noStroke();
				fill(baseColor[0],baseColor[1],baseColor[2]);
				rect(0,height/8 + 2,width,height - height/8);
			}
			
			if (mouseX >= width - 60 - 2 * (width/30) && mouseX <= (width - 60 - 2 * (width/30)) + width/50 && mouseY >= 10 && mouseY <= height/10) {
				baseColor[0] = brushColor[0];
				baseColor[1] = brushColor[1];
				baseColor[2] = brushColor[2];
			}
			if (mouseX >= width - 60 - 3 * (width/30) && mouseX <= (width - 60 - 3 * (width/30)) + width/50 && mouseY >= 10 && mouseY <= height/25) {
				resetSelected();
				if (!resetingSelected) {paintSelected = true;}
			}
			if (mouseX >= width - 60 - 3 * (width/30) && mouseX <= (width - 60 - 3 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
				resetSelected();
				if (!resetingSelected) {pencilSelected = true;}
			}
			if (mouseX >= width - 60 - 4 * (width/30) && mouseX <= (width - 60 - 4 * (width/30)) + width/50 && mouseY >= 10 && mouseY <= height/25) {
				resetSelected();
				if (!resetingSelected) {gradeSelected = true;}
			}
			if (gradeSelected) {	
				if (mouseX >= width - 60 - 5 * (width/30) && mouseX <= (width - 60 - 5 * (width/30)) + width/50 && mouseY >= 10 && mouseY <= height/25) {
					gradeBrushOne[0] = brushColor[0];
					gradeBrushOne[1] = brushColor[1];
					gradeBrushOne[2] = brushColor[2];
					gradeBrushDist();
				}
				if (mouseX >= width - 60 - 6 * (width/30) && mouseX <= (width - 60 - 6 * (width/30)) + width/50 && mouseY >= 10 && mouseY <= height/25) {
					gradeBrushTwo[0] = brushColor[0];
					gradeBrushTwo[1] = brushColor[1];
					gradeBrushTwo[2] = brushColor[2];
					gradeBrushDist();
				}
			}
			if (mouseX >= width - 60 - 4 * (width/30) && mouseX <= (width - 60 - 4 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
				resetSelected();
				if (!resetingSelected) {multiSelected = true;}
			}
			if (multiSelected) {	
				if (mouseX >= width - 60 - 5 * (width/30) && mouseX <= (width - 60 - 5 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
					M1active = true;
					multi1[0] = brushColor[0];
					multi1[1] = brushColor[1];
					multi1[2] = brushColor[2];
				}
				if (mouseX >= width - 60 - 6 * (width/30) && mouseX <= (width - 60 - 6 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
					M2active = true;
					multi2[0] = brushColor[0];
					multi2[1] = brushColor[1];
					multi2[2] = brushColor[2];
				}
				if (mouseX >= width - 60 - 7 * (width/30) && mouseX <= (width - 60 - 7 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
					M3active = true;
					multi3[0] = brushColor[0];
					multi3[1] = brushColor[1];
					multi3[2] = brushColor[2];
				}
			}
			if (!gradeSelected) {		
				if (mouseX >= width - 60 - 5 * (width/30) && mouseX <= (width - 60 - 4 * (width/30)) + width/50 && mouseY >= 10 && mouseY <= height/25) {
					resetSelected();
					if (!resetingSelected) {eraseSelected = true;}
				}
			}
			
			if (mouseX >= 145 + (7 * (width/20)) && mouseX <= 145 + (7 * (width/20)) + brushedColor.length()*12.5f && mouseY >= 80 && mouseY <= 80 + 25) {
				inColor = true;
				inText = true;
				brushedColor = "";
			}
			
			if (mouseX >= 145 + (7 * (width/20)) && mouseX <= 145 + (7 * (width/20)) + (STS.length()) * 17.5f && mouseY >= 29 && mouseY <= 29 + 25) {
				inSize = true;
				inText = true;
				STS = "";
			}
		}
	}
	
	public void topClickedR() {
		if (topClickedR) {	
			if (mouseX >= 10 && mouseX <= width/20 && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] -= 1;
			}
			
			if (mouseX >= 130 + (6 * (width/20)) && mouseX <= 130 + (7 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[3] -= 1;
			}
			
			if (mouseX >= 30 + width/20 && mouseX <= 30 + (2 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[1] -= 1;
			}
			
			if (mouseX >= 50 + 2 * (width/20) && mouseX <= 50 + (3 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[2] -= 1;
			}
			
			if (mouseX >= 70 + 3 * (width/20) && mouseX <= 70 + (4 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] += 1;
				brushColor[1] += 1;
				brushColor[2] += 1;
			}
	
			if (mouseX >= 90 + 4 * (width/20) && mouseX <= 90 + (5 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] -= 1;
				brushColor[1] -= 1;
				brushColor[2] -= 1;
			}
			if (mouseX >= width - 60 - 3 * (width/30) && mouseX <= (width - 60 - 3 * (width/30)) + width/50 && mouseY >= 10 && mouseY <= height/25) {
				paintSelected = false;
			}
			if (mouseX >= width - 60 - 3 * (width/30) && mouseX <= (width - 60 - 3 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
				pencilSelected = false;
			}
			if (mouseX >= width - 60 - 4 * (width/30) && mouseX <= (width - 60 - 4 * (width/30)) + width/50 && mouseY >= 10 && mouseY <= height/25) {
				gradeSelected = false;
			}
			if (mouseX >= width - 60 - 4 * (width/30) && mouseX <= (width - 60 - 4 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
				multiSelected = false;
			}
			if (multiSelected) {	
				if (mouseX >= width - 60 - 5 * (width/30) && mouseX <= (width - 60 - 5 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
					M1active = false;
				}
				if (mouseX >= width - 60 - 6 * (width/30) && mouseX <= (width - 60 - 6 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
					M2active = false;
				}
				if (mouseX >= width - 60 - 7 * (width/30) && mouseX <= (width - 60 - 7 * (width/30)) + width/50 && mouseY >= 30 + height/25 && mouseY <= 30 + height/25 + height/25) {
					M3active = false;
				}
			}
			if (!gradeSelected) {	
				if (mouseX >= width - 60 - 5 * (width/30) && mouseX <= (width - 60 - 4 * (width/30)) + width/50 && mouseY >= 10 && mouseY <= height/25) {
					eraseSelected = false;
				}
			}
		}
	}
	
	public void maxColor() {
		if (brushColor[0] > 255) {brushColor[0] = 255;}
		if (brushColor[1] > 255) {brushColor[1] = 255;}
		if (brushColor[2] > 255) {brushColor[2] = 255;}
		if (brushColor[0] < 0) {brushColor[0] = 0;}
		if (brushColor[1] < 0) {brushColor[1] = 0;}
		if (brushColor[2] < 0) {brushColor[2] = 0;}
		
		if (size > 999999) {size = 999999; STS = "999999";}
	}
	
	public void drawTop() {
		strokeWeight(2);
		stroke(40,40,40);
		fill(20,20,20);
		rect(0,0,width,height/8);
		
		strokeWeight(5);
		stroke(150,0,0);
		fill(255,0,0);
		rect(10,10,width/20,height/10);
		
		strokeWeight(5);
		stroke(0,150,0);
		fill(0,255,0);
		rect(30 + width/20,10,width/20,height/10);
		
		strokeWeight(5);
		stroke(0,0,150);
		fill(0,0,255);
		rect(50 + (2 * (width/20)),10,width/20,height/10);
		
		strokeWeight(5);
		stroke(30,30,30);
		fill(0,0,0);
		rect(70 + (3 * (width/20)),10,width/20,height/10);
		
		strokeWeight(5);
		stroke(190,190,190);
		fill(255,255,255);
		rect(90 + (4 * (width/20)),10,width/20,height/10);
		
		strokeWeight(5);
		stroke(190,190,190);
		fill(brushColor[0],brushColor[1],brushColor[2],brushColor[3]);
		rect(130 + (6 * (width/20)),10,width/20,height/10);
		
		
		strokeWeight(2);
		noFill();
		stroke(40,40,40);
		if (!inColor) {brushedColor = Math.round(brushColor[0]) + " " + Math.round(brushColor[1]) + " " + Math.round(brushColor[2]);}
		rect(145 + (7 * (width/20)),80,brushedColor.length()*12.5f,25);
		rect(145 + (7 * (width/20)),29,(STS.length()) * 15,25);
		
		strokeWeight(5);
		fill(255,255,255);
		textSize(20);
		text(brushedColor, 150 + (7 * (width/20)),100);
		text(STS,150 + (7 * (width/20)),50);
		
		strokeWeight(5);
		stroke(190,190,190);
		fill(100,100,100);
		rect(width - 30 - (width/20),10,width/20,height/10);
		
		strokeWeight(3);
		stroke(150,0,0);
		line(width - 30 - (width/20) + 5,10 + 5,width - 30 - (width/20) + width/20 - 5,10 + height/10 - 5);
		line(width - 30 - (width/20) + width/20 - 5,10 + 5,width - 30 - (width/20) + 5,10 + height/10 - 5);
		
		strokeWeight(5);
		stroke(190,190,190);
		fill(100,100,100);
		rect(width - 60 - 2 * (width/30),10,width/50,height/10);
		
		fill(255,255,255);
		textSize(15);
		text("B\na\nc\nk",width - 45 - 2 * (width/30),30);
		
		strokeWeight(5);
		stroke(190,190,190);
		if (paintSelected) {fill(100,100,200);} else {fill(100,100,100);}
		rect(width - 60 - 3 * (width/30),10,width/50,height/25);
		if (pencilSelected) {fill(100,100,200);} else {fill(100,100,100);}
		rect(width - 60 - 3 * (width/30),30 + height/25,width/50,height/25);
		if (gradeSelected) {fill(100,100,200);} else {fill(100,100,100);}
		rect(width - 60 - 4 * (width/30),10,width/50,height/25);
		if (multiSelected) {fill(100,100,200);} else {fill(100,100,100);}
		rect(width - 60 - 4 * (width/30),30 + height/25,width/50,height/25);
		if (gradeSelected) {	
			fill(gradeBrushOne[0],gradeBrushOne[1],gradeBrushOne[2]);
			rect(width - 60 - 5 * (width/30),10,width/50,height/25);
			fill(gradeBrushTwo[0],gradeBrushTwo[1],gradeBrushTwo[2]);
			rect(width - 60 - 6 * (width/30),10,width/50,height/25);
		}
		if (multiSelected) {	
			if (M1active) {fill(multi1[0],multi1[1],multi1[2]);} else {fill(20,20,20);}
			rect(width - 60 - 5 * (width/30),30 + height/25,width/50,height/25);
			if (M2active) {fill(multi2[0],multi2[1],multi2[2]);} else {fill(20,20,20);}
			rect(width - 60 - 6 * (width/30),30 + height/25,width/50,height/25);
			if (M3active) {fill(multi3[0],multi3[1],multi3[2]);} else {fill(20,20,20);}
			rect(width - 60 - 7 * (width/30),30 + height/25,width/50,height/25);
		}
		if (!gradeSelected) {	
			if (eraseSelected) {fill(100,100,200);} else {fill(100,100,100);}
			rect(width - 60 - 5 * (width/30),10,width/50,height/25);
			image(eraseT,width - 57 - 5 * (width/30),15,0.0171875f * width,0.03055555555f * height);
		}
		image(pencil, width - 60 - 3 * (width/30),32 + height/25,0.0203125f * width,0.03611111111f * height);
		image(bucket, width - 60 - 3 * (width/30),12,            0.0203125f * width,0.03611111111f * height);
		image(gradi,  width - 60 - 4 * (width/30),12,            0.0203125f * width,0.03611111111f * height);
		image(multI,  width - 60 - 4 * (width/30),32 + height/25,0.0203125f * width,0.03611111111f * height);
	}
	
	public void topMiddleClick() {
		if (topMiddleClick) {	
			if (mouseX >= 10 && mouseX <= width/20 && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] = 255;
				brushColor[1] = 0;
				brushColor[2] = 0;
			}
			
			if (mouseX >= 30 + width/20 && mouseX <= 30 + (2 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] = 0;
				brushColor[1] = 255;
				brushColor[2] = 0;
			}
			
			if (mouseX >= 50 + 2 * (width/20) && mouseX <= 50 + (3 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] = 0;
				brushColor[1] = 0;
				brushColor[2] = 255;
			}
			
			if (mouseX >= 70 + 3 * (width/20) && mouseX <= 70 + (4 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] = 0;
				brushColor[1] = 0;
				brushColor[2] = 0;
			}
	
			if (mouseX >= 90 + 4 * (width/20) && mouseX <= 90 + (5 * (width/20)) && mouseY >= 10 && mouseY <= height/10) {
				brushColor[0] = 255;
				brushColor[1] = 255;
				brushColor[2] = 255;
			}
		}
	}
	
	public void mainMiddleClick() {
		if (mainMiddleClick) {
			brushColor[0] = red  (get(mouseX,mouseY));
			brushColor[1] = green(get(mouseX,mouseY));
			brushColor[2] = blue (get(mouseX,mouseY));
		}
	}
	
	public void resetSelected() {
		resetingSelected = true;
		paintSelected = false;
		pencilSelected = false;
		gradeSelected = false;
		multiSelected = false;
		eraseSelected = false;
		resetingSelected = false;
	}
	
	public void gradeBrushDist() {
		colorR = gradeBrushOne[0];
		colorG = gradeBrushOne[1];
		colorB = gradeBrushOne[2];
		Rdist =  gradeBrushTwo[0] - gradeBrushOne[0];
		Gdist =  gradeBrushTwo[1] - gradeBrushOne[1];
		Bdist =  gradeBrushTwo[2] - gradeBrushOne[2];
		gradeForward = true;
	}
	
	public void drawingControls() {
		if (clicked) {
			if (useLastMouse) {	
				if (pencilSelected) {	
					noFill();
					stroke(brushColor[0],brushColor[1],brushColor[2],brushColor[3]);
					strokeWeight(size);
					line(lastX,lastY,mouseX,mouseY); 
					}
				if (gradeSelected) {	
					noFill();
					stroke(colorR,colorG,colorB);
					strokeWeight(size);
					line(lastX,lastY,mouseX,mouseY);
					if (gradeForward) {	
						colorR += (Rdist * gradeSpeed);
						colorG += (Gdist * gradeSpeed);
						colorB += (Bdist * gradeSpeed);
					}
					else {
						colorR -= (Rdist * gradeSpeed);
						colorG -= (Gdist * gradeSpeed);
						colorB -= (Bdist * gradeSpeed);
					}
					if (colorR < 0 || colorR > 255 || colorG < 0 || colorG > 255 || colorB < 0 || colorB > 255) {
						gradeForward = !gradeForward;
					}
					}
				if (multiSelected) {
					float MD = size;
						if (M3active) {
							noFill();
							stroke(multi3[0],multi3[1],multi3[2]);
							strokeWeight(size);
							line(lastX,lastY - MD,mouseX,mouseY - MD); 
						}
						
						if (M2active) {
							noFill();
							stroke(multi2[0],multi2[1],multi2[2]);
							strokeWeight(size);
							line(lastX,lastY,mouseX,mouseY); 
						}
						
						if (M1active) {
							noFill();
							stroke(multi1[0],multi1[1],multi1[2]);
							strokeWeight(size);
							line(lastX,lastY + MD,mouseX,mouseY + MD); 
						}
				}
				if (eraseSelected) {
					noFill();
					stroke(baseColor[0],baseColor[1],baseColor[2],brushColor[3]);
					strokeWeight(size);
					line(lastX,lastY,mouseX,mouseY); 
				}
				if (paintSelected) {
					noFill();
					stroke(mouseX / width,mouseX / width,mouseY / height);
					strokeWeight(size);
					line(lastX,lastY,mouseX,mouseY); 
				} 
			}
			useLastMouse = true;
			lastX = mouseX;
			lastY = mouseY;
		}
	}
	
	public void keyPressed(java.awt.event.KeyEvent e) {
		
	    int key = e.getKeyCode();

	    if (key == KeyEvent.VK_0) {
	        if (inText && inSize) {if (STS.length() < 6) {STS += "0";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "0";}}
	    }
	    if (key == KeyEvent.VK_1) {
	    	if (inText && inSize) {if (STS.length() < 6) {STS += "1";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "1";}}
	    }
	    if (key == KeyEvent.VK_2) {
	    	if (inText && inSize) {if (STS.length() < 6) {STS += "2";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "2";}}
	    }
	    if (key == KeyEvent.VK_3) {
	    	if (inText && inSize) {if (STS.length() < 6) {STS += "3";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "3";}}
	    }
	    if (key == KeyEvent.VK_4) {
	    	if (inText && inSize) {if (STS.length() < 6) {STS += "4";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "4";}}
	    }
	    if (key == KeyEvent.VK_5) {
	    	if (inText && inSize) {if (STS.length() < 6) {STS += "5";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "5";}}
	    }
	    if (key == KeyEvent.VK_6) {
	    	if (inText && inSize) {if (STS.length() < 6) {STS += "6";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "6";}}
	    }
	    if (key == KeyEvent.VK_7) {
	    	if (inText && inSize) {if (STS.length() < 6) {STS += "7";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "7";}}
	    }
	    if (key == KeyEvent.VK_8) {
	    	if (inText && inSize) {if (STS.length() < 6) {STS += "8";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "8";}}
	    }
	    if (key == KeyEvent.VK_9) {
	    	if (inText && inSize) {if (STS.length() < 6) {STS += "9";}}
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += "9";}}
	    }
	    if (key == KeyEvent.VK_SPACE) {
	        if (inText && inColor) {if (brushedColor.length() < 11) {brushedColor += " ";}}
	    }
	    if (key == KeyEvent.VK_ESCAPE) {
	    	if (inText) {exitST();}
	    }
	    if (key == KeyEvent.VK_DELETE || key == KeyEvent.VK_BACK_SPACE) {
	    	if (inText && inSize) {if (STS.length() > 0) {
	    		StringBuilder sb = new StringBuilder(STS);
	            sb.deleteCharAt(STS.length() - 1);
	            STS = sb.toString();
	    	}}
	        if (inText && inColor) {if (brushedColor.length() > 0) {
	        	StringBuilder sb = new StringBuilder(brushedColor);
	            sb.deleteCharAt(brushedColor.length() - 1);
	            brushedColor = sb.toString();
	        }}
	    }
	}
	
	public void exitST() {
		inText = false; 
    	inSize = false;
    	if (STS != null && !STS.isEmpty()) {size = Integer.parseInt(STS);}
    	else {STS = Integer.toString(size);}
		splitStr = brushedColor.trim().split("\\s+");
		splitStr = Arrays.copyOfRange(splitStr, 0, 3);
    	for (int i = 0; i <= 2; i++) {	
			if (splitStr[i] != null && !splitStr[i].isEmpty() && splitStr[i] != "" && splitStr[i] != " ") {
					float tempCI = Integer.parseInt(splitStr[i]);
					if (tempCI > 255) {tempCI = 255;}
					if (tempCI < 0) {tempCI = 0;}
					brushColor[i] = tempCI;
					inColor = false;
			}
			else {
	    		i = 999; inColor = false; brushedColor = Math.round(brushColor[0]) + " " + Math.round(brushColor[1]) + " " + Math.round(brushColor[2]);
	    	}
    	}
    	inColor = false;
	}
	
	public void scrollyStuff() {
		if (wheelMoving) {
			if (wheelForward) {size += 5; wheelMoving = false; STS = Integer.toString(size);}
			else {
				if (size > 0) { size -= 5; STS = Integer.toString(size);}
				wheelMoving = false;
				}
		}
	}

	public void sizeMin() {
		  
	}
}
