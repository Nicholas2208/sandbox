package com.nw.sandbox.visualization.minesweeper;

public class MineSweeperData {
	private static final String PREFIX = "src/main/java/com/nw/sandbox/visualization/minesweeper/images/";
	public static final String BLOCK_IMAGE_URL = PREFIX + "block.png";
	public static final String FLAG_IMAGE_URL = PREFIX + "flag.png";
	public static final String MINE_IMAGE_URL = PREFIX + "mine.png";
	public static final int BLOCK_SIZE = 32;
	private static final int D[][] = { { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, -1 }, { 0, 1 }, { 1, -1 }, { 1, 0 },
			{ 1, 1 } };

	public static String numberImageURL(int num) {
		return PREFIX + num + ".png";
	}

	private int W, H;
	private boolean[][] mines;
    private int[][] numbers;
    public boolean[][] open;
    public boolean[][] flags;

	public MineSweeperData(int W, int H) {
		this.W = W;
		this.H = H;
		this.mines = new boolean[W][H];
        this.open = new boolean[W][H];
        this.flags = new boolean[W][H];
        this.numbers = new int[W][H];
        generateMines();
        calculateNumbers();
	}

	private void calculateNumbers() {
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (mines[i][j]) {
                    numbers[i][j] = -1;
                    continue;
                }
				
				for (int[] aD : D) {
                    int newX = i + aD[0];
                    int newY = j + aD[1];
                    if (inArea(newX, newY) && isMine(newX, newY)) {
                        numbers[i][j]++;
                    }
                }
			}
		}
	}

	private void generateMines() {
		int bombs = 0;
		for(int i = 0; i < mines.length; i++) {
			for(int j = 0; j < mines[i].length; j++) {
				mines[i][j] = Math.random() < 0.15;
				if(mines[i][j]) {bombs++;}
			}
		}
		System.out.println(bombs);
	}

	public int W() {
		return W;
	}

	public int H() {
		return H;
	}
	
	public boolean isMine(int x, int y) {
        if (inArea(x, y)) {
            return mines[x][y];
        }
        return false;
    }

	public boolean inArea(int x, int y) {
		return x >= 0 && x < W && y >= 0 && y < H;
	}
	
	public int getNumber(int x, int y) {
        return numbers[x][y];
    }

	public void gameOver() {
		for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                this.open[i][j] = true;
            }
        }
	}

	public void open(int x, int y) {
		open[x][y] = true;
        if (numbers[x][y] > 0) {
            return;
        }
        for (int[] aD : D) {
            int newX = x + aD[0];
            int newY = y + aD[1];
            if (inArea(newX, newY) && !isMine(newX, newY) && !open[newX][newY]) {
                open(newX, newY);
            }
        }
	}

	public void resetGame() {
        this.mines = new boolean[W][H];
        this.open = new boolean[W][H];
        this.flags = new boolean[W][H];
        this.numbers = new int[W][H];
        generateMines();
        calculateNumbers();
    }
}
