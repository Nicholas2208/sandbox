package com.nw.sandbox.visualization.maze.dfs;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.BaseVisualizer;
import com.nw.sandbox.visualization.base.VisHelper;
import com.nw.sandbox.visualization.maze.MazeData;
import com.nw.sandbox.visualization.maze.MazeFrame;

public class MazeVisualizer extends BaseVisualizer {
	private static final String FILE_NAME = "src/main/java/com/nw/sandbox/visualization/maze/maze_101_101.txt";
	private static final int BLOCK_SIDE = 6;
    private static final int DELAY = 1;
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	@Override
	public Object initData(int sceneWidth, int sceneHeight) {
		return new MazeData(FILE_NAME);
	}

	@Override
	public BaseFrame initFrame(int sceneWidth, int sceneHeight) {
		MazeData data = (MazeData) getData();
        return new MazeFrame("Maze Solver", data.M() * BLOCK_SIDE, data.N() * BLOCK_SIDE);
	}

	@Override
	public void run(Object data, BaseFrame frame) {
		MazeData mazeData = (MazeData) data;
        setData(-1, -1, false);
        if (!go(mazeData.getEntranceX(), mazeData.getEntranceY())) {
            System.out.println("The maze has NO solution!");
        }
        setData(-1, -1, false);
	}
	
	private boolean go(int x, int y) {
		MazeData data = (MazeData) getData();
        data.visited[x][y] = true;
        setData(x, y, true);
        if (x == data.getExitX() && y == data.getExitY()) {
            return true;
        }
        for (int[] aD : d) {
        	int newX = x + aD[0];
            int newY = y + aD[1];
            if (data.inArea(newX, newY) &&
                    data.getMaze(newX, newY) == MazeData.ROAD &&
                    !data.visited[newX][newY]) {
            	if (go(newX, newY)) {
                    return true;
                }
            }
        }
        
        setData(x, y, false);
		return false;
	}
	
	private void setData(int x, int y, boolean isPath) {
		MazeData data = (MazeData) getData();
		if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
		getFrame().setData(getData());
		VisHelper.pause(DELAY);
	}

	public static void main(String[] args) {
		new MazeVisualizer();
	}

}
