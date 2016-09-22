package com.bogdan;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;


public class Texture {

	public static Robot rob1 = new Robot();
	
	/**
	 * Метод позволяющий создать окно
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws LWJGLException 
	 */
	public void window() throws Throwable,FileNotFoundException, IOException{
		//Создание рабочего окна
		try {
			Display.setDisplayMode(new DisplayMode(608, 288)); //размер окна
			Display.setTitle("Reseach map");	//название окна
			Display.create();
		}
		catch (LWJGLException e) {e.printStackTrace();}
		Map.initquad();
		Map.initmap();
		rob1.initrobot();
		try {
			Display.makeCurrent();
		} 
		catch (LWJGLException e) {e.printStackTrace();}
		//Вызов окна
		while (!Display.isCloseRequested()){	//Инициализация OpenGL
			glClear(GL_COLOR_BUFFER_BIT);  // Отчистка экрана
			Map.drawmap();
			rob1.drawrobot();
			Input.mouse();
			Input.keyboard();
			Display.sync(60); //FPS
			Display.update();	//Рендеринг
		}
		Display.destroy();	
		System.exit(0);
	}
	
	public static void main(String[] argv) throws Throwable,FileNotFoundException, IOException {
		Texture texture = new Texture();
    	texture.window();
    }
}


