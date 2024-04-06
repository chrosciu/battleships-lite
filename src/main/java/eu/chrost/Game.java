package eu.chrost;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static eu.chrost.Orientation.HORIZONTAL;
import static eu.chrost.Orientation.VERTICAL;
import static eu.chrost.Result.FINISHED;
import static eu.chrost.Field.of;

class Game {
    public static void main(String[] args) {
        //create empty ship list...
        List<ShipDefinition> shipDefinitions = new ArrayList<>();
        //... and fill it with ships placed on board
        shipDefinitions.add(ShipDefinition.of(Field.of(1, 1), 4, VERTICAL));
        shipDefinitions.add(ShipDefinition.of(Field.of(6, 7), 2, HORIZONTAL));
        //let's start the game
        Shooter shooter = new Shooter(shipDefinitions);
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
            if (FINISHED == result) {
                break;
            }
        }
    }

}
