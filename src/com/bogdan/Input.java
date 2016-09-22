package com.bogdan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.opengl.TextureLoader;

public class Input {
	public static int mousex; //получает значение мышки по x
	public static int mousey; //получает значение мышки по y
	public static int mousedx;
	public static int mousedy;
	/*
	* Метод отвечающий за указание координат цели
	*/
	public static void mouse() {
		if (Mouse.isButtonDown(0)) { //при условии что нажата кнопка мыши
			mousex = Mouse.getX(); //получает значение мышки по x
	        mousey = Mouse.getY(); //получает значение мышки по y
	        mousedx = (int) mousex/32+1;
	        mousedy = (int) (Map.mapY-1) - mousey/32;
	        if ((int) mousedx <= Map.mapX && (int) mousedx >= 1 && (int) mousedy <= Map.mapY && (int) mousedy >= 1){
	        	if (Map.map[mousedx][mousedy] == 0){
	        		int rx = (int) (Math.random()*5);
	        		int ry = (int) (Math.random()*5);
	        		Flag.cursorX=(int) rx;
		        	Flag.cursorY=(int) ry;
	        	} else {
	        		Flag.cursorX=Texture.rob1.rx;
		        	Flag.cursorY=Texture.rob1.ry;
		        	
			        mousedx = Texture.rob1.rx;
			        mousedy = Texture.rob1.ry;
			        mousex = 0;
			        mousey = 0;
			        mousex=(int)(Input.mousedx*32-32);
			        mousey=(int)(32*(Map.mapY-1-Input.mousedy));
	        	}
	        }
		}
		return;
	}
	
	
	/**
	 * Метод отвечающий за перемещение робота c помощью клавиш
	 * @throws Throwable 
	 */
	public static void keyboard() throws Throwable {
		while(Keyboard.next()){	//возвращает значение true если кнопка нажата
			if(Keyboard.getEventKeyState()){	//возвращает значение true если необходимая кнопка нажата
				if (Keyboard.getEventKey() == Keyboard.KEY_W) {	//задание переменной
					RobotAPI.move_up(1);	
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_S) {
					RobotAPI.move_down(1);	
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_A) {
					RobotAPI.move_left(1);
				}	
				else if (Keyboard.getEventKey() == Keyboard.KEY_D) {
					RobotAPI.move_right(1);
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_F) {
						MoveTo.moveto();
				}
				else if (Keyboard.getEventKey() == Keyboard.KEY_G) {
					SeachMaps.seachaim();
				}
			}
		}
	}

	public static org.newdawn.slick.opengl.Texture loadTexture(String key) throws Throwable{
		try {return TextureLoader.getTexture("PNG", new FileInputStream(new File("res/" + key + ".png")));
		} catch (FileNotFoundException e) {
		} catch (IOException e) {e.printStackTrace();}
		return null;
	}
}
