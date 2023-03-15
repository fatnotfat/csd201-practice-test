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
public class Main {
    public static void main(String[] args) {
        String numA = "", numB = "";
        Scanner sc = new Scanner(System.in);
        numA = Input.inputStringInorgeSpace(sc, "Input number A: ");
        sc = new Scanner(System.in);
        numB = Input.inputStringInorgeSpace(sc, "Input number B: ");
        
        BigNumber big1, big2, addRe, subRe, mulRe, divRe;
        big1 = BigNumber.parse(numA);
        big2 = BigNumber.parse(numB);
        System.out.println("Computing....");
        addRe = big1.Add(big2);
        System.out.println("Result of A + B: " + addRe);
        System.out.println("Computing....");
        subRe = big1.Sub(big2);
        System.out.println("Result of A - B: " + subRe);
        System.out.println("Computing....");
        mulRe = big1.Mul(big2);
        System.out.println("Result of A * B: " + mulRe);
        System.out.println("Computing....");
        divRe = big1.Div(big2);
        System.out.println("Result of A / B: " + divRe);
    }
}
