package droid.yutani.com.conwaysgameoflife;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetriDish {

    // This class represents the canvas, because cells grow
    // in petri dishes 🙃

    public static final Random RANDOM = new Random();

    public int width;
    public int height;

    private Cell[][] cells;

    public PetriDish(int width, int height) {
        this.width = width;
        this.height = height;

        cells = new Cell[width][height];

        generateDish();
    }

    private void generateDish() {
        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                cells[row][col] = new Cell(row, col, RANDOM.nextBoolean());
            }
        }
    }

    public Cell getCell(int row, int col) {
        return cells[row][col];
    }

    public int neighbours(int row, int col) {
        int neighbor = 0;

        for (int r = row - 1; r <= row + 1; r++) {
            for (int c = col - 1; c <= col + 1; c++) {
                if ((r != row || c != col)
                        && r >= 0
                        && r < width
                        && c >= 0
                        && c < height
                ) {
                    Cell cell = cells[r][c];

                    if (cell.isAlive) {
                        neighbor++;
                    }
                }
            }
        }
        return neighbor;
    }

    public void cellBehaviour() {
        List<Cell> liveCells = new ArrayList<>();
        List<Cell> deadCells = new ArrayList<>();

        for (int row = 0; row < width; row++) {
            for (int col = 0; col < height; col++) {
                Cell cell = cells[row][col];

                int numNeighbours = neighbours(cell.xx, cell.yy);

                // The following method covers these rules:
                // 1. Any live cell with fewer than two live neighbors dies,
                // as if by under population.
                // 3. Any live cell with more than three live neighbors dies,
                // as if by overpopulation.

                if (cell.isAlive && numNeighbours < 2 || numNeighbours > 3) {
                    deadCells.add(cell);
                }

                // The following method covers these rules:
                // 2. Any live cell with two or three live neighbors lives on
                // to the next generation.
                // 4. Any dead cell with exactly three live neighbors becomes a live cell,
                // as if by reproduction.

                if ((cell.isAlive && (numNeighbours == 3 || numNeighbours == 2)) ||
                        (!cell.isAlive && numNeighbours == 3)) {
                    liveCells.add(cell);
                }
            }
        }

        for (Cell cell : liveCells) {
            cell.switchState();
        }

        for (Cell cell : deadCells) {
            cell.setDead();
        }
    }
}
