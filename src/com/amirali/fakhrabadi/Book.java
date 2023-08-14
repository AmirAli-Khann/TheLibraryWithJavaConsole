package com.amirali.fakhrabadi;

public class Book{
    private final String name;
    private final int ID;
    private boolean isAvailable;
    public static int index = 0;

    public static int NUMBER_OF_ALLOWED_BOOKS = 10;

    public Book(String name, int ID) {
        this.name = name;
        this.ID = ID;
        this.isAvailable = true;
    }

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public boolean isAvailableTheBook() {
        return isAvailable;
    }

    public void setAvailable() {  //Used in return book menu
        isAvailable = true;
    }

    public void setNotAvailable() { //Used in Borrow book menu
        isAvailable = false;
    }

    public static void increaseMemory(int amount) {
        InputController.showMassage("This Method Is Only for GOD Reachable ");
        NUMBER_OF_ALLOWED_BOOKS += amount;
        InputController.showMassage("\n Increased for " + amount + " More Space ");
    }

}
