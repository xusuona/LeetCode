class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][10];
        int[][] cols = new int[9][10];
        int[][] boxs = new int[9][10];
        for (int i = 0; i < 9; ++i) {
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] == '.') continue;
                int num = board[i][j] - '0';
                rows[i][num]++;
                cols[j][num]++;
                boxs[(i/3)*3 + j/3][num]++;
                if (rows[i][num] + cols[j][num] + boxs[(i/3)*3 + j/3][num] > 3) {
                    return false;
                }
            }
        }
        return true;
    }
}
