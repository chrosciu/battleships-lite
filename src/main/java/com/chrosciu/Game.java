package com.chrosciu;

import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        //create empty ship list...
        List<Triple<Field, Integer, Boolean>> rv = new ArrayList<>();
        //... and fill it with ships placed on board
        rv.add(Triple.of(Field.of(1, 1), 4, true));
        rv.add(Triple.of(Field.of(6, 7), 2, false));
        //let's start the game
        Shooter shooter = new Shooter(rv);
        Scanner keyboard = new Scanner(System.in);
        //read user shots
        while (true) {
            System.out.println("enter field coordinates:");
            System.out.println("enter x coordinate: ");
            int xCoordinate = keyboard.nextInt();
            System.out.println("enter y coordinate: ");
            int yCoordinate = keyboard.nextInt();
            //... and take shot !
            int r = shooter.shoot(Field.of(xCoordinate, yCoordinate));
            System.out.println(r);
            //if all ships sunk finish the game
            if (3 == r) {
                break;
            }
        }
    }
}
