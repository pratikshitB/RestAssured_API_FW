package pojo.response;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pratikshit Bansal
 */
public class CsvReader {

    public static List<String> getAllRows(String filepath) {
        List<String> allRows = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allRows.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return allRows;
    }

    public static List<List<String>> getCellValues(String filePath) {
        List<String> rows = getAllRows(filePath);
        List<List<String>> cells = new ArrayList<>();

        for (String str : rows) {
            cells.add(Arrays.asList(str.split(",")));
        }
        return cells;
    }

    public static String getCellValue(List<String> row, int index) {
        if (row == null)
            return null;
        try {
            return row.get(index).equals("") ? null : row.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

}
