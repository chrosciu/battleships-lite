package com.chrosciu;

import org.apache.commons.lang3.tuple.Triple;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.chrosciu.Result.FINISHED;
import static com.chrosciu.Direction.HORIZONTAL;
import static com.chrosciu.Direction.VERTICAL;
import static com.chrosciu.Field.of;

public class Game {
    public static void main(String[] args) {
        //create empty ship list...
        List<Triple<Field, Integer, Direction>> rv = new ArrayList<>();
        //... and fill it with ships placed on board
        rv.add(Triple.of(of(1, 1), 4, VERTICAL));
        rv.add(Triple.of(of(6, 7), 2, HORIZONTAL));
        //let's start the game
        Shooter shooter = new Shooter(rv);
        Scanner keyboard = new Scanner(System.in);
        //read user shots
        for (;;) {
            //read field coordinates...
            System.out.println("enter a");
            int a = keyboard.nextInt();
            System.out.println("enter b");
            int b = keyboard.nextInt();
            //... and take shot !
            Result result = shooter.shoot(of(a, b));
            System.out.println(result);
            //if all ships sunk finish the game
            if (FINISHED == result) {
                break;
            }
        }
    }

}
