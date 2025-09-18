package com.wipro.bank.main;
import com.wipro.bank.service.BankService;
import java.util.Scanner;


public class MainClass {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Tenure (in years): ");
        int tenure = sc.nextInt();

        System.out.print("Enter Principal: ");
        float principal = sc.nextFloat();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Gender: ");
        String gender = sc.next();

        BankService service = new BankService();
        service.calculate(principal, tenure, age, gender);

        sc.close();
    }


}
