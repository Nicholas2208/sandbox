package com.nw.sandbox.visualization.maze;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.VisHelper;

public class MazeFrame extends BaseFrame {
	
	public MazeFrame(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

	@Override
	public void paint(VisHelper visHelper, Object data) {
		MazeData mazeData = (MazeData) data;
		int w = getCanvasWidth() / mazeData.M();
        int h = getCanvasHeight() / mazeData.N();
        for (int i = 0; i < mazeData.N(); i++) {
        	for (int j = 0; j < mazeData.M(); j++) {
        		if (mazeData.getMaze(i, j) == MazeData.WALL) {
        			visHelper.setColor(VisHelper.LightBlue);
        		}else {
        			visHelper.setColor(VisHelper.White);
        		}
        		if (mazeData.path[i][j]) {
        			visHelper.setColor(VisHelper.Yellow);
        		}
        		if (mazeData.result[i][j]) {
        			visHelper.setColor(VisHelper.Red);
        		}
        		visHelper.fillRectangle(j * w, i * h, w, h);
        	}
        }
	}

}
