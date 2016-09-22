package com.bogdan;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glVertex2f;

public class Map{
	public static int mapX = 20;
	public static int mapY = 10;
	public static int[][] map = new int[mapX+1][mapY+1];
	public static org.newdawn.slick.opengl.Texture road;
	public static org.newdawn.slick.opengl.Texture wall;
	public static org.newdawn.slick.opengl.Texture floor;
	
	/**
	 * Метод служит для генерации рандомной карты
	 * */
	static void initmap(){
		for (int y = 0; y <= mapY; y++){
			for (int x = 0; x <= mapX; x++){
				map[x][y] =  0;
				switch ((int) (Math.random()*5)) {   
					case 1: map[x][y] = 1;
					break;
				}
			}
		}
	}
	
	/**
	 * Метод служит для отрисовки карты
	 */
	public static void drawmap() throws Throwable{
		floor = Input.loadTexture("floor");
		wall = Input.loadTexture("wall");
		road = Input.loadTexture("road");
		for (int y = 1; y<=mapY; y++){ 
			for (int x = 1; x<=mapX; x++){
				switch (map[x][y]){
					case 0:
						floor.bind();
						drawquad(x,y);
						break;
					case 1:
						wall.bind();
						drawquad(x,y);
						break;
				}
				road.bind();
				drawquad(Flag.cursorX,Flag.cursorY);
			}
		}
	}


	public static void drawquad(int x, int y) {
		glBegin(GL_QUADS);	// Рисуем квадрат
			glTexCoord2f(0,0);	//координаты изображения
		    glVertex2f(x*32,y*32+32);	//upper -left
		    glTexCoord2f(1,0);	//координаты изображения
		    glVertex2f(x*32+32,y*32+32);	//upper -right
		    glTexCoord2f(1,1);	//координаты изображения
		    glVertex2f(x*32+32,y*32);	//bottom -right
		    glTexCoord2f(0,1);	//координаты изображения
		    glVertex2f(x*32,y*32); //bottom -left
		glEnd();
	}
	
	public static void initquad(){
		glMatrixMode(GL_PROJECTION); //Обозначаем, что работать будем в GL
		glLoadIdentity();
	    glOrtho(32, 640, 320, 32, 1, -1); //https://www.opengl.org/sdk/docs/man2/xhtml/glOrtho.xml
	    glMatrixMode(GL_MODELVIEW);
	    glEnable(GL_TEXTURE_2D);	//Обозначаем, что работаем в 2D графике
	}
}
