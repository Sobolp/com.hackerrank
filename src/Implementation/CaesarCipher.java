package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SoBoLp on 3/25/16.
 */
public class CaesarCipher {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String next = "";
        next = in.readLine();
        int N = Integer.parseInt(next.split(" ")[0]);
        String incom = in.readLine();
        next = in.readLine();
        int K = Integer.parseInt(next.split(" ")[0]);
        System.out.println(CC.rotate(incom, K));
    }

    private static class CC {
        public static String rotate(String str, int K) {
            StringBuilder sb = new StringBuilder();
            int chenge;
            if (K > 26) chenge = K % 26;
            else chenge = K;
            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                int ascii = (int) ch;
                char nch = ch;
                if (ascii >= 65 && ascii <= 90) {
                    int asciiNew = ascii + chenge;
                    if (asciiNew > 90)
                        asciiNew = 64 + asciiNew - 90;
                    nch = (char) asciiNew;
                }
                if (ascii >= 97 && ascii <= 122) {
                    int asciiNew = ascii + chenge;
                    if (asciiNew > 122)
                        asciiNew = 96 + asciiNew - 122;
                    nch = (char) asciiNew;
                }
                sb.append(nch);
//                || (ascii >= 97 && ascii <= 122)
            }
            return sb.toString();
        }
    }

}
