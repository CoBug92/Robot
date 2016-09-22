package com.bogdan;

public class Robot {
	public int rx = 1;
	public int ry = 1;
	private static org.newdawn.slick.opengl.Texture robot;
	
	/**
	 * Метод служит для инициализации робота
	 */
		public void initrobot(){
			for (int x = 1; x<Map.mapX; x++){
				if (Map.map[x][1] == 0) {   
					rx= x;
				break;
				}
			}
		}
		
		/**
		 * Метод для отрисовки робота
		 */
		public void drawrobot() throws Throwable{
			robot = Input.loadTexture("robot");
			robot.bind();
			Map.drawquad(rx,ry);
		}
}
