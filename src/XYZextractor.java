import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class XYZextractor implements Extractor {

    @Override
    public void doextraction(String pathToFile, int step, int start, int finalLine) {
        BufferedReader br;
        BufferedWriter out;

        try {
            File file = new File(pathToFile);
            br = new BufferedReader(new FileReader(file));

            int totalNumberOfLinesInFile = 0;
            while (br.readLine() != null) {
                totalNumberOfLinesInFile++;
            }
            br.close();


            br = new BufferedReader(new FileReader(file));
            int totalNumberOfGeoms = 0;

            int numberOfAtoms = Integer.parseInt(br.readLine());
            totalNumberOfGeoms = totalNumberOfLinesInFile / (numberOfAtoms + 2);
            System.out.println("Total number of geomtries: "+totalNumberOfGeoms);

            int numberOfExtractedGeoms = ((totalNumberOfGeoms - start) / step );
            br.close();
            System.out.println("Number of extracted geometries: "+numberOfExtractedGeoms);
            System.out.println("Number of firstly skipped geometries: "+start);
            System.out.println("step size: "+step);

            String str;

            int counter = 0;

            br = new BufferedReader(new FileReader(file));
            while (counter < start * (numberOfAtoms + 2)) {
                br.readLine();
                counter++;
            }

            int currentFile = 0;
            while (currentFile < numberOfExtractedGeoms) {
                String outFileName = "snap_" + currentFile + ".xyz";
                out = new BufferedWriter(new FileWriter(new File(outFileName)));
                for (int i = 0; i < numberOfAtoms + 2; i++) {
                    str = br.readLine();
                    out.write(str+"\n");
                }
                out.close();

                for (int j = 0; j < step * (numberOfAtoms + 2); j++) {
                    br.readLine();
                }
                currentFile++;
            }
            br.close();

        } catch (FileNotFoundException e) {
            System.out.println("No such file found in path: " + pathToFile);

        } catch (IOException e) {
            System.out.println("Error while reading file");
        }
    }
}
