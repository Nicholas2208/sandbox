package com.nw.sandbox.visualization.base;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public abstract class BaseFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 500;
    
    private int canvasWidth;
    private int canvasHeight;
    private Object data;
    
	public BaseFrame(String title, int canvasWidth, int canvasHeight) throws HeadlessException {
		super(title);
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		BaseCanvas canvas = new BaseCanvas();
		this.setContentPane(canvas);
        this.setResizable(false);
        this.pack();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
	}
	
	public BaseFrame(String title) {
		this(title, DEFAULT_WIDTH, DEFAULT_HEIGHT);
	}
	
	public int getCanvasWidth() {
        return canvasWidth;
    }

    public int getCanvasHeight() {
        return canvasHeight;
    }
    
    public Object getData() {
        return data;
    }
    
    public void setData(Object data) {
        this.data = data;
        this.repaint();
    }
    
    public abstract void paint(VisHelper visHelper, Object data);
    
    private class BaseCanvas extends JPanel{

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			VisHelper visHelper = VisHelper.getInstance(g);
			if (data != null) {
				BaseFrame.this.paint(visHelper, data);
			}
		}

		@Override
		public Dimension getPreferredSize() {
			return new Dimension(canvasWidth, canvasHeight);
		}
    	
    }
}
