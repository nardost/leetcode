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
        displayBoard(board);
    }
    public void solveSudoku(char[][] board) {

    }
    private static void displayBoard(char[][] board) {
        final int N = board[0].length;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < N; i++) {
            sb.append("\n\t[");
            for(int j = 0; j < N; j++) {
                sb.append(board[i][j]);
                sb.append("  ");
            }
            sb.replace(sb.length() - 2, sb.length(), "");
            sb.append("]");
        }
        sb.append("\n]");
        System.out.println(sb.toString());
    }
}
class Cell {
    int x;
    int y;
    Cell(int i, int j) {
        this.x = i;
        this.y = j;
    }
    public static Cell containingBox(Cell c) {
        Cell box = new Cell(c.x, c.y);
        return box;
    }
}
