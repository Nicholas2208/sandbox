package com.nw.sandbox.visualization.minesweeper;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.BaseVisualizer;

public class MineSweeperVisualizer extends BaseVisualizer {
	private static final int W = 20;
    private static final int H = 20;

	@Override
	public Object initData(int sceneWidth, int sceneHeight) {
		return new MineSweeperData(W, H);
	}

	@Override
	public BaseFrame initFrame(int sceneWidth, int sceneHeight) {
		MineSweeperFrame frame = new MineSweeperFrame("Mine Sweeper", W * MineSweeperData.BLOCK_SIZE, H * MineSweeperData.BLOCK_SIZE);
		frame.addMouseListener(new MineMouseListener());
		frame.addKeyListener(new MineKeyListener());
		return frame;
	}

	@Override
	public void run(Object data, BaseFrame frame) {
		setData(true, -1, -1);
	}
	
	private void setData(boolean isLeftClicked, int x, int y) {
		MineSweeperData data = (MineSweeperData) getData();
		BaseFrame frame = getFrame();
		if (data.inArea(x, y)) {
			if (isLeftClicked) {
				if (data.isMine(x, y)) {
                    data.gameOver();
                } else {
                    data.open(x, y);
                }
			}
		}
		
		frame.setData(data);
	}
	
	private class MineKeyListener extends KeyAdapter {
        @Override
        public void keyReleased(KeyEvent e) {
            MineSweeperData data = (MineSweeperData) getData();
            if (e.getKeyChar() == ' ') {
                data.resetGame();
                setData(true, -1, -1);
            }
        }
    }
	
	private class MineMouseListener extends MouseAdapter {
		@Override
		public void mouseReleased(MouseEvent e) {
			MineSweeperData data = (MineSweeperData) getData();
            MineSweeperFrame frame = (MineSweeperFrame) getFrame();
            e.translatePoint(-(frame.getBounds().width - frame.getCanvasWidth()),
                    -(frame.getBounds().height - frame.getCanvasHeight()));
            Point point = e.getPoint();
            int w = frame.getCanvasWidth() / data.W();
            int h = frame.getCanvasHeight() / data.H();
            int x = point.y / h;
            int y = point.x / w;
            
            setData(SwingUtilities.isLeftMouseButton(e), x, y);
		}
	}

	public static void main(String[] args) {
		new MineSweeperVisualizer();
	}

}
