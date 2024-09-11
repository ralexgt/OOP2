package DataStore;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

abstract public class DataCatalog<DataType extends CsvStreamable> {
    abstract protected DataType createFromCsvData(Map<String, String> csvKeyValuePairs);
    abstract protected String[] getHeaders();
    abstract protected void addItem(DataType item, Map<String, String> csvKeyValuePairs);
    abstract protected Collection<DataType> getItems();

    public void loadData(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String headerLine = br.readLine();

            if (headerLine == null) {
                throw new IOException("CSV file is empty");
            }

            String[] header = headerLine.split(",");

            String line;
            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(",");
                Map<String, String> csvKeyPairValues = new HashMap<>();
                for (int i = 0; i < header.length; i++) {
                    csvKeyPairValues.put(header[i], lineData[i]);
                }
                DataType item = createFromCsvData(csvKeyPairValues);
                addItem(item, csvKeyPairValues);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read from file: " + filename, e);
        }
    }

    public void saveData(String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            // Write headers
            String[] headers = getHeaders();
            bw.write(String.join(",", headers));
            bw.newLine();

            // Write data
            for (DataType item : getItems()) {
                Map<String, String> csvKeyValuePairs = item.toCsvKeyValuePairs();
                String[] csvData = new String[headers.length];
                for (int i = 0; i < headers.length; i++) {
                    csvData[i] = csvKeyValuePairs.get(headers[i]);
                }
                bw.write(String.join(",", csvData));
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}