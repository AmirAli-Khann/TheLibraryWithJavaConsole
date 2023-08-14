package com.amirali.fakhrabadi;

public class Main {
    public static void main(String[] args){
        InputController.showMassage("");
        InputController.showMassage("****Welcome to Our Library****");
        boolean quit = false;
        while (!quit) {
            InputController.showMassage("");
            InputController.showMassage("Login as Admin ");
            InputController.showMassage("Login as Member ");
            InputController.showMassage("Exit ");

            String command = InputController.getCommand("");

            switch (command.toUpperCase()) {
                case "GOD":
                    InputController.showMassage("GOD, You Can Increase Memory Rang for Admins and Members and Books ");
                    InputController.showMassage("Also You Can Add Many of Admins ");
                    Menus.godMenu();
                    break;
                case "ADMIN":
                    Menus.adminMenu();
                    break;
                case "MEMBER":
                    Menus.membersMenu();
                    break;
                case "EXIT":
                    quit = true;
                    InputController.showMassage("           Powered By Amir A.Fakhrabadi\u00A9");
                    break;
                default:
                    InputController.showMassage("***Invalid Command***");
            }
        }

    }
}
