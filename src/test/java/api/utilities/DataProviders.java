package api.utilities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "Data")
    public Object[][] provideAllData() throws IOException {
        String path = "src/test/resources/testData/Practice_Data.xlsx";
        XLUtility xl = new XLUtility(path);

        int rownum = xl.getRowCount("Data");
        int colcount = xl.getCellCount("Data", 1);

        String apidata[][] = new String[rownum][colcount];

        for (int i = 1; i <= rownum; i++) {
            for (int j = 0; j < colcount; j++) {
                apidata[i - 1][j] = xl.getCellData("Data", i, j);
            }
        }

        return apidata;
    }



    @DataProvider(name = "UserNames")
    public Object[][] provideUserNames() throws IOException {
        String path = "src/test/resources/testData/Practice_Data.xlsx";
        XLUtility xlUtility = new XLUtility(path);

        int rownum = xlUtility.getRowCount("Data"); // Replace "Sheet1" with your actual sheet name
        int colnum = 1; // Assuming you want to read user names from the first column

        Object[][] data = new Object[rownum][colnum];

        for (int i = 0; i < rownum; i++) {
            data[i][0] = xlUtility.getCellData("Data", i + 1, 0);
        }

        return data;
    }


}
