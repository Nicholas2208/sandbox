package com.nw.sandbox.visualization.fractal.snowflake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.BaseVisualizer;

public class SnowflakeVisualizer extends BaseVisualizer {
	private static final int MAX_DEPTH = 6;
    private static final int SIZE = 900;

	@Override
	public Object initData(int sceneWidth, int sceneHeight) {
		return new SnowflakeData(MAX_DEPTH);
	}

	@Override
	public BaseFrame initFrame(int sceneWidth, int sceneHeight) {
		int w = SIZE;
        int h = SIZE / 3;
        BaseFrame frame = new SnowflakeFrame("Snowflake Fractal", w, h);
        frame.addKeyListener(new SnowflakeKeyListener());
        return frame;
	}

	@Override
	public void run(Object data, BaseFrame frame) {
		setData(0);
	}
	
	private void setData(int depth) {
        SnowflakeData data = (SnowflakeData) getData();
        data.depth = depth;
        getFrame().setData(data);
    }
	
	private class SnowflakeKeyListener extends KeyAdapter {
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
		new SnowflakeVisualizer();
	}

}
