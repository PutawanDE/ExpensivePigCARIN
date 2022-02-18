package com.expensive_pig.carin.core;


import com.expensive_pig.carin.evaluator.Program;
import com.expensive_pig.carin.evaluator.ReadGeneticCode;
import com.expensive_pig.carin.evaluator.SyntaxError;

public class InitGame {
    private final Program[] anti = new Program[3];
    private final Program[] virus = new Program[3];
    private final devGenetic geneticloader = new devGenetic();
    private final ReadGeneticCode programeReader = new ReadGeneticCode();


    public InitGame()  {

        geneticloader.initAntiGenetic();
        geneticloader.initVirusGenetic();


    }

    public Program[] InitVirus() throws SyntaxError {
                /*
         receive user Virus genetic . which gen user manually upload use String
         */
        boolean whichmanuallyupload = false;
        String userGenetic = "";
        /*    ...
         */
        int i = 0;
        for (String gen : geneticloader.ViusGenetic.keySet()) {
            if (whichmanuallyupload) {
                virus[i] = programeReader.getProgrambyString(geneticloader.ViusGenetic.get(gen));
            }else {
                virus[i] = programeReader.getProgrambyPath(geneticloader.ViusGenetic.get(gen));
            }
            i++;
        }
        return virus;
    }

    public Program[] InitAnti() throws SyntaxError {
        /*
         receive user Anti genetic . which gen user manually upload use String
         */
        boolean whichmanuallyupload = false;
        String userGenetic = "";
        /*    ...
         */
        int i = 0;
        for (String gen : geneticloader.AntiGenetic.keySet()) {

            if (whichmanuallyupload) {
                anti[i] = programeReader.getProgrambyString(geneticloader.AntiGenetic.get(gen));
            } else {
                anti[i] = programeReader.getProgrambyPath(userGenetic);
            }
            i++;
        }
        return anti;
    }
}
