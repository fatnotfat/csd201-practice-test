/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BigNumPro;

import java.util.Scanner;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class Input {
    public static char inputChar(Scanner sc, String message) {
        System.out.print(message);
        char result;
        String tmp = sc.next();
        tmp = tmp.trim();
        result = tmp.charAt(0);
        
        return result;
    }
    
    
    public static int inputInt(Scanner sc, String message) {
        System.out.print(message);
        int result = 0;
        try {
            result = sc.nextInt();
        } catch (Exception e) {
            System.out.println("ERROR: something went wrong!");
        }
        
        return result;
    }
    
    public static double inputDouble(Scanner sc, String message) {
        System.out.print(message);
        double result = 0;
        try {
            result = sc.nextDouble();
        } catch (Exception e) {
            System.out.println("ERROR: something went wrong!");
        }
        
        return result;
    }
    
    public static String inputStringInorgeSpace(Scanner sc, String message) {
        System.out.print(message);
        String result;
        result = sc.nextLine();
        
        return result;
    }
    
    public static String inputString(Scanner sc, String message) {
        System.out.print(message);
        String result;
        result = sc.next();
        return result;
    }
    
    public static int [] inputIntArray() {
        Scanner sc = new Scanner(System.in);
        int n = inputInt(sc, "Input number of elements: ");
        int [] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = inputInt(sc, "Input element " + i + ": ");
        }
        
        return a;
    }
}
