package com.juaracoding;

import java.util.Scanner;

public class Percabangan {
    public static void main(String[] args) {
        //login
        Scanner scanner = new Scanner(System.in);

        System.out.print("Username: ");
        String username = scanner.nextLine();

        System.out.print("Password: ");
        String password = scanner.nextLine();
    

        if(username.equals("admin") && password.equals("12345")){
            System.out.println("Login berhasil");
        }else{
            System.out.println("Login gagal");
        }
        }
}
