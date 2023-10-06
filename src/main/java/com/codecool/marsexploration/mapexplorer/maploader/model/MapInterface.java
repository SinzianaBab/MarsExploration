package com.codecool.marsexploration.mapexplorer.maploader.model;

public interface MapInterface {
    int getDimension();

    String getByCoordinate(Coordinate coordinate);

    boolean isEmpty(Coordinate coordinate);

    String toString();
}
