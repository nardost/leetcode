import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SudokuSolver {

    public static void main(String[] args) {
        final SudokuSolver solver = new SudokuSolver();
        final char[][] board = new char[][] {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };
        solver.solveSudoku(board);
    }
    public void solveSudoku(char[][] board) {
        Board BOARD = Board.getInstance(board);
        System.out.println(BOARD.toString());
        BOARD.displayMetrics();
    }
}

class Board {

    public char[][] board;

    private final Map<Cell, Integer> blank = new HashMap<>();

    /*
     * make singleton
     */
    private static Board INSTANCE = null;

    private Board(char[][] board) {
        this.board = board;
        Set<Cell> cells = getEmptyCells();
        System.out.println("Empty Cells = " + cells.size());
        cells.forEach(cell -> {
            final int r = rowDensity(cell.row);
            final int c = colDensity(cell.col);
            final int b = boxDensity(cell.row, cell.col);
            blank.put(cell, r + c + b);
        });
    }

    public static Board getInstance(char[][] board) {
        if(INSTANCE == null) {
            synchronized (Board.class) {
                if(INSTANCE == null) {
                    INSTANCE = new Board(board);
                }
            }
        }
        return INSTANCE;
    }

    /**
     * When a value is determined for a cell, remove the cell from
     * the map of blank cells and fill the board with the value.
     */
    private void fillCell(Cell cell, final int value) {
        blank.remove(cell);
        board[cell.row][cell.col] = Character.forDigit(value, 10);
    }

    public Set<Cell> getEmptyCells() {
        Set<Cell> emptyCells = new HashSet<>();
        for(int row = 0; row < board.length; row++) {
            for(int col = 0; col < board.length; col++) {
                if(isEmpty(row,col)) {
                    emptyCells.add(new Cell(row, col));
                }
            }
        }
        return emptyCells;
    }

    public int rowDensity(final int row) {
        final int N = dimension();
        int density = 0;
        for(int col = 0; col < N; col++) {
            if(isEmpty(row,col)) {
                density++;
            }
        }
        return density;
    }

    public int colDensity(final int col) {
        final int N = dimension();
        int density = 0;
        for(int row = 0; row < N; row++) {
            if(isEmpty(row,col)) {
                density++;
            }
        }
        return density;
    }
    public int boxDensity(final int x, final int y) {
        final int N = dimension();
        final int n = (int) Math.sqrt(N);
        final Cell cell = containingBox(new Cell(x, y));
        int density = 0;
        for(int row = cell.row; row < cell.row + n; row++) {
            for(int col = cell.col; col < cell.col + n; col++) {
                if(isEmpty(row,col)) {
                    density++;
                }
            }
        }
        return density;
    }

    public int dimension() {
        return board.length;
    }

    public Cell containingBox(Cell cell) {
        final int N = dimension();
        final int n = (int) Math.sqrt(N);
        return new Cell(cell.row - (cell.row % n), n * (cell.col / n));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (char[] chars : board) {
            sb.append("\n[");
            for (char c : chars) {
                sb.append(c);
                sb.append("  ");
            }
            sb.replace(sb.length() - 2, sb.length(), "");
            sb.append("]");
        }
        return sb.toString();
    }

    private boolean isEmpty(final int row, final int col) {
        final char EMPTY_MARKER = '.';
        return board[row][col] == EMPTY_MARKER;
    }

    public void displayMetrics() {
        System.out.println(blank.size());
        StringBuilder sb = new StringBuilder();
        blank.forEach( (cell, metrics) -> {
            sb.append(cell);
            sb.append("->");
            sb.append(metrics);
            sb.append("\n");
        });
        System.out.println(sb.toString());
    }
}

class Cell {

    public int row;
    public int col;

    public Cell(int i, int j) {
        this.row = i;
        this.col = j;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Cell)) return false;
        return (((Cell) obj).row == row) && (((Cell) obj).col == col);
    }

    @Override
    public String toString() {
        return "(" + row + ", " + col + ")";
    }

    @Override
    public int hashCode() {
        return ("(" + row + "," + col + ")").hashCode();
    }
}

