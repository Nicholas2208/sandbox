package com.nw.sandbox.visualization.buildmaze;

public class MazeData {
	public static final char ROAD = ' ';
    public static final char WALL = '#';
    private int W, H;
    public char[][] maze;
    private int entranceX, entranceY;
    private int exitX, exitY;
    public boolean[][] visited;
    public boolean[][] inMist;
    public boolean[][] path;
    public boolean[][] result;
    public boolean enableMist;
    
    public MazeData(int W, int H) {
    	this.W = W;
        this.H = H;
        this.maze = new char[W][H];
        this.visited = new boolean[W][H];
        this.inMist = new boolean[W][H];
        this.path = new boolean[W][H];
        this.result = new boolean[W][H];
        for (int i = 0; i < W; i++) {
        	for (int j = 0; j < H; j++) {
        		if (i % 2 == 1 && j % 2 == 1) {
                    maze[i][j] = ROAD;
                } else {
                    maze[i][j] = WALL;
                }
                inMist[i][j] = true;
        	}
        }
        
        this.entranceX = 1;
        this.entranceY = 0;
        this.exitX = W - 2;
        this.exitY = H - 1;
        this.maze[entranceX][entranceY] = ROAD;
        this.maze[exitX][exitY] = ROAD;
    }
    
    public int W() {
        return W;
    }

    public int H() {
        return H;
    }

    public int getEntranceX() {
        return entranceX;
    }

    public int getEntranceY() {
        return entranceY;
    }

    public int getExitX() {
        return exitX;
    }

    public int getExitY() {
        return exitY;
    }

    public boolean inArea(int x, int y) {
        return x >= 0 && x < W && y >= 0 && y < H;
    }

    public void openMist(int newX, int newY) {
    	for (int i = newX - 1; i <= newX + 1; i++) {
    		for (int j = newY - 1; j <= newY + 1; j++) {
    			this.inMist[i][j] = false;
    		}
    	}
    }
}
