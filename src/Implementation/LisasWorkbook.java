package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by SoBoLp on 3/23/16.
 */
public class LisasWorkbook {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String next;
        next = in.readLine();
        String[] arr = next.split(" ");
        int N = Integer.parseInt(arr[0]);
        int K = Integer.parseInt(arr[1]);
        next = in.readLine();
        String[] arr1 = next.split(" ");
        List<Integer> intArr = new ArrayList<>();
        for (String s : arr1) {
            intArr.add(Integer.parseInt(s));
        }
        LW test = new LW(N, K, intArr);

        System.out.println(test.numSpetial());
    }

    private static class LW {
        private Map<Integer, Set<Integer>> book;

        public LW(int N, int K, List<Integer> arr) {
            book = new HashMap<>();
            int list = 0;
            for (Integer i : arr) {
                int num = 0;
                Set<Integer> probl = new HashSet<>();
                while (num != i) {
                    num++;
                    probl.add(num);
                    if (num % K == 0) {
                        list++;
                        book.put(list, probl);
                        probl = new HashSet<>();
                    }
                }
                if (num % K != 0) {
                    list++;
                    book.put(list, probl);
                }
            }
        }

        public int numSpetial() {
            int count = 0;
            for (Integer str : book.keySet()) {
                Set<Integer> probl = book.get(str);
                if (probl.contains(str))
                    count++;
            }
            return count;
        }
    }
}
