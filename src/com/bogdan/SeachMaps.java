package com.bogdan;

public class SeachMaps {
	public static void seachaim() throws Throwable{
		for (int x = 1; x <= Map.mapX; x++){
			for (int y = 1; y <= Map.mapY; y++){
				if ((int) x < Map.mapX && (int) x >= 1 && (int) y < Map.mapY && (int) y >= 1){
			        	if (Map.map[Flag.cursorX][Flag.cursorY] != 1000){
			        		Flag.cursorX=x;
				        	Flag.cursorY=y;
				        	Input.mousedx = x;
					        Input.mousedy = y;
					        Input.mousex=(int)(Input.mousedx*32-32);
					        Input.mousey=(int)(32*(Map.mapY-1-Input.mousedy));
				        	MoveTo.moveto();
			        }
				}
			}
		}
	}
}

