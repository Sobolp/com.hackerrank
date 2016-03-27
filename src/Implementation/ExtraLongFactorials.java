package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

/**
 * Created by SoBoLp on 3/27/16.
 */
public class ExtraLongFactorials {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(in.readLine());
        System.out.println(Factorials.getFact(N));
    }

    private static class Factorials {
        public static BigInteger getFact(Integer N) {
            BigInteger result = new BigInteger(N.toString());
            for (int i = 1; i < N; i++)
                result = result.multiply(BigInteger.valueOf(i));
            return result;
        }
    }
}
