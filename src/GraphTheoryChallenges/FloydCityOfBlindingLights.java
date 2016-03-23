package GraphTheoryChallenges;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by SoBoLp on 3/18/16.
 *
 *https://www.hackerrank.com/challenges/floyd-city-of-blinding-lights
 *
 */
public class FloydCityOfBlindingLights {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String next = in.readLine();
        String[] arr = next.split(" ");
        int N = Integer.parseInt(arr[0]);
        int M = Integer.parseInt(arr[1]);
        Graph testCasegr = new Graph(N);
        for (int i = 0; i < M; i++) {
            next = in.readLine();
            String[] arr1 = next.split(" ");
            testCasegr.setDistance(Integer.parseInt(arr1[0])
                    , Integer.parseInt(arr1[1])
                    , Integer.parseInt(arr1[2]));
        }
        next = in.readLine();
        int Q = Integer.parseInt(next.split(" ")[0]);
        ArrayList<ArrayList<Integer>> qustions = new ArrayList<>();
        for (int i = 0; i < Q; i++) {
            next = in.readLine();
            String[] arr1 = next.split(" ");
            ArrayList<Integer> qust = new ArrayList<>();
            qust.add(Integer.parseInt(arr1[0]));
            qust.add(Integer.parseInt(arr1[1]));
            qustions.add(qust);
        }
        testCasegr.makeFloyd();
        for (ArrayList<Integer> qustion : qustions) {
            long result = testCasegr.getDistance(qustion.get(0), qustion.get(1));
            if (result != Integer.MAX_VALUE)
                System.out.println(result);
            else
                System.out.println("-1");
        }

    }

    private static class Graph {
        private long[][] matrix;
        private int nodesNum;

        public Graph(int N) {
            nodesNum = N + 1;
            this.matrix = new long[nodesNum][nodesNum];
            for (int i = 1; i < nodesNum; i++)
                for (int j = 1; j < nodesNum; j++)
                    if (i != j)
                        matrix[i][j] = Integer.MAX_VALUE;
                    else
                        matrix[i][j] = 0;
        }

        public long getDistance(int from, int to) {
            return matrix[from][to];
        }

        public void setDistance(int from, int to, long distance) {
            matrix[from][to] = distance;
        }

        public void makeFloyd() {
            /*
            for k = 1 to n
            for i = 1 to n
            for j = 1 to n
            W[i][j] = min(W[i][j], W[i][k] + W[k][j])
            */
            for (int k = 1; k < nodesNum; k++)
                for (int i = 1; i < nodesNum; i++)
                    for (int j = 1; j < nodesNum; j++) {
                        long newDist = matrix[i][k] + matrix[k][j];
                        if (matrix[i][j] > newDist)
                            matrix[i][j] = newDist;
                    }
        }
    }
}
