import pojo.response.CsvReader;

import java.util.List;

/**
 * @author Pratikshit Bansal
 */
public class TestBase {
    public Object[][] getDataProvider(String filePath){
        List<List<String>> cells=CsvReader.getCellValues(filePath);
        Object[][] result=new Object[cells.size()][];
        int i=0;
        for(List<String> row:cells){
            result[i]=new Object[]{row};
            i++;
        }
        return result;
    }
}
