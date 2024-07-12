package eu.chrost;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static eu.chrost.Orientation.HORIZONTAL;
import static eu.chrost.Orientation.VERTICAL;
import static eu.chrost.Result.FINISHED;

public class Game {
    public static void main(String[] args) {
        //create empty ship list...
        List<ShipDefinition> rv = new ArrayList<>();
        //... and fill it with ships placed on board
        rv.add(new ShipDefinition(new Field(1, 1), 4, VERTICAL));
        rv.add(new ShipDefinition(new Field(6, 7), 2, HORIZONTAL));
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
            var result = shooter.shoot(new Field(a, b));
            System.out.println(result);
            //if all ships sunk finish the game
            if (FINISHED == result) {
                break;
            }
        }
    }

}
