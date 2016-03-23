package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SoBoLp on 3/23/16.
 */
public class FindDigits {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numTests = 0;
        String next = "";
        next = in.readLine();
        numTests = Integer.parseInt(next.split(" ")[0]);
        for (int tc = 0; tc < numTests; tc++) {
            next = in.readLine();
            int N = Integer.parseInt(next.split(" ")[0]);
            String[] arr1 = next.split("");
            List<Integer> integerList = new ArrayList<>();
            for (String a:arr1) {
                integerList.add(Integer.parseInt(a));

            }
            FD test = new FD(integerList,N);
            System.out.println(test.numDividers());
        }
    }
    private static class FD{
        private List<Integer> digits;
        private int N;

        public FD(List<Integer> digits, int n) {
            this.digits = digits;
            N = n;
        }
        public int numDividers() {
           int count = 0;
            for (Integer i:digits) {
                if (i !=0)
                    if (N%i == 0) count++;
            }
            return count;
        }
    }
}
