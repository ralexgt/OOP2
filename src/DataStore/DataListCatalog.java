package DataStore;

import java.util.*;

abstract public class DataListCatalog<DataType extends CsvStreamable> extends DataCatalog<DataType> {
    protected List<DataType> data = new ArrayList<>();

    @Override
    protected Collection<DataType> getItems() {
        return data;
    }

    @Override
    protected void addItem(DataType item, Map<String, String> csvKeyValuePairs) {
        data.add(item);
    }

    public DataType getEntry(int id) {
        return data.get(id);
    }

    public void addEntry(DataType value) {
        data.add(value);
    }

    public List<DataType> getAllEntries() {
        return data;
    }
}