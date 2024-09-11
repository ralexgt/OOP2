package Services;

import Commands.CommandType;
import Config.PathsConfig;
import DataStore.LogsCatalog;
import Models.Log;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public enum LoggingService implements ServiceInterface {
    INSTANCE;

    private final LogsCatalog logs = new LogsCatalog();

    LoggingService() {}

    public static LoggingService getInstance() {
        return INSTANCE;
    }

    @Override
    public String getName() {
        return "Logging Service";
    }

    @Override
    public void initialize() {
        logs.loadData(PathsConfig.logsPath);
    }

    @Override
    public void deinitialize() {
        logs.saveData(PathsConfig.logsPath);
    }

    @Override
    public List<CommandType> getAvailableCommands() {
        return new ArrayList<>();
    }

    public void log(String action) {
        Log log = new Log(action, LocalDateTime.now());
        logs.addEntry(log);
    }
}