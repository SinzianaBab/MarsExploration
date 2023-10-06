package com.codecool.marsexploration.mapexplorer.commandCenter;

import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.HashMap;
import java.util.List;

public class CommandCenterImpl implements CommandCenter {
    private int id;
    private String name;
    private Coordinate location;
    private int status;
    private HashMap<String, List<Coordinate>> resourcesOnStock;

    public CommandCenterImpl(int id, Coordinate location, int status, HashMap<String, List<Coordinate>> resourcesOnStock) {
        this.name = "Command Center-" + id;
        this.location = location;
        this.status = status;
        this.resourcesOnStock = resourcesOnStock;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Coordinate getLocation() {
        return location;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public void setResourcesOnStock(HashMap<String, List<Coordinate>> resourcesOnStock) {

        for (String key : resourcesOnStock.keySet()) {
            if (this.resourcesOnStock.containsKey(key)) {
                this.resourcesOnStock.get(key).addAll(resourcesOnStock.get(key));
            }
        }
    }

    @Override
    public void incrementStatus() {
        this.status += 1;
    }


}
