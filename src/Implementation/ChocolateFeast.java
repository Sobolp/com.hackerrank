package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SoBoLp on 3/23/16.
 */
public class ChocolateFeast {


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numTests;
        String next;
        next = in.readLine();
        numTests = Integer.parseInt(next.split(" ")[0]);
        for (int tc = 0; tc < numTests; tc++) {
            next = in.readLine();
            String[] arr1 = next.split(" ");
            int N = Integer.parseInt(arr1[0]);
            int C = Integer.parseInt(arr1[1]);
            int M = Integer.parseInt(arr1[2]);
            System.out.println(CF.numChoco(N, C, M));
        }
    }

    private static class CF {
        public static int numChoco(int N, int C, int M) {
            int wrappers = N / C;
            int result = wrappers;
            while (wrappers >= M) {
                wrappers -= M;
                result++;
                wrappers++;
            }
            return result;
        }
    }
}
