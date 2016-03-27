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
        List<String> skills = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            skills.add(in.readLine());
        }
        BinaryMatrix BM = new BinaryMatrix(N, skills);
//        System.out.println(BM);
        System.out.println(BM.getMax());
        System.out.println(BM.getCount());
    }

    private static class BinaryMatrix {
        private String[][] matrix;
        private int N;
        private int max = 0;
        private int count = 0;

        public BinaryMatrix(int n) {
            N = n;
            this.matrix = new String[N][N];
        }

        public BinaryMatrix(int n, List<String> list) {
            this(n);
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = bitwiseOR(list.get(i), list.get(j));
                    int nextMax = getMax(matrix[i][j]);
                    if (max < nextMax) {
                        max = nextMax;
                        count = 1;
                    } else if (max == getMax(matrix[i][j])) count++;
                }
        }

        private String bitwiseOR(String A, String B) {
            StringBuilder SB = new StringBuilder();
            for (int i = 0; i < A.length(); i++) {
                int a = Integer.parseInt(Character.toString(A.charAt(i)), 2);
                int b = Integer.parseInt(Character.toString(B.charAt(i)), 2);
                int c = a | b;
                SB.append(Integer.toBinaryString(c));
            }
            return SB.toString();
        }

        public int getMax() {
            return max;
        }

        private int getMax(String str) {
            int result = 0;
            char one = '1';
            for (char s : str.toCharArray())
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
