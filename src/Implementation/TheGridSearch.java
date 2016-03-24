package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SoBoLp on 3/23/16.
 */
public class TheGridSearch {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int numTests = 0;
        String next = "";
        next = in.readLine();
        numTests = Integer.parseInt(next.split(" ")[0]);
        for (int tc = 0; tc < numTests; tc++) {
            Map<Integer, String> arrayG = new HashMap<>();
            next = in.readLine();
            String[] arr1 = next.split(" ");
            int R = Integer.parseInt(arr1[0]);
            int C = Integer.parseInt(arr1[1]);
            for (Integer i = 0; i < R; i++) {
                next = in.readLine();
                String[] arr2 = next.split(" ");
                arrayG.put(i, arr2[0]);
            }
            Map<Integer, String> arrayP = new HashMap<>();
            next = in.readLine();
            arr1 = next.split(" ");
            int r = Integer.parseInt(arr1[0]);
            int c = Integer.parseInt(arr1[1]);
            for (Integer i = 0; i < r; i++) {
                next = in.readLine();
                String[] arr2 = next.split(" ");
                arrayP.put(i, arr2[0]);
            }
            TGS test = new TGS(arrayG, arrayP);
            System.out.println(test.isPinG());
        }


    }

    private static class TGS {
        private Map<Integer, String> arrayG;
        private Map<Integer, String> arrayP;

        public TGS(Map<Integer, String> arrayG, Map<Integer, String> arrayP) {
            this.arrayG = arrayG;
            this.arrayP = arrayP;
        }

        public String isPinG() {
            for (int i = 0; i < arrayG.keySet().size(); i++) {
                int indexFrom = checkFirst(i, 0);
                while ((indexFrom + arrayP.get(0).length() <= arrayG.get(0).length()) && indexFrom >= 0) {
                    if (indexFrom >= 0) {
                        if (checkAll(indexFrom, i)) return "YES";
                    }
                    indexFrom = checkFirst(i, ++indexFrom);
                }
            }
            return "NO";
        }

        private int checkFirst(int indexStr, int indexFrom) {
            return (arrayG.get(indexStr).indexOf(arrayP.get(0), indexFrom));
        }

        private boolean checkNext(int fromIndexInStr, int indexStr, String substr) {
            return (arrayG.get(indexStr).indexOf(substr, fromIndexInStr) == fromIndexInStr);
        }

        private boolean checkAll(int indexFrom, int indexStr) {
            for (int j = 1; j < arrayP.keySet().size(); j++) {
                if (!checkNext(indexFrom, indexStr + j, arrayP.get(j))) return false;
            }
            return true;
        }
    }
}
