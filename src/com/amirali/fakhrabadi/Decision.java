package com.amirali.fakhrabadi;

import java.util.LinkedList;

public class Decision {
    private static int indexOfCurrentMember;
    private static int indexOfCurrentAdmin;
    private static boolean flag = true;

    public static boolean adminConfirm(String nameOrID, String password) {
        indexOfCurrentAdmin = 0;
        for (Admin admin : Create.admins) {
            if (admin.getId().equals(nameOrID) || admin.getName().equalsIgnoreCase(nameOrID)) {
                if (admin.getPassword().equals(password)) {
                    InputController.showMassage("***You Signed in As a Admin***");
                    return true;
                } else {
                    InputController.showMassage("ERROR!!! Password Isn't Correct ");
                    return false;
                }
            }
            indexOfCurrentAdmin++;
        }
        InputController.showMassage("ERROR!!! Name or ID Doesn't Exist ");
        return false;
    }

    public static boolean viewMembersList() {
        flag = true;
        if (Member.index > 0) {
            InputController.showMassage("       ***Member List***");
            for (Member member : Create.members) {
                InputController.showMassage("Name: " + member.getName());
                InputController.showMassage("Membership: " + member.getMembership());
                if (member.getIsNew()) {
                    InputController.showMassage("Doesn't Approved Yet ");
                } else {
                    InputController.showMassage("Approved");
                }
                InputController.showMassage("_____________");
                flag = false;
            }
        }
        return flag;
    }

    public static boolean viewBookListByName() {      //Must be controlled
        flag = true;
        if (Book.index > 0) {
            LinkedList<String> sortedByName = Sort.sortByName();
            InputController.showMassage("      ***Book(s) List by Name***");
            int i = 0;
            for (String s : sortedByName) {
                InputController.showFormattedMassage(Create.books.get(i).getID(), s);
                flag = false;
                i++;
            }
            InputController.showMassage("_________________________");
        }
        return flag;
    }

    public static boolean viewBookListByID() {
        if (Book.index > 0) {
            Sort.sortByID(Create.books);
            InputController.showMassage("      ***Book(s) List by ID***");
            for (Book book : Create.books) {
                InputController.showFormattedMassage(book.getID(), book.getName());
            }

            InputController.showMassage("_________________________");
            return false;
        }
        return true;
    }

    public static boolean viewAvailableBook() {
        flag = true;
        if (Book.index > 0) {
            InputController.showMassage("      ***Available Book(s) List***");
            for (Book book : Create.books) {
                if (book.isAvailableTheBook()) {
                    InputController.showFormattedMassage(book.getID(), book.getName());
                    flag = false;
                }
            }
            InputController.showMassage("_________________________");
        }
        return flag;
    }

    public static boolean deleteBook() {
        if (Book.index > 0) {
            int i = 0;
            String command = InputController.getCommand("Please Enter Name of the Book That You Want to Delete ");
            for (Book book : Create.books) {
                if (command.equalsIgnoreCase(book.getName())) {
                    InputController.showMassage("The Book with Following Information Will Delete Permanently. Are You Sure(Y/N)? ");
                    InputController.showMassage("Name: " + book.getName() + " ID: " + book.getID() + " ");
                    command = InputController.getCommand("");
                    if (command.equalsIgnoreCase("y")) {
                        Create.books.remove(i);
                        Book.index--;
                    } else {
                        InputController.showMassage("deleting procedure terminated without delete any book ");
                    }
                    return false;
                }
                i++;
            }
            InputController.showMassage("The Name That You've Entered Doesn't Exist ");
            return false;
        }
        Create.books.remove(Book.index);
        System.out.print(Create.books.size());
        return true;
    }

    public static boolean checkAdvices() {
        flag = true;
        for (Member member : Create.members) {
            if (member.getHasAdvice()) {
                InputController.showMassage(member.getAdviseBook());
                member.resetAdviseBook();
                InputController.showMassage("______________");
                flag = false;
            }
        }
        return flag;
    }

    public static void membersConfirmationByAdmin() {
        if (Member.index > 0) {
            for (Member member : Create.members) {
                if (member.getIsNew()) {
                    InputController.showMassage("Would You Like to Confirm Following Information?(Y/N) ");
                    InputController.showMassage(member.getName() + " Membership ID: " + member.getMembership());
                    String command = InputController.getCommand("");
                    if (command.equalsIgnoreCase("Y")) {
                        member.setIsNew();
                    }
                }
            }
        } else InputController.showMassage("There Isn't Any New Member ");
    }

    public static void changePasswordAdmin(String command) {
        if (command.equals(InputController.getCommand("Please Enter New Password Again "))) {
            Create.admins.get(indexOfCurrentAdmin).changePassword(command);
        } else {
            InputController.showMassage("ERROR!!! Try Later ");
        }
    }

