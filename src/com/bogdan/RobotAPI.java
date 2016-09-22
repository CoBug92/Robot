package com.bogdan;
import static com.bogdan.Texture.rob1;


import org.lwjgl.opengl.Display;

public class RobotAPI {
	public Texture texture = new Texture();
	static int memo = 0;

	
	public static void move_up(int kolvoraz){
		if (rob1.ry > 1){
			for(int i = 1; i<= kolvoraz; i++){
				if(Map.map[rob1.rx][rob1.ry - 1] == 0){
					Map.map[rob1.rx][rob1.ry] = memo;
					memo = Map.map[rob1.rx][rob1.ry -1];
					rob1.ry--;
				}
				else{
					Display.update();	//Рендеринг
				}
			}
		}
	}
	
	public static void move_right(int kolvoraz){
		if (rob1.rx < Map.mapX-1){
			for(int i = 1; i<= kolvoraz; i++){
				if(Map.map[rob1.rx + 1][rob1.ry] ==0){
					Map.map[rob1.rx][rob1.ry] = memo;
					memo = Map.map[rob1.rx + 1][rob1.ry];
					rob1.rx++;
				}
				else{
					Display.update();	//Рендеринг
				}
			}
		}
	}
	
	public static void move_down(int kolvoraz){
		if (rob1.ry < Map.mapY-1){
			for(int i = 1; i<= kolvoraz; i++){
				if(Map.map[rob1.rx][rob1.ry + 1] == 0){
					Map.map[rob1.rx][rob1.ry] = memo;
					memo = Map.map[rob1.rx][rob1.ry +1];
					rob1.ry++;
				}
				else{
					Display.update();	//Рендеринг
				}
			}
		}
	}
	
	public static void move_left(int kolvoraz){
		if (rob1.rx > 1){
			for(int i = 1; i<= kolvoraz; i++){
				if(Map.map[rob1.rx -1][rob1.ry] == 0){
					Map.map[rob1.rx][rob1.ry] = memo;
					memo = Map.map[rob1.rx -1][rob1.ry];
					rob1.rx--;
				}
				else{
					Display.update();	//Рендеринг
				}	
			}
		}
	}
}
