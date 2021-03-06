package com.nw.sandbox.visualization.fractal.sierpinski_carpet;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.VisHelper;

public class CarpetFrame extends BaseFrame {
	
	public CarpetFrame(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

	@Override
	public void paint(VisHelper visHelper, Object data) {
		drawFractal(visHelper, 0, 0, getCanvasWidth(), getCanvasHeight(), 0);
	}
	
	private void drawFractal(VisHelper g, int x, int y, int w, int h, int depth) {
		CarpetData data = (CarpetData) getData();
        int w_3 = w / 3;
        int h_3 = h / 3;
        if (depth == data.depth) {
            g.setColor(VisHelper.Indigo);
            g.fillRectangle(x + w_3, y + h_3, w_3, h_3);
            return;
        }
        if (w <= 1 || h <= 1) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i == 1 && j == 1) {
                    g.setColor(VisHelper.Indigo);
                    g.fillRectangle(x + w_3, y + h_3, w_3, h_3);
                } else {
                    drawFractal(g, x + i * w_3, y + j * h_3, w_3, h_3, depth + 1);
                }
            }
        }
	}

}
