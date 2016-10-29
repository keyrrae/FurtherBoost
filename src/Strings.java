import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuanwang on 10/28/16.
 */
public class Strings {

    public static String countAndSay(int n) {
        if(n <= 1) return "1";
        String ans = "1";

        for(int i = 2; i <= n; i++){
            System.out.println(ans);
            int count = 1;
            char c = ans.charAt(0);
            StringBuilder sb = new StringBuilder();
            for(int j = 1; j < ans.length(); j++){
                if(ans.charAt(j) != c){
                    sb.append(count);
                    sb.append(c);
                    count = 1;
                    c = ans.charAt(j);
                }else{
                    count++;
                }
            }
            sb.append(count); sb.append(c);
            ans = sb.toString();
        }
        return ans;
    }

    private static final String[] LESS_THAN_20 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] TENS = {"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private static final String[] THOUSANDS = {"", "Thousand", "Million", "Billion"};

    public static String numberToWords(int num) {
        if (num == 0) return "Zero";

        int i = 0;
        String words = "";

        while (num > 0) {
            if (num % 1000 != 0)
                words = numToWordsHelper(num % 1000) +THOUSANDS[i] + " " + words;
            num /= 1000;
            i++;
        }

        return words.trim();
    }

    private static String numToWordsHelper(int num) {
        if (num == 0)
            return "";
        else if (num < 20)
            return LESS_THAN_20[num] + " ";
        else if (num < 100)
            return TENS[num / 10] + " " + numToWordsHelper(num % 10);
        else
            return LESS_THAN_20[num / 100] + " Hundred " + numToWordsHelper(num % 100);
    }

    public static int strStr(String s, String t) {
        int l1 = s.length(), l2 = t.length();
        if(l1<l2){
            return -1;
        }
        if(l2 == 0){
            return 0;
        }
        for(int i =0; i < s.length()- t.length()+1;i++){
            boolean found = true;
            for(int j = 0; j < t.length();j++){
                if(s.charAt(i+j) != t.charAt(j)){
                    found = false;
                    break;
                }
            }
            if(found) return i;
        }
        return -1;
    }

    public String minWindowSubString(String s, String t) {

        Map<Character, Integer> m = new HashMap<>();

        for (char c : t.toCharArray()) {
            m.put(c, m.getOrDefault(c, 0) + 1);
        }
        int minlen = s.length();
        String win = "";
        for (int i = 0; i < s.length(); i++) {
            Map<Character, Integer> map = new HashMap<>(m);
            for (int j = i; j < Math.min(i + minlen, s.length()); j++) {
                char c = s.charAt(j);
                if (!map.containsKey(c)) {
                    continue;
                }
                int cnt = map.get(c);
                if (cnt == 1) {
                    map.remove(c);
                } else {
                    map.put(c, map.get(c) - 1);
                }

                if (map.isEmpty()) {
                    minlen = j - i + 1;
                    win = s.substring(i, j + 1);
                }

            }
        }
        return win;
    }

}
