package com.company;

//UoW ID - w1810019
//IIT ID - 20200312

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Formula1Driver extends Driver {

    HashMap <Integer, String> results = new HashMap <Integer, String>();
    ArrayList <Integer> positions = new ArrayList <Integer>();

    public Formula1Driver (String driverFName, String driverLName, String driverCountry, String teamName,
                           int driverPoints, int driverTotalRaces, int driverWins) {

        this.driverPoints = driverPoints;
        this.driverCompletedRaces = driverTotalRaces;
        this.driverWins = driverWins;
        this.driverFName = driverFName;
        this.driverLName = driverLName;
        this.driverLocation = driverCountry;
        this.driverTeamName = teamName;
    }

    int raceChpStartDay = 1, raceChpStartMonth = 1, raceChpYear = 2021;

    public void switchRaceDate() {

        switch (currentRaceDate) {

            case 1:
                raceChpStartDay = 2;
                raceChpStartMonth = 2;
                raceChpYear = 2022;
                break;

            case 2:
                raceChpStartDay = 3;
                raceChpStartMonth = 3;
                raceChpYear = 2022;
                break;

            case 3:
                raceChpStartDay = 4;
                raceChpStartMonth = 4;
                raceChpYear = 2022;
                break;

            case 4:
                raceChpStartDay = 5;
                raceChpStartMonth = 5;
                raceChpYear = 2022;
                break;

            case 5:
                raceChpStartDay = 6;
                raceChpStartMonth = 6;
                raceChpYear = 2022;
                break;

            case 6:
                raceChpStartDay = 7;
                raceChpStartMonth = 7;
                raceChpYear = 2022;
                break;

            case 7:
                raceChpStartDay = 8;
                raceChpStartMonth = 8;
                raceChpYear = 2022;
                break;

            case 8:
                raceChpStartDay = 9;
                raceChpStartMonth = 9;
                raceChpYear = 2022;
                break;

            case 9:
                raceChpStartDay = 10;
                raceChpStartMonth = 10;
                raceChpYear = 2022;
                break;

            case 10:
                raceChpStartDay = 11;
                raceChpStartMonth = 11;
                raceChpYear = 2022;
                break;

            default:
                System.out.println("Something went wrong!");
        }
    }

    public void sortPointsDesc(){

        if (drivers == null || "".equals(drivers)) {
            System.out.println("There are no drivers!");
        } else {
            Collections.sort(drivers, new Comparator<Driver>() {
                public int compare(Driver driver1, Driver driver2) {
                    return driver1.getDriverPoints() - driver2.getDriverPoints();
                }
            }
            );

        }

    }
}