package com.example.rpg.Calc.Mission;

import com.example.rpg.Calc.Error.Finish;
import com.example.rpg.Calc.Person2;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class MissionSab implements Serializable {
    public void receive(Person2 p, ArrayList<Mission> mission_all) {
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        ArrayList<Mission> missions = new ArrayList<>();
        while (i==0) {
            int endflg = 1;
            for (Mission mission : mission_all) {
                if (p.lv >= mission.dangerous_lv && !(mission.progress)) {
                    endflg = 0;
                    System.out.println(mission.name + " " + "報酬" + mission.reward + "$" + " [" + mission.code + "]");
                    missions.add(mission);
                }
            }
            if (endflg==1){
                System.out.println("って、もうミッションがないな");
                break;
            }
            System.out.println("戻る [goback]");
            String missionSelection = scanner.next();
            for (Mission mission : missions) {
                if (missionSelection.equals(mission.code)) {
                    mission.progress = true;
                    System.out.println(mission.name+"のミッションを受けた！");
                    System.out.println("ほかに受けたいミッションはあるか？");
                    endflg = 1;
                    break;
                }
            }
            if (missionSelection.equals("goback")) {
                System.out.println("そうか");
                i++;
            }else if (endflg==0){
                    System.out.println("ミッションの中から選んでくれ");
                }
            }
        }
    public void missionProgres(Mission successmission){
                successmission.progress = false;
                successmission.get_reward = true;
    }
}