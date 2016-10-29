/**
 * Created by xuanwang on 10/28/16.
 */
public class DepthFirstSearch {
    public int numIslands(char[][] grid) {
        if(grid == null || grid.length == 0 || grid[0]== null || grid[0].length == 0){
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        int count = 0;
        for(int i = 0; i < m;i++){
            for(int j = 0; j< n; j++){
                if(grid[i][j] == '1'){
                    numIslandsHelper(i, j, grid);
                    count++;
                }
            }
        }
        return count;
    }

    private void numIslandsHelper(int i, int j, char[][] grid){
        if(grid[i][j] == '0') return;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int m = grid.length;
        int n = grid[0].length;
        grid[i][j] = '0';
        for(int[] dir: dirs){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x < 0 || x > m-1 || y<0 || y > n-1) continue;
            numIslandsHelper(x, y, grid);
        }
    }

    //IsdandSize();
}
