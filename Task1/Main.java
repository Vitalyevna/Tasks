import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class Main {

        public static void main(String[] args) throws IOException {
            String path = args[0];
            File CP_file = new File(path);
            String text = "";
            BufferedReader br1 = new BufferedReader(new FileReader(CP_file));
            StringBuilder sb1 = new StringBuilder();
            String line = br1.readLine();
            while (line != null) {
                sb1.append(line);
                sb1.append(System.lineSeparator());
                line = br1.readLine();
            }
            text = sb1.toString();

            String[] listOfNums = text.split("\\n");

            ArrayList<Integer> nums = new ArrayList<>();

            for (int i = 0; i < listOfNums.length; i++) {
                nums.add(Integer.parseInt(listOfNums[i]));
            }

            DescriptiveStatistics stats = new DescriptiveStatistics();
            for (int i = 0; i < nums.size(); i++) {
                stats.addValue(nums.get(i));
            }
            double max = stats.getMax();
            double min = stats.getMin();
            double percentile = stats.getPercentile(90);
            double mean = stats.getMean();
            double median = stats.getPercentile(50);


            DecimalFormat df = new DecimalFormat("0.00");
            for (Number n : Arrays.asList(percentile, median, max, min, mean)) {
                Double d = n.doubleValue();
                System.out.println(df.format(d));

            }
        }
    }
