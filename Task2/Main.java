import java.awt.geom.Path2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        String path1 = args[0];
        File file1 = new File(path1);
        String text = "";
        BufferedReader br1 = new BufferedReader(new FileReader(file1));
        StringBuilder sb1 = new StringBuilder();
        String line = br1.readLine();
        while (line != null) {
            sb1.append(line);
            sb1.append(" ");
            line = br1.readLine();
            text = sb1.toString();
        }
        br1.close();
        String[] listOfNums = text.split(" ");
        double[] nums = Arrays.stream(listOfNums)
                .mapToDouble(Double::parseDouble)
                .toArray();

        double[] vertex1 = {nums[0], nums[1]};
        double[] vertex2 = {nums[2], nums[3]};
        double[] vertex3 = {nums[4], nums[5]};
        double[] vertex4 = {nums[6], nums[7]};

        double[] valoresX = {nums[0], nums[2], nums[4], nums[6]};
        double[] valoresY = {nums[1], nums[3], nums[5], nums[7]};
        int n = valoresX.length;

        Path2D path = new Path2D.Double();
        path.moveTo(valoresX[0], valoresY[0]);
        for (int i = 1; i < valoresX.length; ++i) {
            path.lineTo(valoresX[i], valoresY[i]);
        }
        path.closePath();

        String path2 = args[1];
        File file2 = new File(path2);
        String text2 = "";
        BufferedReader br2 = new BufferedReader(new FileReader(file2));
        StringBuilder sb2 = new StringBuilder();
        String line2 = br2.readLine();
        while (line2 != null) {
            sb2.append(line2);
            sb2.append(System.lineSeparator());
            line2 = br2.readLine();
            text2 = sb2.toString();
        }
        br2.close();

        ArrayList<double[]> points = new ArrayList<>();
        String[] listOfNums2 = text2.split("\\n");
        for (int i = 0; i < listOfNums2.length; i++) {
            String[] a = listOfNums2[i].split(" ");
            double[] p = new double[2];
            p[0] = Double.parseDouble(a[0]);
            p[1] = Double.parseDouble(a[1]);
            points.add(p);
        }

        int vertex = 0;
        int side = 1;
        int in = 2;
        int out = 3;


        for (int i = 0; i < points.size(); i++) {

            if
            (Arrays.equals(points.get(i), vertex1) || Arrays.equals(points.get(i), vertex2) || Arrays.equals(points.get(i), vertex3) || Arrays.equals(points.get(i), vertex4)) {
                System.out.println(vertex);
            } else if (isPointOnSquare(vertex1, vertex2, vertex3, vertex4, points.get(i))) {
                System.out.println(side);
            } else if (path.contains(points.get(i)[0], points.get(i)[1])) {
                System.out.println(in);
            } else {
                System.out.println(out);
            }
        }
    }


    public static double hyp ( double x1, double x2, double y1, double y2){
        return Math.hypot((x1 - x2), (y1 - y2));
    }

    public static boolean isPointOnLine ( double[] lines1, double[] lines2, double[] arr3){
        double lineLength1 = hyp(lines1[0], lines2[0], lines1[1], lines2[1]);
        double s2 = hyp(lines2[0], arr3[0], lines2[1], arr3[1]);
        double s3 = hyp(lines1[0], arr3[0], lines1[1], arr3[1]);
        return lineLength1 == s2 + s3;
    }

    public static boolean isPointOnSquare ( double[] vertex1, double[] vertex2, double[] vertex3, double[] vertex4,
                                            double[] points){
        if (isPointOnLine(vertex1, vertex2, points)) {
            return true;
        } else if (isPointOnLine(vertex2, vertex3, points)) {
            return true;
        } else if (isPointOnLine(vertex3, vertex4, points)) {
            return true;
        } else if (isPointOnLine(vertex4, vertex1, points)) {
            return true;
        } else {
            return false;
        }


    }
}

