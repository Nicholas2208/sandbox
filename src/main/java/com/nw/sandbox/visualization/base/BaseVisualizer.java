package com.nw.sandbox.visualization.base;

import java.awt.EventQueue;

public abstract class BaseVisualizer {
	private Object data;
    private BaseFrame frame;
    
    public abstract Object initData(int sceneWidth, int sceneHeight);
    
    public abstract BaseFrame initFrame(int sceneWidth, int sceneHeight);
    
    public abstract void run(Object data, BaseFrame frame);
    
    public BaseVisualizer() {
    	this(0, 0);
    }
    
    public BaseVisualizer(int sceneWidth, int sceneHeight) {
    	data = initData(sceneWidth, sceneHeight);
    	EventQueue.invokeLater(() -> {
    		frame = initFrame(sceneWidth, sceneHeight);
    		new Thread(() -> run(data, frame)).start();
    	});
    }

	public Object getData() {
		return data;
	}

	public BaseFrame getFrame() {
		return frame;
	}
}
