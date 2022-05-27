package com.company;

//UoW ID - w1810019
//IIT ID - 20200312

import java.io.IOException;

interface ChampionshipManager {

    void saveToDatabase() throws IOException;
    void loadFromDatabase() throws IOException;
    void seasonChampion();
    void showDriversAscByPoints();
}
