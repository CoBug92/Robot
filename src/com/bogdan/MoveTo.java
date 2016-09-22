package com.bogdan;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;

import java.util.ArrayList;

import org.lwjgl.opengl.Display;

public class MoveTo {

	static int[][] movemap = new int [Map.mapX+1][Map.mapY+1];
	public static ArrayList<Integer> path;
	static int finderX = Texture.rob1.rx;
	static int finderY = Texture.rob1.ry;
	
	public static void moveto() throws Throwable{
		
		path = new ArrayList<Integer>();//список направления куда надо идти роботу чтобы придти к флагу
		
		int movestotarget;
		for(int y = 1; y < Map.mapY; y++){
			for(int x = 1; x < Map.mapX; x++){
				if(Map.map[x][y] == 0){    // 0 means empty space
					movemap[x][y]=0;
				}
				else{
					
					movemap[x][y]=1000;
				}	
			}
		}
				
		movemap[Texture.rob1.rx][Texture.rob1.ry] = 1; //в новый массив будем вносить дальность расположения точки от робота
		for(int count = 0; count<=1000; count++){//Цикл полностью пробегающий карту
			for(int y = 1; y < Map.mapY; y++){
				for(int x = 1; x < Map.mapX; x++){
					if ( (movemap[x][y] != 1000) && (movemap[x][y] != 0) && (movemap[x+1][y] == 0) ){
						movemap[x+1][y] = movemap[x][y]+1;
					}
					else if(movemap[x][y] != 1000 && movemap[x][y] != 0 && movemap[x-1][y] == 0){
						movemap[x-1][y] = movemap[x][y]+1;
					}
					else if(movemap[x][y] != 1000 && movemap[x][y] != 0 && movemap[x][y+1] == 0){
						movemap[x][y+1] = movemap[x][y]+1;
					}
					else if(movemap[x][y] != 1000 && movemap[x][y] != 0 && movemap[x][y-1] == 0){
						movemap[x][y-1] = movemap[x][y]+1;
					}
				}
			}
		}
		Input.mousex = Input.mousedx;
		Input.mousey = Input.mousedy;
		finderX = Input.mousex;
		finderY = Input.mousey;
		movestotarget = movemap[finderX][finderY];
		if (movemap[finderX][finderY] != 0){
			for(int i = 0; i <= movestotarget; i++){
				if (movemap[Input.mousex-1][Input.mousey] == (movemap[Input.mousex][Input.mousey]-1)){ //right
					path.add(2);
					Input.mousex--;
				}
				else if (movemap[Input.mousex+1][Input.mousey] == (movemap[Input.mousex][Input.mousey]-1)){ //left
					path.add(4);
					Input.mousex++;
				}
				else if (movemap[Input.mousex][Input.mousey-1] == (movemap[Input.mousex][Input.mousey]-1)){ //down
					path.add(3);
					Input.mousey--;
				}
				else if (movemap[Input.mousex][Input.mousey+1] == (movemap[Input.mousex][Input.mousey]-1)){ //up
					path.add(1);
					Input.mousey++;
				}
			}
		}
		for (int i=path.size()-1; i>=0; i--) {
			switch(path.get(i)) {
				case 1:
					RobotAPI.move_up(1);
					break;
				case 2:
					RobotAPI.move_right(1);
					break;
				case 3:
					RobotAPI.move_down(1);
					break;
				case 4:
					RobotAPI.move_left(1);
					break;
			}
			glClear(GL_COLOR_BUFFER_BIT);  // Отчистка экрана
			Map.drawmap();
			Texture.rob1.drawrobot();
			Thread.sleep(100);
			Display.update();
		
		}
	}
}