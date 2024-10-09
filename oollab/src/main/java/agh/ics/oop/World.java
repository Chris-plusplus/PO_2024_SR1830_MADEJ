package agh.ics.oop;

import agh.ics.oop.model.MoveDirection;

public class World {
    private static void run(MoveDirection[] directions){
        System.out.println("Start");
        for(var arg : directions){
            switch (arg){
                case FORWARD: System.out.println("Zwierzak idzie do przodu"); break;
                case BACKWARD: System.out.println("Zwierzak idzie do tyłu"); break;
                case RIGHT: System.out.println("Zwierzak skręca w prawo"); break;
                case LEFT: System.out.println("Zwierzak skręca w lewo"); break;
            }
        }
        System.out.println("Stop");
    }
    public static void main(String[] args){
        run(OptionsParser.parse(args));
    }
}