    public static Member memberConfirm(String nameOrMembership, String password) {
        if (Member.index > 0) {
            indexOfCurrentMember = 0;
            for (Member member : Create.members) {
                if (member.getName().equalsIgnoreCase(nameOrMembership) || member.getMembership().equals(nameOrMembership)) {
                    if (member.getPassword().equals(password)) {
                        if (member.getIsNew()) {
                            InputController.showMassage("Please Wait Till Approval by the Administrator ");
                            return null;
                        }
                        return member;
                    } else {
                        InputController.showMassage("ERROR!!! Password Isn't Correct ");
                        return null;
                    }
                }
                indexOfCurrentMember++;
            }
            InputController.showMassage("ERROR!!! Name or ID Doesn't Exist ");
            return null;
        }
        InputController.showMassage("There Is No Any Membership. Please Create a new");
        return null;
    }

    public static boolean borrowBook(Member member) {
        if (Book.index > 0) {
            String command = InputController.getCommand("Please Enter the Name of the Book That You Want ");
            for (Book book : Create.books) {
                if (book.getName().equalsIgnoreCase(command) && book.isAvailableTheBook()) {
                    InputController.showMassage("Do You Want to Borrow the Following Book? (Y/N) ");
                    InputController.showFormattedMassage(book.getID(), book.getName());
                    command = InputController.getCommand("");
                    if (command.equalsIgnoreCase("Y")) {
                        member.addBook(book);
//                        Create.members.add(indexOfCurrentMember, member);
                        book.setNotAvailable();
                    } else {
                        InputController.showMassage("No One Book Added ");
                    }
                    return true;
                }
            }
            InputController.showMassage("No Book with This Name Was Found ");
        } else {
            InputController.showMassage("There Is No Book in Repository");
        }
        return false;
    }

    public static boolean returnBook(Member member) {
        if (member.getNumberOfTakenBooks() > 0) {
            InputController.showMassage("***Return a Book*** ");
            InputController.showMassage("If You Have the ID of the Book Type : I ");
            InputController.showMassage("If You Have the Name of the Book Type : N ");
            String command = InputController.getCommand("");
            if (command.equalsIgnoreCase("N")) {
                command = InputController.getCommand("PLease Enter the Name That You Want to Return");
                for (int i = 0; i < member.getNumberOfTakenBooks(); i++) {
                    Book book = member.getTakenBook(i);
                    if (book.getName().equalsIgnoreCase((command))) {
                        InputController.showMassage("Following Book Will be Return, Are You Sure?(Y/N)");
                        InputController.showFormattedMassage(book.getID(), book.getName());
                        command = InputController.getCommand("");
                        if (command.equalsIgnoreCase("Y")) {
                            member.returnBook(i);
                            Create.members.add(indexOfCurrentMember, member);
                            InputController.showMassage("The Book Returned Successfully ");
                            for (int j = 0; j < Book.index; j++) {
                                if (book.getID() == Create.books.get(j).getID()) {
                                    Create.books.get(j).setAvailable();
                                    return true;
                                }
                            }
                        } else {
                            InputController.showMassage("Try Later! ");
                        }
                    }
                }
                InputController.showMassage("You Have Never Borrowed Such a Book Before ");
            } else if (command.equalsIgnoreCase("I")) {
                int temp = InputController.getInteger("Please Enter the ID That You Want to Return ");
                for (int i = 0; i < member.getNumberOfTakenBooks(); i++) {
                    Book book = member.getTakenBook(i);
                    if (book.getID() == temp) {
                        InputController.showMassage("Following Book Will be Return, Are You Sure ?(Y/N)");
                        InputController.showFormattedMassage(book.getID(), book.getName());
                        command = InputController.getCommand("");
                        if (command.equalsIgnoreCase("Y")) {
                            member.returnBook(i);
                            Create.members.add(indexOfCurrentMember, member);
                            InputController.showMassage("The Book Returned Successfully ");
                            for (int j = 0; j < Book.index; j++) {
                                if (book.getID() == Create.books.get(j).getID()) {
                                    Create.books.get(j).setAvailable();
                                    return true;
                                }
                            }
                        } else {
                            InputController.showMassage("Try Later! ");
                        }
                    }
                }
                InputController.showMassage("You Have Never Borrowed Such a Book Before With That ID");
            } else {
                InputController.showMassage("Invalid Command ");
                InputController.showMassage("Try Later ");
            }
        } else InputController.showMassage("You Have Never Borrowed Any Books Before ");
        return false;
    }

    public static void makeAdvice(String name) { // check shavad
        Create.members.get(indexOfCurrentMember).adviseBook(name);
        InputController.showMassage("It Sent to Admin, It Will Be Added to Repository as Soon as Possible ");
    }

    public static boolean viewTakenBooks(Member member) {
        if (member.getNumberOfTakenBooks() > 0) {
            for (int i = 0; i < member.getNumberOfTakenBooks(); i++) {
                Book book = member.getTakenBook(i);
                InputController.showFormattedMassage(book.getID(), book.getName());
            }
            return false;
        }
        return true;
    }

}