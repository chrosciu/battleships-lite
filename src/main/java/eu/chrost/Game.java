package eu.chrost;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static eu.chrost.Shooter.point;

public class Game {
    public static void main(String[] args) {
        //create empty ship list...
        List<Ship> rv = new ArrayList<>();
        //... and fill it with ships placed on board
        rv.add(new Ship(point(1, 1), 4, true));
        rv.add(new Ship(point(6, 7), 2, false));
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
            int r = shooter.shoot(point(a, b));
            System.out.println(r);
            //if all ships sunk finish the game
            if (3 == r) {
                break;
            }
        }
    }

}
