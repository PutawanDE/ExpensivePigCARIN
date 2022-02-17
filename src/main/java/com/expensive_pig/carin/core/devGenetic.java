package com.expensive_pig.carin.core;

import java.util.HashMap;

public class devGenetic {
    HashMap<String,String> AntiGenetic = new HashMap<>();
    HashMap<String,String> ViusGenetic = new HashMap<>();
    public void initAntiGenetic(){
        AntiGenetic.put("A1","src/main/java/com/expensive_pig/carin/dev_genetic/Anti1.txt");
        AntiGenetic.put("A2","src/main/java/com/expensive_pig/carin/dev_genetic/Anti2.txt");
        AntiGenetic.put("A3","src/main/java/com/expensive_pig/carin/dev_genetic/Anti3.txt");
    }

    public void initVirusGenetic(){
        ViusGenetic.put("V1","src/main/java/com/expensive_pig/carin/dev_genetic/Virus1.txt");
        ViusGenetic.put("V2","src/main/java/com/expensive_pig/carin/dev_genetic/Virus1.txt");
        ViusGenetic.put("V3","src/main/java/com/expensive_pig/carin/dev_genetic/Virus1.txt");
    }


}
