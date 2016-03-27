package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.BitSet;
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
        List<BitSet> skills = new ArrayList<>();
        for (int i = 0; i < N; i++) {

            BigInteger binary = new BigInteger(in.readLine(), 2);
            skills.add(BitSet.valueOf(binary.toByteArray()));
        }
        BinaryMatrix BM = new BinaryMatrix(N, skills);
//        System.out.println(BM);
        System.out.println(BM.getMax());
        System.out.println(BM.getCount());
    }

    private static class BinaryMatrix {
        private BitSet[][] matrix;
        private int N;
        private int max = 0;
        private int count = 0;

        public BinaryMatrix(int n) {
            N = n;
            this.matrix = new BitSet[N][N];
        }

        public BinaryMatrix(int n, List<BitSet> list) {
            this(n);
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++) {
                    matrix[i][j] = (BitSet) list.get(i).clone();
                    matrix[i][j].or(list.get(j));
                    int card = matrix[i][j].cardinality();
                    if (max < card) {
                        max = card;
                        count = 1;
                    } else if (max == card) count++;
                }
        }

        public int getMax() {

            return max;
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
