package com.nw.sandbox.visualization.fractal.vicsek;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.VisHelper;

public class VicsekFrame extends BaseFrame {
	
	public VicsekFrame(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

	@Override
	public void paint(VisHelper visHelper, Object obj) {
		VicsekData data = (VicsekData) obj;
        drawFractal(visHelper, 0, 0, getCanvasWidth(), getCanvasHeight(), 0);
	}

	private void drawFractal(VisHelper g, int x, int y, int w, int h, int depth) {
        VicsekData data = (VicsekData) getData();
        if (depth == data.depth) {
            g.setColor(VisHelper.Indigo);
            g.fillRectangle(x, y, w, h);
            return;
        }
        if (w <= 1 || h <= 1) {
            g.setColor(VisHelper.Indigo);
            g.fillRectangle(x, y, Math.max(w, 1), Math.max(h, 1));
            return;
        }
        int w_3 = w / 3;
        int h_3 = h / 3;
        drawFractal(g, x, y, w_3, h_3, depth + 1);
        drawFractal(g, x + 2 * w_3, y, w_3, h_3, depth + 1);
        drawFractal(g, x + w_3, y + h_3, w_3, h_3, depth + 1);
        drawFractal(g, x, y + 2 * h_3, w_3, h_3, depth + 1);
        drawFractal(g, x + 2 * w_3, y + 2 * h_3, w_3, h_3, depth + 1);
    }
}
