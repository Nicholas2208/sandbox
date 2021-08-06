package com.nw.sandbox.visualization.minesweeper;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.VisHelper;

public class MineSweeperFrame extends BaseFrame {
	
	public MineSweeperFrame(String title, int canvasWidth, int canvasHeight) {
        super(title, canvasWidth, canvasHeight);
        setLayout(null);
    }

	@Override
	public void paint(VisHelper visHelper, Object obj) {
		MineSweeperData data = (MineSweeperData) obj;
		int cellw = getCanvasWidth() / data.W();
        int cellh = getCanvasHeight() / data.H();
        for (int i = 0; i < data.H(); i++) {
        	for (int j = 0; j < data.W(); j++) {
        		if (data.open[i][j]) {
        			if (data.isMine(i, j)) {
                        visHelper.putImage(j * cellw, i * cellh, MineSweeperData.MINE_IMAGE_URL);
                    } else {
                        visHelper.putImage(j * cellw, i * cellh, MineSweeperData.numberImageURL(data.getNumber(i, j)));
                    }
        		}else {
        			visHelper.putImage(j * cellw, i * cellh, MineSweeperData.BLOCK_IMAGE_URL);
        		}
        	}
        }
    }
}
