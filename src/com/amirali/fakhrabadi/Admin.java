package com.amirali.fakhrabadi;

public class Admin {
    private final String ID;
    private String password;
    private final String name;
    public static int index = 0;
    public static int NUMBER_OF_ALLOWED_ADMINS = 10;

    public Admin() {
        this.name = "default";
        this.ID ="946572747";
        this.password = "0000";
    }

    public Admin(String name, String ID) {
        this.name = name;
        this.ID = ID;
        this.password = "0000";
    }

    public String getId() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String password) {
        this.password = password;
        InputController.showMassage("Password Changed ");
    }

    public static void increaseMemory(int amount) {
        InputController.showMassage("This Method Is Only for GOD Reachable ");
        NUMBER_OF_ALLOWED_ADMINS += amount;
        InputController.showMassage("\n Increased for " + amount + " More Space ");
    }
}
