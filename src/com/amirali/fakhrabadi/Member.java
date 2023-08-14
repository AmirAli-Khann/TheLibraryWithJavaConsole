package com.amirali.fakhrabadi;

public class Member {
    private final String name;
    private String password;
    private String membership = "511";
    private String adviseBook;
    private boolean hasAdvice = false;
    private boolean isNew;

    public static int index = 0;
    private int numberOfTakenBooks = 0;
    private final Book[] takenBooks = new Book[10];


    public static int NUMBER_OF_ALLOWED_MEMBERS = 10;


    public Member(String name, String socialNumber, String password) {
        this.name = name;
        this.membership += socialNumber;
        this.password = password;
        isNew = true;
    }



    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getMembership() {
        return membership;
    }

    public void addBook(Book book) {
        takenBooks[numberOfTakenBooks] = book;
        numberOfTakenBooks++;
    }

    public Book getTakenBook(int index) { // Index increment is the responsibility of the reference method.
        return takenBooks[index];
        }

    public void returnBook(int index) {
        for (int i = index; i < numberOfTakenBooks - 1; i++) {
            takenBooks[i] = takenBooks[i + 1];
        }
        if (index == numberOfTakenBooks - 1) {
            takenBooks[index] = null;
        }
        numberOfTakenBooks--;
    }

    public int getNumberOfTakenBooks() {
        return numberOfTakenBooks;
    }

    public boolean getHasAdvice() {
        return hasAdvice;
    }

    public String getAdviseBook() {       // Uses by admin
        return adviseBook;
    }

    public void resetAdviseBook() {        // Uses by admin
        hasAdvice = false;
    }

    public void adviseBook(String name) {     //uses by member
        adviseBook = name;
        hasAdvice = true;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public void setIsNew() {
        this.isNew = false;
    }

    public void changePassword(String password) {
        this.password = password;
        InputController.showMassage("Password Changed ");
    }

    public static void increaseMemory(int amount) {
        InputController.showMassage("This Method Is Only for GOD Reachable ");
        NUMBER_OF_ALLOWED_MEMBERS += amount;
        InputController.showMassage("\n Increased for " + amount + " More Space ");
    }

}
