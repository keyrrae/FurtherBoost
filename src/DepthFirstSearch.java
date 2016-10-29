import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xuanwang on 10/28/16.
 */
public class DepthFirstSearch {

    public static void main(String[] args){
        char[][] grid = {{'1','0','0','0'},{'1','0','0','0'},{'0','0','1','1'},{'0','1','1','1'}};

        int size = islandSize(grid);
        List<String> res = addOperators("1232", 2);
        return;
    }

    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        if(num == null || num.length() == 0){
            return res;
        }
        helper(res, "", num, target, 0, 0);
        return res;
    }

    private static void helper(List<String> res, String path, String num, int target, int pos, long eval){
        if(pos == num.length()){
            if(eval == target){
                res.add(path);
                return;
            }
        }

        for(int i = pos; i<num.length(); i++){
            if(i!= pos && num.charAt(pos) == '0') break;
            long cur = Long.parseLong(num.substring(pos, i+1));
/*
            if(pos == 0){
                helper(res, path + cur, num, target, i + 1, eval + cur);
            }else{*/

                helper(res, path+'+'+cur, num, target, i+1, eval + cur);
                helper(res, path+'-'+cur, num, target, i+1, eval - cur);
                //helper(res, path+'*'+cur, num, target, i+1, eval - multed + cur *multed , cur*multed);
//            }
        }
    }

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

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        boolean []visited = new boolean[nums.length];
        permuteUniqueHelper(ans, new ArrayList<Integer>(), nums, visited);
        return ans;
    }

    private void permuteUniqueHelper(List<List<Integer>> ans, List<Integer> temp, int[] nums, boolean[] visited){
        if (temp.size() == nums.length){
            ans.add(new ArrayList<Integer>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            if(i>0 && nums[i] == nums[i-1] && !visited[i-1]){
                continue;
            }
            visited[i] = true;
            temp.add(nums[i]);
            permuteUniqueHelper(ans, temp, nums, visited);
            visited[i] = false;
            temp.remove(temp.size()-1);
        }
    }

    public static int islandSize(char[][] grid){
        if(grid == null || grid.length == 0 || grid[0]== null || grid[0].length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int max = 0;
        for(int i = 0; i < m;i++){
            for(int j = 0; j< n; j++){
                if(grid[i][j] == '1'){
                    max = Math.max(islandSizeHelper(i, j, grid), max);
                }
            }
        }
        return max;
    }

    private static int islandSizeHelper(int i, int j, char[][]grid){
        if(grid[i][j] == '0') return 0;

        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        int m = grid.length;
        int n = grid[0].length;
        grid[i][j] = '0';
        int res = 1;
        for(int[] dir: dirs){
            int x = i + dir[0];
            int y = j + dir[1];

            if(x < 0 || x > m-1 || y<0 || y > n-1) continue;
            res += islandSizeHelper(x, y, grid);
        }
        return res;
    }

    public boolean wordSearch(char[][] board, String word) {
        char[] w = word.toCharArray();
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[y].length; x++) {
                if (exist(board, y, x, w, 0)) return true;
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int y, int x, char[] word, int i) {
        if (i == word.length) return true;
        if (y<0 || x<0 || y == board.length || x == board[y].length) return false;
        if (board[y][x] != word[i]) return false;
        board[y][x] ^= 256;
        boolean exist = exist(board, y, x+1, word, i+1)
                || exist(board, y, x-1, word, i+1)
                || exist(board, y+1, x, word, i+1)
                || exist(board, y-1, x, word, i+1);
        board[y][x] ^= 256;
        return exist;
    }
}
