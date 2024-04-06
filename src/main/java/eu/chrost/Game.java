package eu.chrost;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static eu.chrost.Orientation.HORIZONTAL;
import static eu.chrost.Orientation.VERTICAL;
import static eu.chrost.Result.FINISHED;
import static eu.chrost.Point.point;

class Game {
    public static void main(String[] args) {
        //create empty ship list...
        List<Shooter.Ship> rv = new ArrayList<>();
        //... and fill it with ships placed on board
        rv.add(Shooter.Ship.of(point(1, 1), 4, VERTICAL));
        rv.add(Shooter.Ship.of(point(6, 7), 2, HORIZONTAL));
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
            Result result = shooter.shoot(point(a, b));
            System.out.println(result);
            if (FINISHED == result) {
                break;
            }
        }
    }

}
