package com.nw.sandbox.visualization.fractal.snowflake;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.VisHelper;

public class SnowflakeFrame extends BaseFrame {
	
	public SnowflakeFrame(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

	@Override
	public void paint(VisHelper g, Object data) {
		drawFractal(g, 0, getCanvasHeight() - 3, getCanvasWidth(), 0, 0);
	}

	private void drawFractal(VisHelper g, double x1, double y1, double side, double angle, int depth) {
        SnowflakeData data = (SnowflakeData) getData();
        if (side <= 0) {
            return;
        }
        if (depth == data.depth) {
            double x2 = x1 + Math.cos(angle * Math.PI / 180.0) * side;
            double y2 = y1 - Math.sin(angle * Math.PI / 180.0) * side;
            g.setColor(VisHelper.Indigo);
            g.drawLine(x1, y1, x2, y2);
            return;
        }
        double s = side / 3;
        double x2 = x1 + Math.cos(angle * Math.PI / 180.0) * s;
        double y2 = y1 - Math.sin(angle * Math.PI / 180.0) * s;
        drawFractal(g, x1, y1, s, angle, depth + 1);

        double x3 = x2 + Math.cos((angle + 60.0) * Math.PI / 180.0) * s;
        double y3 = y2 - Math.sin((angle + 60.0) * Math.PI / 180.0) * s;
        drawFractal(g, x2, y2, s, angle + 60.0, depth + 1);

        double x4 = x3 + Math.cos((angle - 60.0) * Math.PI / 180.0) * s;
        double y4 = y3 - Math.sin((angle - 60.0) * Math.PI / 180.0) * s;
        drawFractal(g, x3, y3, s, angle - 60.0, depth + 1);

        drawFractal(g, x4, y4, s, angle, depth + 1);
    }
}
