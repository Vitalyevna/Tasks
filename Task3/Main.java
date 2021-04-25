import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String text = "";
        String path = args[0];
        File dir = new File(path);
        File[] files = dir.listFiles();
        assert files != null;
        StringBuilder sb = new StringBuilder();
        for (File file : files) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
                text = sb.toString();


            br.close();
        }


        String[] listOfNums = text.split("\\n");

        double[] nums = Arrays.stream(listOfNums)
                .mapToDouble(Double::parseDouble)
                .toArray();

        double [] sum = new double[16];
            for (int i = 0; i < 16; i++) {
                sum[i] = nums[i] + nums[i + 16] + nums[i + 32] + nums[i + 48] + nums[i + 64];
            }


        int maxIndex = 0;
        double maxElement = sum[0];
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] > maxElement) {
                maxElement = sum[i];
                maxIndex = i;
            }
        }
        int interval = maxIndex + 1;
        System.out.println(interval);
    }
}




