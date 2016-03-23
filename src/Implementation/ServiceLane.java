package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SoBoLp on 3/23/16.
 */
public class ServiceLane {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numTests = 0;
        String next = "";
        next = in.readLine();
        String[] arr = next.split(" ");
        int N = Integer.parseInt(arr[0]);   //the length of the freeway
        int T = Integer.parseInt(arr[1]);   //the number of test cases
        next = in.readLine();
        String[] arr1 = next.split(" ");
        int[] widtharr = new int[N];
        for (int i = 0; i < N; i++) {
            widtharr[i] = Integer.parseInt(arr1[i]);
        }
        SL servLine = new SL(widtharr);
        for (int i = 0; i < T; i++) {
            next = in.readLine();
            String[] arr2 = next.split(" ");
            System.out.println(servLine.getMin(Integer.parseInt(arr2[0]), Integer.parseInt(arr2[1])));
        }

    }

    private static class SL {
        private int[] array;

        public SL(int[] array) {
            this.array = array;
        }

        public int getMin(int A, int B) {
            int result = Integer.MAX_VALUE;
            for (int i = A; i <= B; i++) {
                if (array[i] < result) result = array[i];
            }
            return result;
        }
    }
}
