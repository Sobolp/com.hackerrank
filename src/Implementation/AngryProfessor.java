package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SoBoLp on 3/23/16.
 */
public class AngryProfessor {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numTests = 0;
        String next = "";
        next = in.readLine();
        numTests = Integer.parseInt(next.split(" ")[0]);
        for (int tc = 0; tc < numTests; tc++) {
            next = in.readLine();
            String[] arr = next.split(" ");
            int N = Integer.parseInt(arr[0]);
            int K = Integer.parseInt(arr[1]);
            next = in.readLine();
            String[] arr1 = next.split(" ");
            List<Integer> integerList = new ArrayList<>();
            for (String a:arr1) {
                integerList.add(Integer.parseInt(a));

            }
            AP test = new AP(N,K,integerList);
            System.out.println(test.isCanceled());
        }


    }

    private static class AP {
        private int N; //students in the class
        private int K; //the cancelation threshold
        private List<Integer> times;

        public AP(int n, int k, List<Integer> times) {
            N = n;
            K = k;
            this.times = times;
        }
        public String isCanceled(){
            int count = 0;
            for (Integer t:times) {
                if (t <=0) count++;
            }
            if (count < K) return "YES";
            else return "NO";
        }
    }
}
