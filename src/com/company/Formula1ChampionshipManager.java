package com.company;

//UoW ID - w1810019
//IIT ID - 20200312

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Formula1ChampionshipManager extends JFrame implements ChampionshipManager, ActionListener {

    Formula1Driver driver = new Formula1Driver(
            "",
            "",
            "",
            "",
            0,
            0,
            0);

    String[] raceDates = {
            "01/01/2021",
            "02/02/2021",
            "03/03/2021",
            "04/04/2021",
            "05/05/2021",
            "06/06/2021",
            "07/07/2021",
            "08/08/2021",
            "09/09/2021",
            "10/10/2021"};

    //Create buttons for functions
    JButton btnDescByPoints,
            btnAscByPoints,
            btnStartRace,
            btnDescByWins,
            btnReset,
            btnRaceDatesHistory;

    //Create dropdown list button
    JMenuItem loadFromDatabase, saveToDatabase;

    //Create label to show any method completion or error message
    JLabel methodMessage = new JLabel();

    //Create container for application
    JPanel panel = new JPanel();

    JPanel buttons = new JPanel();

    //Create label to show drivers
    JLabel[] displayDrivers = new JLabel[10];

    GridBagConstraints gridEditor = new GridBagConstraints();

    JLabel[] raceResults = new JLabel[10];

    ArrayList<JLabel> raceDatesHistory = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new Formula1ChampionshipManager();
    }

    /*
    public void programmeCLI() {
        Scanner input = new Scanner(System.in);

        while(true) {

            //User Menu
            System.out.println("---Select an Option---");
            System.out.println("1 - Add Driver to Database");
            System.out.println("2 - Remove Driver from Database");
            System.out.println("3 - Change Driver of a Team");
            System.out.println("4 - Show Driver Statistics");
            System.out.println("5 - Show Drivers Table");
            System.out.println("6 - Start a Race");
            System.out.println("7 - Save Information to Database");
            System.out.println("8 - Load Information from Database");
            System.out.println("9 - Exit Application");

            loop : while(true) {

                //Prompt user for choice
                System.out.println("Select a number: ");
                //Get user choice
                int userInput = input.nextInt();

                switch(userInput) {
                    case 1:
                        addDriver();
                        break;
                    case 2:
                        removeDriver();
                        break;
                    case 3:
                        changeDriver();
                        break;
                    case 4:
                        showDriverStats();
                        break;
                    case 5:
                        showDriversDescByPoints();
                        break;
                    case 6:
                        startRace();
                        break;
                    case 7:
                        saveToDatabase();
                        break;
                    case 8:
                        loadFromDatabase();
                        break;
                    case 9:
                        break loop;
                    default:
                        System.out.println("Invalid entry! Try again.");
                }
            }
        }
    }

    //Add driver to a team
    public void addDriver() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter First Name: ");
        String fName = input.next();
        driver[driverNum].setFName(fName);

        System.out.println("Enter Last Name: ");
        String lName = input.next();
        driver[driverNum].setLName(lName);

        System.out.println("Enter Location: ");
        String location = input.next();
        driver[driverNum].setDriverLocation(location);

        System.out.println("Enter Team Name: ");
        String team = input.next();
        driver[driverNum].setDriverTeamName(team);
    }

    //Remove driver from a team
    public void removeDriver() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter driver no.: ");
        int driverNum = input.nextInt();
        driver.[driverNum].setDriverFName("");
        System.out.println("Driver" + driverNum + " removed from database");
    }

    //Change driver of a team
    public void changeDriver() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter driver no.: ");
        int driverNum = input.nextInt();

        System.out.println("Enter team no.: ");
        int teamNum = input.nextInt();
        teams[teamNum].setDriverTeamName(teamNum);

        System.out.println("Driver successfully changed");
    }

     */

    Formula1ChampionshipManager() throws IOException {

        this.setLayout(new GridBagLayout());

        //Set application title
        this.setTitle("Formula1 Championship Simulator");

        //Set dimensions for application interface
        this.setSize(1500, 600);

        //Set application background color
        this.getContentPane().setBackground(Color.yellow);

        //Stop application from running in the background when exited
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Restrict user from resizing application
        this.setResizable(false);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setBackground(Color.black);

        ImageIcon chmpLogo = new ImageIcon(new ImageIcon("src/com/company/Img/image.png").getImage().getScaledInstance(72, 72, Image.SCALE_DEFAULT));

        this.setIconImage(chmpLogo.getImage());

        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        buttons.setAlignmentY(Component.RIGHT_ALIGNMENT);

        buttons.setBackground(Color.black);

        saveLoadMenu();

        this.setVisible(true);

        this.getContentPane().add(panel);

        this.getContentPane().add(buttons);

        sortInAscPointsBtn();
        sortInDescPointsBtn();
        sortInDescWinsBtn();
        raceDatesHistoryBtn();
        startRaceBtn();
        resetBtn();
        clearDatabase();
        showDriversDescByPoints();
    }

    public void showDriversDescByPoints() {

        clearApplicationScreen();

        this.add(methodMessage);

        if (driver.drivers.isEmpty()) {

            methodMessage.setText("Driver database is empty!");

            methodMessage.setForeground(Color.white);

            gridEditor.anchor = GridBagConstraints.PAGE_START;

            gridEditor.weightx = 0;
            gridEditor.gridx = 1;

            gridEditor.weighty = 0;
            gridEditor.gridy = 1;

            panel.add(methodMessage, gridEditor);

        } else {

            int driverNum = 0;
            Collections.sort(driver.drivers, new Comparator<Driver>() {
                public int compare(Driver driver1, Driver driver2) {
                    return driver2.getDriverPoints() - driver1.getDriverPoints();
                }

            });

            while (driverNum < driver.drivers.size()) {

                panel.add(displayDrivers[driverNum] = new JLabel("Full Name - " + driver.drivers.get(driverNum).getDriverFName()  + " " + driver.drivers.get(driverNum).getDriverLName() +
                        " - Team - " + driver.drivers.get(driverNum).getDriverTeamName() + " - Points - " + driver.drivers.get(driverNum).getDriverPoints()));

                driverNum++;
            }
        }
    }

    public void showDriversDescByWins() {

        clearApplicationScreen();

        if (driver.drivers.isEmpty()) {

            methodMessage.setText("Driver database is empty!");

            methodMessage.setForeground(Color.white);

            gridEditor.anchor = GridBagConstraints.PAGE_START;

            gridEditor.weightx = 0;
            gridEditor.gridx = 1;

            gridEditor.weighty = 0;
            gridEditor.gridy = 1;

            panel.add(methodMessage, gridEditor);

        } else {

            int driverNum = 0;
            Collections.sort(driver.drivers, new Comparator<Driver>() {  //
                public int compare(Driver driver1, Driver driver2) {
                    return driver2.getDriverWins() - driver1.getDriverWins();
                }

            });

            while (driverNum < driver.drivers.size()) {

                panel.add(displayDrivers[driverNum] = new JLabel("Full Name - " + driver.drivers.get(driverNum).getDriverFName() + " " + driver.drivers.get(driverNum).getDriverLName() +
                        " - Team - " + driver.drivers.get(driverNum).getDriverTeamName() + " - Wins - " + driver.drivers.get(driverNum).getDriverWins()));
                panel.setForeground(Color.white);
                driverNum++;

            }
        }
    }

    public void showDriversAscByPoints() {

        clearApplicationScreen();

        this.add(methodMessage);

        if (driver.drivers.isEmpty()) {

            methodMessage.setText("Driver database is empty!");

            methodMessage.setForeground(Color.white);

            gridEditor.anchor = GridBagConstraints.PAGE_START;
            gridEditor.weightx = 0;
            gridEditor.gridx = 1;

            gridEditor.weighty = 0;
            gridEditor.gridy = 1;

            panel.add(methodMessage, gridEditor);

        } else {

            int driverNum = 0;
            Collections.sort(driver.drivers, new Comparator<Driver>() {
                public int compare(Driver driver1, Driver driver2) {
                    return driver1.getDriverPoints() - driver2.getDriverPoints();

                }
            });

            while (driverNum < driver.drivers.size()) {

                panel.add(displayDrivers[driverNum] = new JLabel("Driver " + (driverNum + 1) + " - Full Name - " + driver.drivers.get(driverNum).getDriverFName() + " " + driver.drivers.get(driverNum).getDriverLName() +
                        " Team - " + driver.drivers.get(driverNum).getDriverTeamName() + " Points - " + driver.drivers.get(driverNum).getDriverPoints()));

                driverNum++;

            }
        }
    }

    public void clearDatabase() throws IOException {

        clearApplicationScreen();

        driver.drivers.clear();
        driver.currentRaceDate = 1;

        String driverDatabase = "src/com/company/Data/driver_database_clear.txt";
        String raceDateHistoryClear = "src/com/company/Data/race_dates_history_clear.txt";
        String raceDriverPosition = "src/com/company/Data/driver_race_positions.txt";

        String l1, l2, l3, l4, l5, l6, l7;

        Scanner readRaceDateHistoryClear = null;
        Scanner readDriverDatabaseClear = null;
        Scanner readRaceDriverPosition = null;

        BufferedWriter writeRaceDriverPosition = new BufferedWriter(new FileWriter(raceDriverPosition));

        try {

            readDriverDatabaseClear = new Scanner(new File(driverDatabase));
            readRaceDateHistoryClear = new Scanner(new File(raceDateHistoryClear));
            readRaceDriverPosition = new Scanner(new File(raceDriverPosition));

        } catch (Exception e) {

            System.out.println("Unable to locate database");

        }

        int driverLine = 0;
        while (readDriverDatabaseClear.hasNext()) {

            l1 = readDriverDatabaseClear.nextLine();
            l2 = readDriverDatabaseClear.nextLine();
            l3 = readDriverDatabaseClear.nextLine();
            l4 = readDriverDatabaseClear.nextLine();
            l5 = readDriverDatabaseClear.nextLine();
            l6 = readDriverDatabaseClear.nextLine();
            l7 = readDriverDatabaseClear.nextLine();

            driver.drivers.add(new Formula1Driver(l1, l2, l3, l4, Integer.parseInt(l5), Integer.parseInt(l6), Integer.parseInt(l7)));

            driverLine++;

        }

        if (!(driverLine < 1)) {

            for (int i = 0; i < driverLine; i++) {
                driver.formulaTeams.remove(driver.drivers.get(driverLine - 1).getDriverTeamName());

            }
        }

        while (readRaceDateHistoryClear.hasNext()) {

            driver.raceChpStartDay = Integer.parseInt(readRaceDateHistoryClear.nextLine());
            driver.raceChpStartMonth = Integer.parseInt(readRaceDateHistoryClear.nextLine());
            driver.raceChpYear = Integer.parseInt(readRaceDateHistoryClear.nextLine());

        }

        while(readRaceDriverPosition.hasNext()){

            writeRaceDriverPosition.flush();
        }

        if (displayDrivers[0] != null) {

            for (int i = 0; i < 10; i++) {

                displayDrivers[i].setText("");

            }
        }

        methodMessage.setText("Database Cleared!");
        methodMessage.setForeground(Color.white);
        gridEditor.anchor = GridBagConstraints.PAGE_START;
        gridEditor.weightx = 0;
        gridEditor.weighty = 0;
        gridEditor.gridx = 1;
        gridEditor.gridy = 1;
        panel.add(methodMessage, gridEditor);
        readRaceDateHistoryClear.close();
        readDriverDatabaseClear.close();

    }

    public void startRace() throws IOException {

        clearApplicationScreen();

        Random random = new Random();
        String driverRacePositions = "src/com/company/Data/driver_race_positions.txt";
        BufferedWriter writeToFile = new BufferedWriter(new FileWriter(driverRacePositions, true));

        if(driver.currentRaceDate == 11){
            seasonChampion();
        }
        else{

            if (driver.drivers.size() == 10) {

                methodMessage.setText(driver.raceChpStartDay + "/" + driver.raceChpStartMonth + "/" + driver.raceChpYear);

                methodMessage.setForeground(Color.white);

                gridEditor.anchor = GridBagConstraints.PAGE_START;

                gridEditor.weightx = 0;
                gridEditor.gridx = 1;
                gridEditor.weighty = 0;
                gridEditor.gridy = 1;

                panel.add(methodMessage, gridEditor);

                driver.switchRaceDate();

                HashMap<Integer, String> results = new HashMap<>();

                ArrayList<Integer> positions = new ArrayList<>();

                for (int pos = 0; pos < driver.maxDriverCount; pos++) {

                    int randomPositionAssign = random.nextInt(10);

                    if (results.containsKey(randomPositionAssign)) {

                        while (results.containsKey(randomPositionAssign)) {

                            randomPositionAssign = random.nextInt(10);

                        }

                        results.put(randomPositionAssign, driver.drivers.get(randomPositionAssign).getDriverFName());

                    } else {

                        results.put(randomPositionAssign, driver.drivers.get(randomPositionAssign).getDriverFName());

                    }

                    panel.add(raceResults[pos] = new JLabel("Place " + (pos + 1) + " - " + results.get(randomPositionAssign) +
                            " " + driver.drivers.get(randomPositionAssign).getDriverTeamName() + " "));

                    positions.add(randomPositionAssign);

                }

                for (int i = 0; i < results.size(); i++) {

                    writeToFile.write(driver.drivers.get(positions.get(i)).getDriverFName() + "\n");


                }
                System.out.println(positions);

                for (int i = 0; i < positions.size(); i++) {
                    racePositions(i, positions);

                }

                if (driver.currentRaceDate == 11){
                    seasonChampion();

                }
            }

            else{
                methodMessage.setText("Need more drivers");
                methodMessage.setForeground(Color.white);

            }

            //Close stream
            writeToFile.close();

            driver.currentRaceDate++;
        }
    }

    //Display dates the races have taken place
    public void raceDatesHistory() {

        clearApplicationScreen();

        if (driver.currentRaceDate == 1){

            methodMessage.setText("Season hasn't started yet");

            methodMessage.setForeground(Color.white);

            gridEditor.anchor = GridBagConstraints.PAGE_START;

            gridEditor.weightx = 0;
            gridEditor.weighty = 0;
            gridEditor.gridx = 1;
            gridEditor.gridy = 1;

            panel.add(methodMessage, gridEditor);

        } else {

            int driverNum = 0;

            while (driverNum < driver.currentRaceDate - 1) {

                JLabel date = new JLabel(raceDates[driverNum]+"\n");

                raceDatesHistory.add(date);

                date.setForeground(Color.white);

                driverNum++;
            }

            for (JLabel x : raceDatesHistory){

                panel.add(x);

                System.out.println("");
            }
        }
    }

    //Save race progress to a text file
    public void saveToDatabase() throws IOException {

        String driverDatabase = "src/com/company/Data/driver_database.txt";
        String raceDateHistoryDatabase = "src/com/company/Data/race_dates_history.txt";
        String raceDriverPositionDatabase = "src/com/company/Data/driver_race_positions_save.txt";


        BufferedWriter writeDriverDatabase = new BufferedWriter(new FileWriter(driverDatabase));
        BufferedWriter writeRaceDateHistoryDatabase = new BufferedWriter(new FileWriter(raceDateHistoryDatabase));
        BufferedWriter writeRaceDriverPositionDatabase = new BufferedWriter(new FileWriter(raceDriverPositionDatabase));

        for (int i = 0; i < driver.drivers.size(); i++) {

            writeDriverDatabase.write(driver.drivers.get(i).getDriverFName() + "\n");
            writeDriverDatabase.write(driver.drivers.get(i).getDriverLName() + "\n");
            writeDriverDatabase.write(driver.drivers.get(i).getDriverLocation() + "\n");
            writeDriverDatabase.write(driver.drivers.get(i).getDriverTeamName() + "\n");
            writeDriverDatabase.write(driver.drivers.get(i).getDriverPoints() + "\n");
            writeDriverDatabase.write(driver.drivers.get(i).getDriverTotalRaces() + "\n");
            writeDriverDatabase.write(driver.drivers.get(i).getDriverWins() + "\n");

        }

        writeRaceDateHistoryDatabase.write(driver.raceChpStartDay + "\n");
        writeRaceDateHistoryDatabase.write(driver.raceChpStartMonth + "\n");
        writeRaceDateHistoryDatabase.write(driver.raceChpYear + "\n");
        String raceDriverPosition = "src/com/company/Data/positions.txt";
        Scanner readDriverDatabase = null;

        try {

            readDriverDatabase = new Scanner(new File(raceDriverPosition));

        } catch (Exception e) {

            methodMessage.setText("Unable to locate database");

        }

        while(readDriverDatabase.hasNext()){

            writeRaceDriverPositionDatabase.write(readDriverDatabase.nextLine() + "\n");

        }

        if (displayDrivers[0] != null) {

            for (int i = 0; i < displayDrivers.length; i++) {

                displayDrivers[i].setText("");

            }
        }

        methodMessage.setText("Database updated");
        methodMessage.setForeground(Color.black);

        gridEditor.anchor = GridBagConstraints.PAGE_START;

        gridEditor.weightx = 0;
        gridEditor.weighty = 0;
        gridEditor.gridx = 1;
        gridEditor.gridy = 1;

        panel.add(methodMessage, gridEditor);

        writeDriverDatabase.close();
        writeRaceDateHistoryDatabase.close();
        writeRaceDriverPositionDatabase.close();
    }

    //Loads data from a text file
    public void loadFromDatabase() throws IOException {

        driver.drivers.clear();

        String driverDatabase = "src/com/company/Data/driver_database.txt";
        String raceDateHistoryDatabase = "src/com/company/Data/race_dates_history.txt";
        String raceDriverPositionDatabase = "src/com/company/Data/driver_race_positions_save.txt";
        String raceDriverPosSwap = "src/com/company/Data/driver_race_positions.txt";

        String l1, l2, l3, l4, l5, l6, l7;

        BufferedWriter writeDriverPosSwap = new BufferedWriter(new FileWriter(raceDriverPosSwap));

        Scanner readRaceDateHistoryDatabase = null;
        Scanner readDriverDatabase = null;

        try {

            readDriverDatabase = new Scanner(new File(driverDatabase));
            readRaceDateHistoryDatabase = new Scanner(new File(raceDateHistoryDatabase));

        } catch (Exception e) {

            methodMessage.setText("Unable to locate database");
            methodMessage.setForeground(Color.white);

        }

        int driverLine = 0;

        while (readDriverDatabase.hasNext()) {

            l1 = readDriverDatabase.nextLine();
            l2 = readDriverDatabase.nextLine();
            l3 = readDriverDatabase.nextLine();
            l4 = readDriverDatabase.nextLine();
            l5 = readDriverDatabase.nextLine();
            l6 = readDriverDatabase.nextLine();
            l7 = readDriverDatabase.nextLine();

            driver.drivers.add(new Formula1Driver(l1, l2, l3, l4, Integer.parseInt(l5), Integer.parseInt(l6), Integer.parseInt(l7)));

            driverLine++;

        }

        if (!(driverLine < 1)) {

            for (int i = 0; i < driverLine; i++) {
                driver.formulaTeams.remove(driver.drivers.get(driverLine - 1).getDriverTeamName());
            }
        }

        while (readRaceDateHistoryDatabase.hasNext()) {

            driver.raceChpStartDay = Integer.parseInt(readRaceDateHistoryDatabase.nextLine());
            driver.raceChpStartMonth = Integer.parseInt(readRaceDateHistoryDatabase.nextLine());
            driver.raceChpYear = Integer.parseInt(readRaceDateHistoryDatabase.nextLine());

        }
        if (displayDrivers[0] != null) {

            for (int i = 0; i < 10; i++) {
                displayDrivers[i].setText("");
            }
        }

        methodMessage.setText("Database retrieved");
        methodMessage.setForeground(Color.white);
        gridEditor.anchor = GridBagConstraints.PAGE_START;
        gridEditor.weightx = 0;
        gridEditor.weighty = 0;
        gridEditor.gridx = 1;
        gridEditor.gridy = 1;
        panel.add(methodMessage, gridEditor);

        //close streams
        readRaceDateHistoryDatabase.close();
        readDriverDatabase.close();
        writeDriverPosSwap.close();
    }

    public void sortInAscPointsBtn() {

        //Set button label
        btnAscByPoints = new JButton("Sort Drivers Table in Asc. Order of Points");

        //Set bounds for button
        btnAscByPoints.setBounds(50, 100, 150, 30);

        //Define activity when user clicks
        btnAscByPoints.addActionListener(this);

        //Remove button focus
        btnAscByPoints.setFocusable(false);

        //Set button text color
        btnAscByPoints.setForeground(Color.white);

        //Set button color
        btnAscByPoints.setBackground(Color.black);

        //Remove button border
        btnAscByPoints.setBorderPainted(false);

        //Align to center
        btnAscByPoints.setVerticalTextPosition(JButton.CENTER);

        buttons.add(btnAscByPoints);

    }

    //Create dropdown list menu to save/load data to/from database
    public void saveLoadMenu() {

        JMenuBar menu = new JMenuBar();

        this.setJMenuBar(menu);

        JMenu fileMenu = new JMenu("Save/Load Database");

        //Set label
        loadFromDatabase = new JMenuItem("Load");

        //Set label
        saveToDatabase = new JMenuItem("Save");

        //Define activity when user clicks
        saveToDatabase.addActionListener(this);
        loadFromDatabase.addActionListener(this);

        fileMenu.add(saveToDatabase);
        fileMenu.add(loadFromDatabase);

        menu.add(fileMenu);
    }

    public void sortInDescPointsBtn() {

        //Set button label
        btnDescByPoints = new JButton("Sort Drivers Table in Desc. Order of Points");

        //Define activity when user clicks
        btnDescByPoints.addActionListener(this);

        //Remove button focus
        btnDescByPoints.setFocusable(false);

        //Set button text color
        btnDescByPoints.setForeground(Color.white);

        //Set button color
        btnDescByPoints.setBackground(Color.black);

        //Remove button border
        btnDescByPoints.setBorderPainted(false);

        //Align to center
        btnDescByPoints.setVerticalTextPosition(JButton.CENTER);

        buttons.add(btnDescByPoints);
    }

    public void sortInDescWinsBtn() {

        //Set button label
        btnDescByWins = new JButton("Sort Drivers Table in Desc. Order of Wins");

        //Define activity when user clicks
        btnDescByWins.addActionListener(this);

        //Remove button focus
        btnDescByWins.setFocusable(false);

        //Set button text color
        btnDescByWins.setForeground(Color.white);

        //Set button color
        btnDescByWins.setBackground(Color.black);

        //Remove button border
        btnDescByWins.setBorderPainted(false);

        //Align to center
        btnDescByWins.setVerticalTextPosition(JButton.CENTER);

        buttons.add(btnDescByWins);

    }

    public void raceDatesHistoryBtn() {

        //Set button label
        btnRaceDatesHistory = new JButton("Race History");

        //Define activity when user clicks
        btnRaceDatesHistory.addActionListener(this);

        //Remove button focus
        btnRaceDatesHistory.setFocusable(false);

        //Set button text color
        btnRaceDatesHistory.setForeground(Color.white);

        //Set button color
        btnRaceDatesHistory.setBackground(Color.black);

        //Remove button border
        btnRaceDatesHistory.setBorderPainted(false);

        //Align to center
        btnRaceDatesHistory.setVerticalTextPosition(JButton.CENTER);

        buttons.add(btnRaceDatesHistory);

    }

    public void startRaceBtn() {

        //Set button label
        btnStartRace = new JButton("Race");

        //Define activity when user clicks
        btnStartRace.addActionListener(this);

        //Remove button focus
        btnStartRace.setFocusable(false);

        //Set button text color
        btnStartRace.setForeground(Color.white);

        //Set button color
        btnStartRace.setBackground(Color.black);

        //Remove button border
        btnStartRace.setBorderPainted(false);

        //Align to center
        btnStartRace.setVerticalTextPosition(JButton.CENTER);

        buttons.add(btnStartRace);
    }

    public void resetBtn() {

        //Set button label
        btnReset = new JButton("Reset races");

        //Define activity when user clicks
        btnReset.addActionListener(this);

        //Remove button focus
        btnReset.setFocusable(false);

        //Set button text color
        btnReset.setForeground(Color.white);

        //Set button color
        btnReset.setBackground(Color.black);

        //Remove button border
        btnReset.setBorderPainted(false);

        //Align to center
        btnReset.setVerticalTextPosition(JButton.CENTER);

        buttons.add(btnReset);
    }

    public void racePositions(int pstn, ArrayList<Integer> positions){

        //switch cases to define points for each position
        switch (pstn) {

            case 1: //1st place
                driver.drivers.get(positions.get(pstn)).setDriverWins(1);
                driver.drivers.get(positions.get(pstn)).setDriverPoints(25);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

            case 2: //2nd place
                driver.drivers.get(positions.get(pstn)).setDriverPoints(18);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

            case 3: //3rd place
                driver.drivers.get(positions.get(pstn)).setDriverPoints(15);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

            case 4: //4th place
                driver.drivers.get(positions.get(pstn)).setDriverPoints(12);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

            case 5: //5th place
                driver.drivers.get(positions.get(pstn)).setDriverPoints(10);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

            case 6: //6th place
                driver.drivers.get(positions.get(pstn)).setDriverPoints(8);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

            case 7: //7th place
                driver.drivers.get(positions.get(pstn)).setDriverPoints(6);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

            case 8: //8th place
                driver.drivers.get(positions.get(pstn)).setDriverPoints(4);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

            case 9: //9th place
                driver.drivers.get(positions.get(pstn)).setDriverPoints(2);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

            case 10:    //10th place
                driver.drivers.get(positions.get(pstn)).setDriverPoints(1);
                driver.drivers.get(positions.get(pstn)).setDriverTotalRaces(1);
                break;

        }
    }

    //Display season champion
    public void seasonChampion(){

        driver.sortPointsDesc();

        methodMessage.setText(
                "This year's champion is " +
                        driver.drivers.get(9).getDriverFName() + " " + driver.drivers.get(9).getDriverLName() +
                        " of " + driver.drivers.get(9).getDriverTeamName() +
                        " with " + driver.drivers.get(9).getDriverPoints()
        );

        gridEditor.anchor = GridBagConstraints.CENTER;

        gridEditor.weightx = 0;
        gridEditor.weighty = 0;
        gridEditor.gridx = 1;
        gridEditor.gridy = 1;

        panel.add(methodMessage, gridEditor);

    }

    //
    public void actionPerformed(ActionEvent e) {

        int descByPointsBtnClicks = 0,
            descByWinsBtnClicks = 0;

        if (e.getSource() == btnDescByPoints) {

            if (descByPointsBtnClicks == 0) {

                showDriversDescByPoints();
                descByPointsBtnClicks++;
            }
        }

        if (e.getSource() == btnAscByPoints) {

            showDriversAscByPoints();
        }

        if (e.getSource() == saveToDatabase) {

            try {
                saveToDatabase();

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }

        if (e.getSource() == loadFromDatabase) {

            try {
                loadFromDatabase();

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }

        if(e.getSource() == btnDescByWins){

            if (descByWinsBtnClicks == 0) {

                showDriversDescByWins();
                descByWinsBtnClicks++;
            }
        }

        if (e.getSource() == btnStartRace) {

            try {

                startRace();

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
        if (e.getSource() == btnReset) {

            try {

                clearDatabase();

            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }

        if (e.getSource() == btnRaceDatesHistory) {

            raceDatesHistory();
        }
    }

    //Clear screen before implementing certain methods
    public void clearApplicationScreen() {

        methodMessage.setText("");

        if (displayDrivers[0] != null) {
            for (int i = 0; i < displayDrivers.length; i++) {
                displayDrivers[i].setText("");
            }
        }

        for (int i = 0; i < raceDatesHistory.size(); i++) {
            raceDatesHistory.get(i).setText("");
        }

        if (raceResults[0] != null) {
            for (int i = 0; i < raceResults.length; i++) {
                raceResults[i].setText("");
            }
        }

        this.add(methodMessage);
    }
}







