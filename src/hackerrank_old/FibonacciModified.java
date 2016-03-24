package hackerrank_old;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by SoBoLp on 3/15/16.
 */
public class FibonacciModified {

    private static BigInteger getNext(BigInteger first, BigInteger seckond){
        return seckond.multiply(seckond).add(first);
    }
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);

        BigInteger first = new BigInteger (Integer.toString(in.nextInt()));
        BigInteger seck = new BigInteger (Integer.toString(in.nextInt()));
        int N = in.nextInt();
        BigInteger next = BigInteger.ZERO;
        for (int i=2;i<N;i++){
            next = getNext(first, seck);
            first = seck;
            seck = next;
        }
        System.out.println(next);
    }
}
