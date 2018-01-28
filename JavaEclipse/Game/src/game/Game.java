package game;

import java.util.Random;
import javax.swing.*;

import javafx.scene.transform.Rotate;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.Timer;
import processing.core.PApplet;


public class Game extends PApplet {
	float playerY;
	Timer t = new Timer();
	double speedy = 1.5;
	public enemy e = new enemy();
	enemy[] enemyList = new enemy[25];
	int count = 0;
	float y = 0;
	boolean jump = false;
	float velocity;
	float maxVelocity = 20;
	float rotationAmount;
	double speedDist = 0.025;
	int spawnDist = 2000;
	int score = 0;

	public void setup() {
		size(1000, 550);
        background(255,255,255);
	}

	public void draw() {
		background(255,255,255);
		//ground
		fill(255,255,255);
		rect(0,500,1000,2);
		//points
		fill(0,0,0);
		textSize(20);
		text(score,800,100);
		for (int i = 0; i < enemyList.length; i++) {
			if (enemyList[i] != null) {
				enemyList[i].drawEnemy();
				if (enemyList[i].x<-50) {
					enemyList[i] = null;
				}
			}
		}

		//player
		translate(100, y + 440);
		rotate(rotationAmount);
		fill(255,255,255);
		rect(0, 0, 20, 50);
		if (jump) {
			y -= velocity;
			rotationAmount += (2 * PI)  / (maxVelocity * 2);
            if(rotationAmount > (2 * PI) + (2 * PI)  / (maxVelocity * 2)) {rotationAmount = 0;}
			if (velocity > -maxVelocity) { velocity -= 1; }
			else {jump = false; y = 0;}
		}
	}
	
	public class enemy {
		float x = 1000;

		public enemy() {
			
		}
		
		public void drawEnemy() {
			x -= speedy;
			fill(0,0,0);
			rect(x,440,20,50);			
			if (x > 100 && x < 120) { 
				if (y>-50.0f) {System.out.println(y); System.exit(0);}
				else {score += 1;}
				}
		}
	}
	public static void main(String _args[]) {
        PApplet.main(new String[] { game.Game.class.getName() });
    }
	
	public Game() {
	    Timer time = new Timer();
	    EnemySpawn enemySpawn = new EnemySpawn();
	    Speedy speedy = new Speedy();
	    time.schedule(speedy, 1 * 1000, 100);
	    time.schedule(enemySpawn, 1 * 1000, 2000);
	}
	
	class EnemySpawn extends TimerTask {
	    public void run() {
	      count++;
	      for (int i = 0; i < enemyList.length; i++) {
				if (enemyList[i] == null) {
					enemy pleb = new enemy();
					enemyList[i] = pleb;
					spawnDist -= 1000 * speedDist;
					break;
				}
			}
	    }
	}
	
	class Speedy extends TimerTask {
	    public void run() {
			if (speedy < 17) { speedy += speedDist; }
	    }
	}
	public void keyPressed() 
    {
        if (jump == false) { jump = true; velocity = maxVelocity; }
    }
}
