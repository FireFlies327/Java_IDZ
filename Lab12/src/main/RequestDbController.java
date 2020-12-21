package main;

import java.util.List;

public class RequestDbController {
    DatabaseService databaseService;

    public RequestDbController() {
        databaseService = DatabaseService.getDatabaseService();
    }

    List<Worker> getWorkerList() {
        return databaseService.getWorkerList();
    }

    Report getReport(int id) {
        return databaseService.getReport(id);
    }
}
