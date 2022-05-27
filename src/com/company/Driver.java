package com.company;

//UoW ID - w1810019
//IIT ID - 20200312

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

abstract class Driver {

    public String driverFName,
            driverLName,
            driverTeamName,
            driverLocation;

    public int driverPoints,
                driverCompletedRaces,
                driverWins;

    public String[] teamNames = {"Nissan","Mitsubishi", "Mazda", "Subaru", "Yamaha", "Lexus", "Toyota", "Honda", "Acura", "Datsun"};

    public int currentRaceDate = 1;

    public static int maxDriverCount = 10;

    public ArrayList<Driver> drivers = new ArrayList<>(maxDriverCount);
    public ArrayList<String> formulaTeams = new ArrayList<>(Arrays.asList(teamNames));

    public void validatingString(String userInput){

        String regex = "^[a-zA-Z]+$";

        while (true) {
            if (!userInput.matches(regex)) {
                System.out.println("Invalid Entry. Please Try Again!");
            } else {
                break;
            }
        }
    }

    public void capitalizeString (String userInput) {

        userInput = userInput.substring(0,1).toUpperCase() + userInput.substring(1).toLowerCase();
    }

    public void setDriverFName(String driverFName){

        Scanner input = new Scanner(System.in);

        System.out.println(" Please driver's first name: ");

        validatingString(driverFName);

        capitalizeString(driverFName);

    }

    public void setDriverLName(String driverLName){

        Scanner input = new Scanner(System.in);

        System.out.println("Enter driver's last name: ");

        validatingString(driverLName);

        capitalizeString(driverLName);
    }

    public void setDriverTeamName(String driverTeamName){

        Scanner input = new Scanner(System.in);

        System.out.println(" Please enter Racers team name : ");

        validatingString(driverTeamName);

        capitalizeString(driverTeamName);
    }

    public void setDriverPoints(int driverPoints) {
        this.driverPoints += driverPoints;
    }

    public void setDriverTotalRaces(int driverCompletedRaces){
        this.driverCompletedRaces += driverCompletedRaces;
    }

    public void setDriverWins(int driverWins){
        this.driverWins += driverWins;
    }

    public int getDriverPoints() {
        return driverPoints;
    }

    public int getDriverTotalRaces() {
        return driverCompletedRaces;
    }

    public int getDriverWins() {
        return driverWins;
    }

    public String getDriverFName() {
        return driverFName;
    }

    public String getDriverLName() {
        return driverLName;
    }

    public String getDriverTeamName() {
        return driverTeamName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }
}
