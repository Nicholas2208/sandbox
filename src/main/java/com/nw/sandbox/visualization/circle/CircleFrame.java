package com.nw.sandbox.visualization.circle;

import java.awt.Color;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.VisHelper;

public class CircleFrame extends BaseFrame {
	
	public CircleFrame(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
    }

	@Override
	public void paint(VisHelper visHelper, Object data) {
		visHelper.setStrokeWidth(1);
		visHelper.setColor(Color.RED);
		Circle[] circles = (Circle[]) data;
		for (Circle c : circles) {
			if (c.isFilled) {
				visHelper.fillCircle(c.x, c.y, c.getR());
			}else {
				visHelper.strokeCircle(c.x, c.y, c.getR());
			}
		}
	}

}
