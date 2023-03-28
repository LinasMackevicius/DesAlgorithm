package org.example;

import java.util.Scanner;
public class GetInput {
    public String returnMyString() {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.print(">\t");

        String myString = myObj.nextLine();  // Read user input

        return myString;
    }
}
