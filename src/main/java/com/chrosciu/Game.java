package com.chrosciu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.chrosciu.Direction.HORIZONTAL;
import static com.chrosciu.Direction.VERTICAL;

public class Game {
    public static void main(String[] args) {
        List<Ship> ships = Arrays.asList(
            Ship.of(Field.of(1, 1), 4, VERTICAL),
            Ship.of(Field.of(6, 7), 2, HORIZONTAL)
        );
        Shooter shooter = new Shooter(ships);
        Scanner keyboard = new Scanner(System.in);
        //let's start the game
        //read user shots
        for (;;) {
            //read field coordinates...
            System.out.println("enter a");
            int a = keyboard.nextInt();
            System.out.println("enter b");
            int b = keyboard.nextInt();
            //... and take shot !
            Result result = shooter.shoot(Field.of(a, b));
            System.out.println(result);
            //if all ships sunk finish the game
            if (Result.FINISHED == result) {
                break;
            }
        }
    }

}
