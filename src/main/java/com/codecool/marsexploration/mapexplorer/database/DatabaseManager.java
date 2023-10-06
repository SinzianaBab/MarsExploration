package com.codecool.marsexploration.mapexplorer.database;

public interface DatabaseManager {
    void addRover(String name, int steps, String resources, String outcome);
    void addResourceExtraction(String roverId, int resource1Count, int resource2Count);
    void addResourceDelivery(Integer centerId, int resource1Count, int resource2Count);
    void updateCommandCenterStock(Integer centerId, int resource1Stock, int resource2Stock);
    void addConstructionEvent(String name, String progress, String responsibleUnit, int step);
    void deleteAllRovers();
    void deleteAllCommandCenters();
    void deleteAllConstructions();
}
