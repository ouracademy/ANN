/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataSources;


import com.our.neuralnetwork.DataSet;
import com.our.Util.FileUtils;
import java.util.Arrays;
import java.util.List;


/**
 *
 * @author Diana Quintanilla Perez
 */
public class GetData  {
    
    public DataSet[] fromFile(String path){
        DataSet[] dataSet = null;
        try {
            List<String> dataSource = FileUtils.readFileAsListOfStrings(path);
            dataSet = transformSourceToDataSet(dataSource);
        } catch (Exception ex) {

        }
        return dataSet;
        
    }

    private DataSet[] transformSourceToDataSet(List<String> source) {
        int lengthDataSet = source.size();
        DataSet[] dataSet = new DataSet[lengthDataSet];
        double[] dataOfLine = null;
        int i = 0;
        for (String line : source) {
            dataOfLine = transformLineToArray(line, ",");
            dataSet[i] = transformArrayToDataSet(dataOfLine);
            i++;
        }
        return dataSet;
    }

    private double[] transformLineToArray(String linea, String separador) {
        return Arrays.stream(linea.split(separador))
                .mapToDouble(Double::parseDouble)
                .toArray();
    }

    private DataSet transformArrayToDataSet(double[] array) {
        int length = array.length;
        DataSet dataSet = new DataSet(array);
        dataSet.out = {array[length - 1]};
        return dataSet;
    }


}
