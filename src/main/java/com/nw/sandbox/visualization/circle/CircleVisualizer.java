package com.nw.sandbox.visualization.circle;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.BaseVisualizer;
import com.nw.sandbox.visualization.base.VisHelper;

public class CircleVisualizer extends BaseVisualizer {
	private static final int N = 10;
    
    public CircleVisualizer(int sceneWidth, int sceneHeight) {
        super(sceneWidth, sceneHeight);
    }

	@Override
	public Object initData(int sceneWidth, int sceneHeight) {
		Circle[] circles = new Circle[N];
		int R = 50;
		for (int i = 0; i < N; i++) {
			int x = (int) (Math.random() * (sceneWidth - 2 * R) + R);
            int y = (int) (Math.random() * (sceneHeight - 2 * R) + R);
            int vx = (int) (Math.random() * 11 - 5);
            int vy = (int) (Math.random() * 11 - 5);
            circles[i] = new Circle(x, y, R, vx, vy);
		}
		
		return circles;
	}

	@Override
	public BaseFrame initFrame(int sceneWidth, int sceneHeight) {
		CircleFrame frame = new CircleFrame("Welcome", sceneWidth, sceneHeight);
		frame.addMouseListener(new CircleMouseListener());
		return frame;
	}

	@Override
	public void run(Object data, BaseFrame frame) {
		Circle[] circles = (Circle[]) data;
		while (true) {
			frame.setData(circles);
			VisHelper.pause(20);
			for (Circle c : circles) {
				c.move(0, 0, frame.getCanvasWidth(), frame.getCanvasHeight());
			}
		}
	}
	
	private class CircleMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent event) {
            Circle[] circles = (Circle[]) getData();
            for (Circle circle : circles) {
                if (circle.contain(event.getPoint())) {
                    circle.isFilled = !circle.isFilled;
                }
            }
        }
    }

	public static void main(String[] args) {
        int sceneWidth = 800;
        int sceneHeight = 700;
        new CircleVisualizer(sceneWidth, sceneHeight);
    }
}
