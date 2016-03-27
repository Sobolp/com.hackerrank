package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by SoBoLp on 3/27/16.
 */
public class TheTimeInWords {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int H = Integer.parseInt(in.readLine().split(" ")[0]);
        int MM = Integer.parseInt(in.readLine().split(" ")[0]);
        TiW test = new TiW();
        System.out.println(test.getTiW(H, MM));
    }

    private static class TiW {
        private HashMap<Integer, String> hours;
        private HashMap<Integer, String> minutes;

        public TiW() {
            this.hours = new HashMap<>();
            this.minutes = new HashMap<>();
            hours.put(1, "one");
            hours.put(2, "two");
            hours.put(3, "three");
            hours.put(4, "four");
            hours.put(5, "five");
            hours.put(6, "six");
            hours.put(7, "seven");
            hours.put(8, "eight");
            hours.put(9, "nine");
            hours.put(10, "ten");
            hours.put(11, "eleven");
            hours.put(12, "twelve");

            minutes.put(1, "one minute to");
            minutes.put(2, "two minutes past");
            minutes.put(3, "three minutes past");
            minutes.put(4, "four minutes past");
            minutes.put(5, "five minutes past");
            minutes.put(6, "six minutes past");
            minutes.put(7, "seven minutes past");
            minutes.put(8, "eight minutes past");
            minutes.put(9, "nine minutes past");
            minutes.put(10, "ten minutes past");
            minutes.put(11, "eleven minutes past");
            minutes.put(12, "twelve minutes past");
            minutes.put(13, "thirteen minutes past");
            minutes.put(14, "fifteen minutes past");
            minutes.put(15, "quarter past");
            minutes.put(16, "sixteen minutes past");
            minutes.put(17, "seventeen minutes past");
            minutes.put(18, "eighteen minutes past");
            minutes.put(19, "nineteen minutes past");
            minutes.put(20, "twenty minutes past");
            minutes.put(21, "twenty one minutes past");
            minutes.put(22, "twenty two minutes past");
            minutes.put(23, "twenty three minutes past");
            minutes.put(24, "twenty four minutes past");
            minutes.put(25, "twenty five minutes past");
            minutes.put(26, "twenty six minutes past");
            minutes.put(27, "twenty seven minutes past");
            minutes.put(28, "twenty eight minutes past");
            minutes.put(29, "twenty nine minutes past");
            minutes.put(30, "half past");

            minutes.put(59, "one minute to");
            minutes.put(58, "two minutes to");
            minutes.put(57, "three minutes to");
            minutes.put(56, "four minutes to");
            minutes.put(55, "five minutes to");
            minutes.put(54, "six minutes to");
            minutes.put(53, "seven minutes to");
            minutes.put(52, "eight minutes to");
            minutes.put(51, "nine minutes to");
            minutes.put(50, "ten minutes to");
            minutes.put(49, "eleven minutes to");
            minutes.put(48, "twelve minutes to");
            minutes.put(47, "thirteen minutes to");
            minutes.put(46, "fifteen minutes to");
            minutes.put(45, "quarter to");
            minutes.put(44, "sixteen minutes to");
            minutes.put(43, "seventeen minutes to");
            minutes.put(42, "eighteen minutes to");
            minutes.put(41, "nineteen minutes to");
            minutes.put(40, "twenty minutes to");
            minutes.put(39, "twenty one minutes to");
            minutes.put(38, "twenty two minutes to");
            minutes.put(37, "twenty three minutes to");
            minutes.put(36, "twenty four minutes to");
            minutes.put(35, "twenty five minutes to");
            minutes.put(34, "twenty six minutes to");
            minutes.put(33, "twenty seven minutes to");
            minutes.put(32, "twenty eight minutes to");
            minutes.put(31, "twenty nine minutes to");
        }

        public String getTiW(int H, int MM) {
            if (MM == 0) return hours.get(H).toString() + " o' clock";
            if (MM < 31) return minutes.get(MM).toString() + " " + hours.get(H);
            else {
                int HH = H + 1;
                if (HH == 13) HH = 1;
                return minutes.get(MM).toString() + " " + hours.get(HH);
            }
        }
    }

}
