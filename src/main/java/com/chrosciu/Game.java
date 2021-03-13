package com.chrosciu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.chrosciu.Orientation.HORIZONTAL;
import static com.chrosciu.Orientation.VERTICAL;
import static com.chrosciu.Result.FINISHED;

public class Game {
    public static void main(String[] args) {
        List<Ship> ships = new ArrayList<>();
        ships.add(Ship.of(Field.of(1, 1), 4, VERTICAL));
        ships.add(Ship.of(Field.of(6, 7), 2, HORIZONTAL));
        Shooter shooter = new Shooter(ships);
        Scanner keyboard = new Scanner(System.in);
        while (true) {
            System.out.println("enter field coordinates:");
            System.out.println("enter x coordinate: ");
            int xCoordinate = keyboard.nextInt();
            System.out.println("enter y coordinate: ");
            int yCoordinate = keyboard.nextInt();
            //... and take shot !
            Result r = shooter.takeShot(Field.of(xCoordinate, yCoordinate));
            System.out.println(r);
            if (FINISHED == r) {
                break;
            }
        }
    }
}
