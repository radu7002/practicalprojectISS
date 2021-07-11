package sda.practical.app;

import java.sql.SQLOutput;
import java.util.Scanner;

public class Menu {

    Scanner in = new Scanner(System.in);
    int readInt = 0;
    public int Menu() {
        do {
            System.out.println("------- Please enter an option -------");
            System.out.println(">Type 1 for a distance between two coordinates...");
            System.out.println(">Type 2 for calculating needed speed between two coordinates...");
            System.out.println(">Type 3 to show people on the ISS...");
            System.out.println(">Type 4 to exit...");
            readInt = in.nextInt();

            if(readInt != 1 && readInt !=2 && readInt != 3 && readInt != 4){
                System.out.println("Wrong command...");
            }
            else
                if (readInt == 4)
                    break;

        }
        while (readInt == 1 || readInt == 2 || readInt == 3);
        return readInt;
    }
}
