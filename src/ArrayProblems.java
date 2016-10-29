/**
 * Created by xuanwang on 10/28/16.
 */
public class ArrayProblems {
    public static void mergeTwoSortedArrays(int A[], int m, int B[], int n) {
        int i=m-1;
        int j=n-1;
        int k = m+n-1;
        while(j >=0 && i >=0){
            if(A[i] > B[j]) A[k--] = A[i--];
            else A[k--] = B[j--];
        }
        while(j >= 0) A[k--] = B[j--];
    }
}
