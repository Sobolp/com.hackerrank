package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SoBoLp on 3/27/16.
 */
public class ManasaAndStones {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String next = "";
        next = in.readLine();
        int T = Integer.parseInt(next.split(" ")[0]);
        for (int i = 0; i < T; i++) {
            next = in.readLine();
            int N = Integer.parseInt(next.split(" ")[0]) - 1;
            next = in.readLine();
            int A = Integer.parseInt(next.split(" ")[0]);
            next = in.readLine();
            int B = Integer.parseInt(next.split(" ")[0]);
            int Amin = Math.min(A, B);
            int Bmax = Math.max(A, B);
            int curr = Amin * N;
            int max = Bmax * N;
            int diff = Bmax - Amin;
            if (Amin == Bmax) System.out.println(curr);
            else {
                StringBuilder SB = new StringBuilder();
                while (curr <= max) {
                    SB.append(curr + " ");
                    curr += diff;
                }
                System.out.println(SB);
            }
        }
    }
}
