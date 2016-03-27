package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SoBoLp on 3/27/16.
 */
public class TaumAndB_Day {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        for (int i = 0; i < N; i++) {
            String next = in.readLine();
            String[] arr = next.split(" ");
            int B = Integer.parseInt(arr[0]);
            int W = Integer.parseInt(arr[1]);
            next = in.readLine();
            arr = next.split(" ");
            int X = Integer.parseInt(arr[0]);
            int Y = Integer.parseInt(arr[1]);
            int Z = Integer.parseInt(arr[2]);
            BDay test = new BDay(B, W, X, Y, Z);
            System.out.println(test.culc());
        }
    }

    private static class BDay {
        private long black;
        private long white;
        private long Bcost;
        private long Wcost;
        private long convertCost;

        public BDay(int black, int white, int bcost, int wcost, int convertCost) {
            this.black = black;
            this.white = white;
            Bcost = bcost;
            Wcost = wcost;
            this.convertCost = convertCost;
        }

        public long culc() {
            if (Bcost == Wcost) return (Bcost * (black + white));
            if (Bcost + convertCost < Wcost) return (Bcost * (black + white) + convertCost * white);
            else if (Wcost + convertCost < Bcost) return (Wcost * (black + white) + convertCost * black);
            else return (Wcost * white + Bcost * black);
        }
    }

}
