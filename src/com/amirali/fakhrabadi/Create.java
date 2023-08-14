package com.amirali.fakhrabadi;

import java.util.ArrayList;
import java.util.List;

public class Create {
    public static List<Admin> admins = new ArrayList<>();
    public static List<Member> members = new ArrayList<>();
    public static List<Book> books = new ArrayList<>();


    public static void createDefaultAdmin() {
        admins.add(new Admin());
        Admin.index++;
    }

    public static void createAdminByGOD(String name, String ID) {
        admins.add(new Admin(name, ID));
        Admin.index++;
    }

    public static int createBook() {
        if (Book.index < Book.NUMBER_OF_ALLOWED_BOOKS) {
            String name = InputController.getCommand("Please Enter the Name of Book ");
            int ID = InputController.getInteger("Please Choose an Uniq ID for the Book ");
            for (Book book : books) {
                if (ID == book.getID()) {
                    return -1;
                }
            }
            books.add(new Book(name, ID));
            Book.index++;
            InputController.showMassage("The Book " + name + " ID: " + ID + " Added");
            System.out.print(Create.books.size());
            return 1;
        }
        return -2;
    }

    public static boolean createMember() {
        if (Member.index < Member.NUMBER_OF_ALLOWED_MEMBERS) {
            String name = InputController.getCommand("Please Enter Your Full Name(for ex: Amir A.Fakhrabadi)");
            String socialNumber = InputController.getCommand("Please Enter Your Your Social Number ");
            String password = InputController.getCommand("Please Enter Your Your Password ");
            members.add(new Member(name, socialNumber, password));
            Member.index++;
            return true;
        }
        return false;
    }

}
