package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SoBoLp on 3/27/16.
 */
public class ACM_ICPC_Team {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String next = "";
        next = in.readLine();
        String[] arr = next.split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        List<Long> skills = new ArrayList<>();
        for (int i = 0; i < N; i++) {

            long binary = Long.parseLong(in.readLine(), 2);
            skills.add(binary);
        }
        BinaryMatrix BM = new BinaryMatrix(N, skills);
//        System.out.println(BM);
        System.out.println(BM.getMax());
        System.out.println(BM.getCount());
    }

    private static class BinaryMatrix {
        private long[][] matrix;
        private int N;
        private long max = 0;
        private int count = 0;

        public BinaryMatrix(int n) {
            N = n;
            this.matrix = new long[N][N];
        }

        public BinaryMatrix(int n, List<Long> list) {
            this(n);
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = list.get(i) | list.get(j);
                    if (max < matrix[i][j]) {
                        max = matrix[i][j];
                        count = 1;
                    } else if (max == matrix[i][j]) count++;
                }
        }

        public int getMax() {
            int result = 0;
            char one = '1';
            for (char s : Long.toBinaryString(max).toCharArray())
                if (s == '1') result++;
            return result;
        }

        public int getCount() {
            return count / 2;
        }

        @Override
        public String toString() {
            StringBuilder SB = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++)
//                    SB.append(Integer.toBinaryString(matrix[i][j])+"\t");
                    SB.append(matrix[i][j] + "\t");
                SB.append("\n");
            }

            return SB.toString();
        }
    }
}
