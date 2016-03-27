package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by SoBoLp on 3/27/16.
 */
public class ModifiedKaprekarNumbers {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int P = Integer.parseInt(in.readLine().split(" ")[0]);
        int Q = Integer.parseInt(in.readLine().split(" ")[0]);
        System.out.println(MKN.getMKN(P, Q));
    }

    public static class MKN {
        public static String getMKN(int P, int Q) {
            StringBuilder SB = new StringBuilder();
            for (int i = P; i <= Q; i++)
                if (isMKN(i)) SB.append(i + " ");
            if (SB.toString().compareTo("") == 0) return "INVALID RANGE";
            return SB.toString();
        }

        private static boolean isMKN(Integer i) {
            int d = i.toString().length();
            BigInteger square = new BigInteger(i.toString());
            square = square.multiply(square);
            String squareStr = square.toString();
            Long r = Long.valueOf(squareStr.substring(squareStr.length() - d));
            Long l = Long.valueOf(0);
            if (squareStr.length() > d)
                l = Long.valueOf(squareStr.substring(0, squareStr.length() - d));
            return (r + l == i);
        }
    }
}
