/**
 * Created by xuanwang on 10/28/16.
 */
public class DynamicProgramming {



    public static int numDecodings(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int[] dp = new int[s.length()+1];

        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for(int i = 2; i<= s.length();i++){
            int first = s.charAt(i-2) - '0';
            int second = s.charAt(i-1) - '0';

            if(first == 2 && second >=0 && second <=6){
                dp[i] += dp[i-2];
            }else if(first == 1){
                dp[i] += dp[i-2];
            }

            if(second > 0 && second <= 9){
                dp[i] += dp[i-1];
            }
        }

        return dp[s.length()];
    }
}
