package first_matlab_test;

import processing.core.*;
import matlabcontrol.internal.*;
import matlabcontrol.extensions.*;
import matlabcontrol.*;

public class MainSystem extends PApplet {
	float[] x = new float[20];
	float[] y = new float[20];
	float segLength = 8;
	float hX;
	float hY;
	float b_hX;
	float b_hY;

	float[] hdx = new float[100];
	float[] hdy = new float[100];

	float hAngle;
	boolean[] keys;
	long lastKey[] = new long[2];
	long lastKey2[] = new long[2];
	float velo;

	int count;
	int mflag; // 평상시가 1, 점프했을때 2,
	PImage head;
	PImage body;
	PImage back;
	MatlabController mc;

	// Enemy e = new Enemy(0,220);

	Enemy e[] = new Enemy[4];
	AirE a[] = new AirE[4];
	UnderwaterE b[] = new UnderwaterE[4];
	float timer;
	int kill;
	int numofboats;
	int numofbirds;
	int numofscubas;
	int points;

	public void setup() {
		imageMode(CENTER);
		mc = new MatlabController();
		size(1200, 700);
		hX = width / 2;
		hY = height - 50;
		head = loadImage("data\\header_r.png");
		body = loadImage("data\\body_r.png");
		back = loadImage("data\\lake image2.png");

		count = 0;
		mflag = 1;
		velo = 2;
		kill = 0;
		numofboats = 0;
		numofbirds = 0;
		numofscubas = 0;
		points = 0;
		timer = 30;

		e[0] = new WaterSurfE(0, 0);
		e[1] = new WaterSurfE(-100, 0);
		e[2] = new WaterSurfE(width + 35, 1);
		e[3] = new WaterSurfE(width + 100, 1);

		a[0] = new AirE(0, 0);
		a[1] = new AirE(0, 0);
		a[2] = new AirE(width + 20, 1);
		a[3] = new AirE(width + 100, 1);

		b[0] = new UnderwaterE(-70, 0);
		b[1] = new UnderwaterE(-120, 0);
		b[2] = new UnderwaterE(width + 70, 1);
		b[3] = new UnderwaterE(width + 100, 1);

		keys = new boolean[4];
		for (int i = 0; i < 4; i++) {
			keys[i] = false;
		}
	}

	public void draw() {
		background(back);
		thread("emove");
		thread("collision");
		if (mflag == 1) {
			moving();
			if (hX < 10) {
				hX = 13;
			} else if (hX > width - 10) {
				hX = width - 13;
			}
			if (hY > height - 23) {
				hY = height - 26;
			}
			if (hY < 250) {
				hY = 257;
				mflag <<= 1;
				measure();
			}
		}

		if (mflag == 4) {
			if (count != hdx.length) {
				hX = b_hX + hdx[count];
				hY = b_hY - hdy[count];
				count++;
			} else {
				lastKey[0] = -(~lastKey[0]);
				mflag >>= 2;
				count = 0;
			}
		}

		dragSegment(0, hX, hY, head);
		for (int i = 0; i < x.length - 1; i++) {
			dragSegment(i + 1, x[i], y[i], body);
		}
		for (int i = 0; i < e.length; i++) {
			image(e[i].img, e[i].x, e[i].y, 70, 40);
			image(a[i].img, a[i].x, a[i].y, 40, 35);
			image(b[i].img, b[i].x, b[i].y, 50, 40);
		}
		//timer = 30 - millis() / 1000;
		textSize(32);
		text("Remaining Time:" + (timer-millis()/1000), 20, 50);
		text("Number of kills:" + kill, 20, 75);

		if ((timer-millis()/1000) <= 0) {
			endofGame();
		}
	}

	public void emove() {
		for (int i = 0; i < e.length; i++) {
			e[i].move();
			a[i].move();
			b[i].move();
		}

	}

	public void collision() {
		for (int i = 0; i < e.length; i++) {
			if (hX >= e[i].x - 35 && hX <= e[i].x + 35 && hY <= e[i].y + 20
					&& hY >= e[i].y - 20) {
				e[i].reset();
				kill++;
				numofboats++;
				points = points + 200;
			}
			if (hX >= a[i].x - 20 && hX <= a[i].x + 20 && hY <= a[i].y + 18
					&& hY >= a[i].y - 18) {
				a[i].reset();
				kill++;
				numofbirds++;
				points = points + 350;
			}
			if (hX >= b[i].x - 20 && hX <= b[i].x + 20 && hY <= b[i].y + 18
					&& hY >= b[i].y - 18) {
				b[i].reset();
				kill++;
				numofscubas++;
				points = points + 50;
			}
		}
	}

	public void dragSegment(int i, float xin, float yin, PImage pi) {
		float dx = xin - x[i];
		float dy = yin - y[i];
		float angle = atan2(dy, dx);
		x[i] = xin - cos(angle) * segLength;
		y[i] = yin - sin(angle) * segLength;
		segment(x[i], y[i], angle, pi);
	}

	public void segment(float x, float y, float a, PImage pi) {
		pushMatrix();
		translate(x, y);
		rotate(a);
		image(pi, 0, 0, 15, 20);
		popMatrix();
	}

