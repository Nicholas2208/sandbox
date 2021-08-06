package com.nw.sandbox.visualization.maze;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;

public class MazeData {
	public static final char ROAD = ' ';
    public static final char WALL = '#';
	private int W, H;
	private char[][] maze;
	private int entranceX, entranceY;
    private int exitX, exitY;
	public boolean[][] visited;
    public boolean[][] path;
    public boolean[][] result;
	
	public MazeData(String fileName) {
		Scanner scanner = null;
		try {
			File file = new File(fileName);
			FileInputStream fis = new FileInputStream(file);
			scanner = new Scanner(new BufferedInputStream(fis), "UTF-8");
			String whLine = scanner.nextLine();
			String[] wh = whLine.trim().split("\\s+");
			W = Integer.parseInt(wh[0]);
            H = Integer.parseInt(wh[1]);
            maze = new char[W][H];
            visited = new boolean[W][H];
            path = new boolean[W][H];
            result = new boolean[W][H];
            for (int i = 0; i < W; i++) {
            	String line = scanner.nextLine();
            	for (int j = 0; j < H; j++) {
                    maze[i][j] = line.charAt(j);
                }
            }
		}catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (scanner != null) {
                scanner.close();
            }
        }
		
		this.entranceX = 1;
        this.entranceY = 0;
        this.exitX = W - 2;
        this.exitY = H - 1;
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

    public int W() {
        return W;
    }

    public int H() {
        return H;
    }
    
    public char getMaze(int i, int j) {
        return maze[i][j];
    }
    
    public boolean inArea(int x, int y) {
        return x >= 0 && x < W && y >= 0 && y < H;
    }
	
	public void print() {
        System.out.println(W + " " + H);
        for (int i = 0; i < W; i++) {
            for (int j = 0; j < H; j++) {
                System.out.print(maze[i][j]);
            }
            System.out.println();
        }
    }
	
	public static void main(String[] args) {
        String fileName = "src/main/java/com/nw/sandbox/visualization/maze/maze_101_101.txt";
        MazeData mazeData = new MazeData(fileName);
        mazeData.print();
    }
}
