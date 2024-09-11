package DataStore;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

abstract public class DataMapCatalog<DataType extends CsvStreamable> extends DataCatalog<DataType> {
    protected Map<String, DataType> data = new HashMap<>();

    abstract protected String getKeyFromCsvData(Map<String, String> csvKeyValuePairs);

    @Override
    protected void addItem(DataType item, Map<String, String> csvKeyValuePairs) {
        String key = getKeyFromCsvData(csvKeyValuePairs);
        data.put(key, item);
    }

    @Override
    protected Collection<DataType> getItems() {
        return data.values();
    }

    public DataType getEntry(String key) {
        return data.get(key);
    }

    public void setEntry(String key, DataType value) {
        data.put(key, value);
    }

    public void removeEntry(String key) {
        data.remove(key);
    }

    public Map<String, DataType> getAllEntries() {
        return data;
    }
}