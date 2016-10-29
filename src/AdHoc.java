/**
 * Created by xuanwang on 10/28/16.
 */
public class AdHoc {

    public static String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        int i = a.length() - 1, j = b.length() -1, carry = 0;
        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (j >= 0) sum += b.charAt(j--) - '0';
            if (i >= 0) sum += a.charAt(i--) - '0';
            sb.append(sum % 2);
            carry = sum / 2;
        }
        if (carry != 0) sb.append(carry);
        return sb.reverse().toString();
    }

    public static int factorialTrailingZeroes(int n) {
        int count = 0;
        while(n != 0){
            n /= 5;
            count += n;
        }
        return count;
    }

    /*
    public static int findCelebrity(int n) {
        int candidate = 0;
        for(int i = 1; i <n; i++){
            if(knows(candidate, i)){
                candidate = i;
            }
        }

        for(int i = 0; i < n; i++){
            if(i != candidate && ( knows(candidate, i) || !knows(i, candidate)) )return -1;
        }
        return candidate;
    }
    */

    /*
    public int read4I(char[] buf, int n) {

        boolean eof = false;
        char[] tmp = new char[4];
        int total = 0;

        while (!eof && total < n){
            int count = read4(tmp);
            eof = count < 4;

            count = Math.min(count, n - total);
            for (int i = 0; i < count ; i++){
                buf[total++] = tmp[i];
            }
        }
        return total;
    }

    private int buffPtr = 0;
    private int buffCnt = 0;
    private char[] buff = new char[4];
    public int read4II(char[] buf, int n) {
        int ptr = 0;
        while (ptr < n) {
            if (buffPtr == 0) {
                buffCnt = read4(buff);
            }
            if (buffCnt == 0) break;
            while (ptr < n && buffPtr < buffCnt) {
                buf[ptr++] = buff[buffPtr++];
            }
            if (buffPtr >= buffCnt) buffPtr = 0;
        }
        return ptr;
    }
    */
}
