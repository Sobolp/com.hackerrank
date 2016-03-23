package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SoBoLp on 3/23/16.
 */
public class SherlockAndSquares {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numTests = 0;
        String next = "";
        next = in.readLine();
        numTests = Integer.parseInt(next.split(" ")[0]);
        for (int tc = 0; tc < numTests; tc++) {
            next = in.readLine();
            String[] arr = next.split(" ");
            int A = Integer.parseInt(arr[0]);
            int B = Integer.parseInt(arr[1]);
            SAS test = new SAS(A,B);
            System.out.println(test.numSquares());
        }
    }
    private static class SAS {
        private int count;
        public SAS(int a, int b) {
            int A = (int) Math.sqrt(a);
            int B = (int) Math.sqrt(b);
            count = 0;
            if (A*A != a) A++;
            if (B*B <= b) B++;
//            System.out.println(A+" "+B);
            for (int i = A; i < B; i++)
                count++;
        }
        public int numSquares (){
            return count;
        }
    }
}
