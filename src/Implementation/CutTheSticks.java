package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SoBoLp on 3/23/16.
 */
public class CutTheSticks {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String next = "";
        next = in.readLine();
        int N = Integer.parseInt(next.split(" ")[0]);
        next = in.readLine();
        String[] arr1 = next.split(" ");
        List<Integer> langhtArr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            langhtArr.add(Integer.parseInt(arr1[i]));
        }
        CTS.numOfSticks(langhtArr);
    }

    private static class CTS {
        public static List<Integer> numOfSticks(List<Integer> intArr) {
            List<Integer> result = new ArrayList<>();
            Integer minStick = minFromList(intArr);
            System.out.println(intArr.size());
            for (Integer i : intArr) {
                Integer res = i - minStick;
                if (res > 0) result.add(res);
            }
            if (result.size() != 0) numOfSticks(result);
            return null;
        }

        public static Integer minFromList(List<Integer> list) {
            Integer result = Integer.MAX_VALUE;
            for (Integer i : list) {
                if (i < result) result = i;
            }
            return result;
        }
    }
}
