package com.nw.sandbox.visualization.fractal.tree;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.VisHelper;

public class TreeFrame extends BaseFrame {
	
	public TreeFrame(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

	@Override
	public void paint(VisHelper visHelper, Object data) {
		drawFractal(visHelper, getCanvasWidth() / 2, getCanvasHeight(), getCanvasHeight(), 0, 0);
	}
	
	private void drawFractal(VisHelper visHelper, double x1, double y1, double side, double angle, int depth) {
		TreeData data = (TreeData) getData();
        if (side <= 0) {
            return;
        }
        if (depth == data.depth) {
        	double x2 = x1 - Math.sin(angle * Math.PI / 180.0) * side;
            double y2 = y1 - Math.cos(angle * Math.PI / 180.0) * side;
            visHelper.setColor(VisHelper.Indigo);
            visHelper.drawLine(x1, y1, x2, y2);
            return;
        }
        
        double s = side / 2;
        double x2 = x1 - Math.sin(angle * Math.PI / 180.0) * s;
        double y2 = y1 - Math.cos(angle * Math.PI / 180.0) * s;
        visHelper.setColor(VisHelper.Indigo);
        visHelper.drawLine(x1, y1, x2, y2);
        
        drawFractal(visHelper, x2, y2, s, angle + data.splitAngle / 2, depth + 1);
        drawFractal(visHelper, x2, y2, s, angle - data.splitAngle / 2, depth + 1);
	}

}
