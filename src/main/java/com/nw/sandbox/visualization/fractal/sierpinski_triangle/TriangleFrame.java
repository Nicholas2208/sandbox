package com.nw.sandbox.visualization.fractal.sierpinski_triangle;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.VisHelper;

public class TriangleFrame extends BaseFrame {
	
	public TriangleFrame(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

	@Override
	public void paint(VisHelper visHelper, Object data) {
		drawFractal(visHelper, 0, getCanvasHeight(), getCanvasWidth(), 0);
	}

	private void drawFractal(VisHelper g, int aX, int aY, int side, int depth) {
        TriangleData data = (TriangleData) getData();
        int bX = aX + side;
        int bY = aY;
        int h = (int) (Math.sin(60.0 * Math.PI / 180.0) * side);
        int cX = aX + side / 2;
        int cY = aY - h;
        if (side <= 1) {
            g.setColor(VisHelper.Indigo);
            g.fillRectangle(aX, aY, 1, 1);
            return;
        }
        if (depth == data.depth) {
            g.setColor(VisHelper.Indigo);
            g.fillTriangle(aX, aY, bX, bY, cX, cY);
            return;
        }
        int abCenterX = (aX + bX) / 2;
        int abCenterY = (aY + bY) / 2;
        int acCenterX = (aX + cX) / 2;
        int acCenterY = (aY + cY) / 2;
        g.setColor(VisHelper.Indigo);
        drawFractal(g, aX, aY, side / 2, depth + 1);
        drawFractal(g, acCenterX, acCenterY, side / 2, depth + 1);
        drawFractal(g, abCenterX, abCenterY, side / 2, depth + 1);
    }
}
