package com.nw.sandbox.visualization.fractal.sierpinski_carpet;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.BaseVisualizer;

public class CarpetVisualizer extends BaseVisualizer {
	private static final int MAX_DEPTH = 6;

	@Override
	public Object initData(int sceneWidth, int sceneHeight) {
		return new CarpetData(MAX_DEPTH);
	}

	@Override
	public BaseFrame initFrame(int sceneWidth, int sceneHeight) {
		int w = (int) Math.pow(3, MAX_DEPTH);
        int h = (int) Math.pow(3, MAX_DEPTH);
        BaseFrame frame = new CarpetFrame("Sierpinski Carpet Fractal", w, h);
        frame.addKeyListener(new CarpetKeyListener());
        return frame;
	}

	@Override
	public void run(Object data, BaseFrame frame) {
		setData(0);
	}
	
	private void setData(int depth) {
        CarpetData data = (CarpetData) getData();
        data.depth = depth;
        getFrame().setData(data);
    }
	
	private class CarpetKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent event) {
            char keyChar = event.getKeyChar();
            if (keyChar >= '0' && keyChar <= '9') {
                int depth = keyChar - '0';
                setData(depth);
            }
        }
    }

	public static void main(String[] args) {
        new CarpetVisualizer();
    }
}
