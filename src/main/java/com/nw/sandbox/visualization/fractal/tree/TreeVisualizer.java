package com.nw.sandbox.visualization.fractal.tree;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.BaseVisualizer;

public class TreeVisualizer extends BaseVisualizer {
	private static final int MAX_DEPTH = 6;
    private static final double SPLIT_ANGLE = 60.0;
	
	public TreeVisualizer(int sceneWidth, int sceneHeight) {
        super(sceneWidth, sceneHeight);
    }

	@Override
	public Object initData(int sceneWidth, int sceneHeight) {
		return new TreeData(MAX_DEPTH, SPLIT_ANGLE);
	}

	@Override
	public BaseFrame initFrame(int sceneWidth, int sceneHeight) {
		BaseFrame frame = new TreeFrame("Tree Fractal", sceneWidth, sceneHeight);
		frame.addKeyListener(new FractalKeyListener());
		return frame;
	}

	@Override
	public void run(Object data, BaseFrame frame) {
		setData(0);
	}
	
	private void setData(int depth) {
		TreeData data = (TreeData) getData();
        data.depth = depth;
        getFrame().setData(data);
	}
	
	private class FractalKeyListener extends KeyAdapter {
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
		new TreeVisualizer(800, 800);
	}

}
