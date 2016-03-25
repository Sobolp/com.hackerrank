package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by SoBoLp on 3/25/16.
 */
public class LibraryFine {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String next = "";
        next = in.readLine();
        String[] arr = next.split(" ");
        int dayAct = Integer.parseInt(arr[0]);
        int monthAct = Integer.parseInt(arr[1]);
        int yearAct = Integer.parseInt(arr[2]);
        next = in.readLine();
        arr = next.split(" ");
        int dayExp = Integer.parseInt(arr[0]);
        int monthExp = Integer.parseInt(arr[1]);
        int yearExp = Integer.parseInt(arr[2]);
        int result = 0;
        if (yearAct < yearExp) result = 0;
        else if (yearAct > yearExp) result = 10000;
        else if (monthAct < monthExp) result = 0;
        else if (monthAct > monthExp) result = 500 * (monthAct - monthExp);
        else if (dayAct > dayExp) result = 15 * (dayAct - dayExp);
        System.out.println(result);

    }
}
