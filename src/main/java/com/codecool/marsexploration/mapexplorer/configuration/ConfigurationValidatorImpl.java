package com.codecool.marsexploration.mapexplorer.configuration;

import com.codecool.marsexploration.mapexplorer.configuration.model.Configuration;
import com.codecool.marsexploration.mapexplorer.maploader.MapLoaderImpl;
import com.codecool.marsexploration.mapexplorer.maploader.model.Coordinate;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationValidatorImpl implements ConfigurationValidator {

    @Override
    public boolean validateConfigurationObject(Configuration mapConfiguration) {
        return mapConfiguration.steps() > 0 && checkLandingSpots(mapConfiguration.landingSpot(), mapConfiguration) && checkAdjacentCoordinate(mapConfiguration.landingSpot(), mapConfiguration).size() > 0 && !mapConfiguration.map().isEmpty() && checkSymbols(mapConfiguration.symbols());
    }

    @Override
    public List<Coordinate> checkAdjacentCoordinate(Coordinate coordinate, Configuration mapConfiguration) {
        List<Coordinate> adjCoordinates = new ArrayList<>();
        int x = coordinate.X();
        int y = coordinate.Y();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                Coordinate coordinateToAdd = new Coordinate(x + i, y + j);
                if (!coordinateToAdd.equals(coordinate) && checkLandingSpots(coordinateToAdd, mapConfiguration)) {
                    adjCoordinates.add(coordinateToAdd);
                }
            }
        }
        return adjCoordinates;
    }

    @Override
    public boolean checkSymbols(List<String> symbols) {
        List<String> allSymbols = List.of("#", "&", "*", "%");
        if (symbols.size() == 0) {
            return false;
        }
        for (String symbol : symbols) {
            if (!allSymbols.contains(symbol)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkLandingSpots(Coordinate coordinate, Configuration mapConfiguration) {
        int x = coordinate.X();
        int y = coordinate.Y();
        char[][] mapArray = getMap2D(mapConfiguration);

        if (x >= 0 && x < mapArray.length && y >= 0 && y < mapArray.length) {
            return mapArray[x][y] == ' ';
        }

        return false;
    }

    @Override
    public void roverMap(Coordinate spaceshipLocation, Configuration mapConfiguration, List<Coordinate> coordinates) {
        char[][] mapArrayChar = getMap2D(mapConfiguration);
        String [][] mapArray = convertChar2DToString2D(mapArrayChar);
        for (Coordinate coordinate : coordinates) {
            mapArray[coordinate.X()][coordinate.Y()] = "\uD83D\uDE97";
        }
        mapArray[spaceshipLocation.X()][spaceshipLocation.Y()] =  "\uD83D\uDE80";
        for(int i=0; i<mapArray.length; i++){
            for(int j=0; j<mapArray[i].length; j++){
                switch (mapArray[i][j]) {
                    case "#" -> mapArray[i][j] = "\uD83D\uDDFB";
                    case "&" -> mapArray[i][j] = "\uD83D\uDEB5";
                    case "%" -> mapArray[i][j] = "\uD83D\uDD36";
                    case "*" -> mapArray[i][j] = "\uD83D\uDCA7";
                }
            }
        }

        for (String[] strings : mapArray) {
            for (int j = 0; j < mapArray.length; j++) {
                System.out.print(strings[j] + " ");
            }
            System.out.println();
        }
    }
//todo utils with all private methods
    public String convertConfigurationIntoMap(Configuration mapConfiguration) {
        List<String> mapLoader = new MapLoaderImpl().readAllLines(mapConfiguration.map());
        return String.join("", mapLoader);
    }

    private char[][] getMap2D(Configuration mapConfiguration) {
        String map = convertConfigurationIntoMap(mapConfiguration);
        int sqrtOfMapSize = (int) Math.sqrt(map.length());
        char[][] mapArray = new char[sqrtOfMapSize][sqrtOfMapSize];
        int index = 0;

        for (int i = 0; i < sqrtOfMapSize; i++) {
            for (int j = 0; j < sqrtOfMapSize; j++) {
                mapArray[i][j] = map.charAt(index++);
            }
        }

        return mapArray;
    }

    public String[][] convertChar2DToString2D(char[][] charArray) {
        int rows = charArray.length;
        int cols = charArray[0].length;

        String[][] stringArray = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                stringArray[i][j] = String.valueOf(charArray[i][j]);
            }
        }

        return stringArray;
    }
}