	public void keyPressed() {
		if (mflag == 1) {
			if (keyCode == UP) {
				keys[0] = true;
				lastKey[0] = 0;
			}
			if (keyCode == LEFT) {
				keys[1] = true;
				lastKey2[0] = 0;
			}
			if (keyCode == RIGHT) {
				keys[2] = true;
				lastKey2[0] = 1;
			}
			if (keyCode == DOWN) {
				keys[3] = true;
				lastKey[0] = 1;
			}
		}
		if (key == 'y') {
			if (mflag == -1) {
				reGame();
				loop();
				draw();
			}
		} else if (key == 'n') {
			if (mflag == -1) {
				exit();
			}
		}
	}

	public void keyReleased() {
		if (keyCode == UP) {
			keys[0] = false;
			lastKey[1] = millis();
		}
		if (keyCode == LEFT) {
			keys[1] = false;
			lastKey2[1] = millis();
		}
		if (keyCode == RIGHT) {
			keys[2] = false;
			lastKey2[1] = millis();
		}
		if (keyCode == DOWN) {
			keys[3] = false;
			lastKey[1] = millis();
		}
	}

	public void measure() {
		b_hX = hX;
		b_hY = hY;
		mc.eval("[x,y] = jumpingmove(" + x[3] + "," + y[3] + "," + hX + ","+ hY + "," + velo + ");");
		for (int i = 0; i < 100; i++) {
			double result_x = 0;
			double result_y = 0;
			try {
				result_x = mc.getnumVari("x(" + (i + 1) + ")");
				result_y = mc.getnumVari("y(" + (i + 1) + ")");
			} catch (MatlabInvocationException e) {
			}
			hdx[i] = (float) result_x;
			hdy[i] = (float) result_y;
		}
		mflag <<= 1;
	}

	public void moving() {
		if (mflag == 1) {
			if (keys[0]) {
				hY -= velo;
			}
			if (keys[1]) {
				hX -= velo;
			}
			if (keys[2]) {
				hX += velo;
			}
			if (keys[3]) {
				hY += velo;
			}
			if (keys[0] == true || keys[1] == true || keys[2] == true
					|| keys[3] == true) {
				if (velo < 10) {
					velo += 0.05;
				}
			} else {
				if (velo > 0) {
					velo -= 1;
					if (lastKey[0] == 0) {
						if (lastKey2[0] == 0) {
							if (Math.abs(lastKey[1] - lastKey2[1]) <= 100) {
								hY -= velo;
								hX -= velo;
							} else if (lastKey[1] - lastKey2[1] <= 0) {
								hX -= velo;
							} else if (lastKey[1] - lastKey2[1] >= 0) {
								hY -= velo;
							}
						} else if (lastKey2[0] == 1) {
							if (Math.abs(lastKey[1] - lastKey2[1]) <= 100) {
								hY -= velo;
								hX += velo;
							} else if (lastKey[1] - lastKey2[1] <= 0) {
								hX += velo;
							} else if (lastKey[1] - lastKey2[1] >= 0) {
								hY -= velo;
							}
						}
					} else if (lastKey[0] == 1) {
						if (lastKey2[0] == 0) {
							if (Math.abs(lastKey[1] - lastKey2[1]) <= 100) {
								hY += velo;
								hX -= velo;
							} else if (lastKey[1] - lastKey2[1] <= 0) {
								hX -= velo;
							} else if (lastKey[1] - lastKey2[1] >= 0) {
								hY += velo;
							}
						} else if (lastKey2[0] == 1) {
							if (Math.abs(lastKey[1] - lastKey2[1]) <= 100) {
								hY += velo;
								hX += velo;
							} else if (lastKey[1] - lastKey2[1] <= 0) {
								hX += velo;
							} else if (lastKey[1] - lastKey2[1] >= 0) {
								hY += velo;
							}
						}
					}

				}
			}
		}
	}

	void endofGame() {
		noLoop();
		mflag = -1;
		if (mflag == -1) {
			background(0);
			textSize(32);
			fill(255);
			text("Time is up", width / 2 - 105, height / -70);
			text("Boat kills:" + numofboats, width / 2 - 130, height / 2 - 35);
			text("Bird kills:" + numofbirds, width / 2 - 130, height / 2);
			text("Scuba kills:" + numofscubas, width / 2 - 130, height / 2 + 35);
			text("Total kills:" + kill, width / 2 - 130, height / 2 + 70);
			text("Total points:" + points, width / 2 - 130, height / 2 + 100);
			text("Play again?", width / 2 - 130, height / 2 + 130);
			text("Y / N", width / 2 - 110, height / 2 + 175);
		}
	}

	void reGame() {
		hX = width / 2;
		hY = height - 50;
		head = loadImage("data\\header_r.png");
		body = loadImage("data\\body_r.png");
		back = loadImage("data\\lake image2.png");

		count = 0;
		mflag = 1;
		velo = 2;
		kill = 0;
		numofboats = 0;
		numofbirds = 0;
		numofscubas = 0;
		points = 0;
		timer = millis()/1000 + 31;

		e[0] = new WaterSurfE(0, 0);
		e[1] = new WaterSurfE(-100, 0);
		e[2] = new WaterSurfE(width + 35, 1);
		e[3] = new WaterSurfE(width + 100, 1);

		a[0] = new AirE(0, 0);
		a[1] = new AirE(0, 0);
		a[2] = new AirE(width + 20, 1);
		a[3] = new AirE(width + 100, 1);

		b[0] = new UnderwaterE(-70, 0);
		b[1] = new UnderwaterE(-120, 0);
		b[2] = new UnderwaterE(width + 70, 1);
		b[3] = new UnderwaterE(width + 100, 1);

		keys = new boolean[4];
		for (int i = 0; i < 4; i++) {
			keys[i] = false;
		}
	}
}
