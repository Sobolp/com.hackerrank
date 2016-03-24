package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SoBoLp on 3/24/16.
 */
public class CavityMap {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String next = "";
        next = in.readLine();
        int n = Integer.parseInt(next.split(" ")[0]);
        Map<Integer, String[]> testMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            next = in.readLine();
            String[] arr = next.split("");
            testMap.put(i, arr);
        }
        CM test = new CM(testMap, n);
        CM result = new CM(test.cavity(), n);
        System.out.print(result);
    }

    private static class CM {
        private Map<Integer, String[]> map;
        private int n;

        public CM(Map<Integer, String[]> map, int n) {
            this.map = map;
            this.n = n;
        }

        public Map<Integer, String[]> cavity() {
            Map<Integer, String[]> result = new HashMap<>();
            result.put(0, map.get(0));
            result.put(n - 1, map.get(n - 1));
            for (int i = 1; i < n - 1; i++) {
                result.put(i, getMarkStr(map.get(i), i));
            }
            return result;
        }

        public String[] getMarkStr(String[] arrStr, int strIdx) {
            String[] result = new String[n];
            result[0] = arrStr[0];
            result[n - 1] = arrStr[n - 1];
            for (int i = 1; i < n - 1; i++) {
                if (Integer.parseInt(arrStr[i]) > Integer.parseInt(arrStr[i - 1]) &&
                        Integer.parseInt(arrStr[i]) > Integer.parseInt(arrStr[i + 1]) &&
                        Integer.parseInt(arrStr[i]) > Integer.parseInt(getStrByCoord(strIdx - 1, i)) &&
                        Integer.parseInt(arrStr[i]) > Integer.parseInt(getStrByCoord(strIdx + 1, i)))
                    result[i] = "X";
                else result[i] = arrStr[i];
            }
            return result;
        }

        private String getStrByCoord(int idxStr, int idxInStr) {
            return (map.get(idxStr)[idxInStr]);
        }

        @Override
        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer i : map.keySet()) {
                for (String s : map.get(i)) {
                    stringBuilder.append(s);
                }
                stringBuilder.append("\n");
            }
            return stringBuilder.toString();
        }
    }
}
