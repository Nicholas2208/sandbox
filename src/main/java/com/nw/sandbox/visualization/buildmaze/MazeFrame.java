package com.nw.sandbox.visualization.buildmaze;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.VisHelper;

public class MazeFrame extends BaseFrame {
	
	public MazeFrame(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

	@Override
	public void paint(VisHelper visHelper, Object data) {
		MazeData mazeData = (MazeData) data;
		int w = getCanvasWidth() / mazeData.W();
        int h = getCanvasHeight() / mazeData.H();
        for (int i = 0; i < mazeData.W(); i++) {
        	for (int j = 0; j < mazeData.H(); j++) {
        		if (mazeData.enableMist && mazeData.inMist[i][j]) {
        			visHelper.setColor(VisHelper.Black);
        		}else {
        			if (mazeData.maze[i][j] == MazeData.WALL) {
        				visHelper.setColor(VisHelper.LightBlue);
        			}else {
        				visHelper.setColor(VisHelper.White);
        			}
        			if (mazeData.path[i][j]) {
        				visHelper.setColor(VisHelper.Yellow);
        			}
        		}
        		visHelper.fillRectangle(j * w, i * h, w, h);
        	}
        }
	}

}
