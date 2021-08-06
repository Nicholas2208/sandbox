package com.nw.sandbox.visualization.maze.bfs;

import java.util.Stack;

import com.nw.sandbox.visualization.base.BaseFrame;
import com.nw.sandbox.visualization.base.BaseVisualizer;
import com.nw.sandbox.visualization.base.VisHelper;
import com.nw.sandbox.visualization.maze.MazeData;
import com.nw.sandbox.visualization.maze.MazeFrame;

public class MazeVisualizerNoRecursive extends BaseVisualizer {
	private static final String FILE_NAME = "src/main/java/com/nw/sandbox/visualization/maze/maze_101_101.txt";
	private static final int BLOCK_SIZE = 6;
    private static final int DELAY = 1;
    private static final int d[][] = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

	@Override
	public Object initData(int sceneWidth, int sceneHeight) {
		return new MazeData(FILE_NAME);
	}

	@Override
	public BaseFrame initFrame(int sceneWidth, int sceneHeight) {
		MazeData data = (MazeData) getData();
        return new MazeFrame("Maze Solver", data.W() * BLOCK_SIZE, data.H() * BLOCK_SIZE);
	}

	@Override
	public void run(Object data, BaseFrame frame) {
		MazeData mazeData = (MazeData) data;
        setData(-1, -1, false);

        Stack<Position> stack = new Stack<>();
        Position entrance = new Position(mazeData.getEntranceX(), mazeData.getEntranceY(), null);
        stack.push(entrance);
        mazeData.visited[entrance.x][entrance.y] = true;
        boolean isSolved = false;
        while (!stack.empty()) {
        	Position curPos = stack.pop();
        	setData(curPos.x, curPos.y, true);
        	if (curPos.x == mazeData.getExitX() && curPos.y == mazeData.getExitY()) {
                isSolved = true;
                findPath(curPos);
                break;
            }
        	for (int[] aD : d) {
        		int newX = curPos.x + aD[0];
                int newY = curPos.y + aD[1];
                if (mazeData.inArea(newX, newY)
                        && !mazeData.visited[newX][newY]
                        && mazeData.getMaze(newX, newY) == MazeData.ROAD) {
                    stack.push(new Position(newX, newY, curPos));
                    mazeData.visited[newX][newY] = true;
                }
        	}
        }
        
        if (!isSolved) {
            System.out.println("The maze has NO solution!");
        }
        setData(-1, -1, false);
	}
	
	private void findPath(Position des) {
        MazeData data = (MazeData) getData();
        Position cur = des;
        while (cur != null) {
            data.result[cur.x][cur.y] = true;
            cur = cur.prev;
        }
    }
	
	private void setData(int x, int y, boolean isPath) {
		MazeData data = (MazeData) getData();
		if (data.inArea(x, y)) {
            data.path[x][y] = isPath;
        }
		getFrame().setData(getData());
		VisHelper.pause(DELAY);
	}
	
	private class Position {
        private int x, y;
        private Position prev;

        private Position(int x, int y, Position prev) {
            this.x = x;
            this.y = y;
            this.prev = prev;
        }
    }

	public static void main(String[] args) {
		new MazeVisualizerNoRecursive();
	}

}
