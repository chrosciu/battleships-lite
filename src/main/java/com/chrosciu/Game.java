package com.chrosciu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.chrosciu.Direction.HORIZONTAL;
import static com.chrosciu.Direction.VERTICAL;
import static com.chrosciu.Field.of;
import static com.chrosciu.Result.FINISHED;

public class Game {
    public static void main(String[] args) {
        List<Ship> ships = new ArrayList<>();
        ships.add(Ship.of(Field.of(1, 1), 4, VERTICAL));
        ships.add(Ship.of(Field.of(6, 7), 2, HORIZONTAL));
        Shooter shooter = new Shooter(ships);
        Scanner keyboard = new Scanner(System.in);
        //read user shots
        for (;;) {
            //read field coordinates...
            System.out.println("enter a");
            int a = keyboard.nextInt();
            System.out.println("enter b");
            int b = keyboard.nextInt();
            Result result = shooter.shoot(of(a, b));
            System.out.println(result);
            if (FINISHED == result) {
                break;
            }
        }
    }

}
