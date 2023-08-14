package com.amirali.fakhrabadi;

public class Menus {
    private static String command;
    private static boolean quit = false;

    public static void adminMenu() {
        quit = false;
        if (Admin.index == 0) {
            Create.createDefaultAdmin();
        }

        String nameOrID = InputController.getCommand("Please Enter Your Name or ID ");
        String password = InputController.getCommand("Please Enter Your Password ");
        if (Decision.adminConfirm(nameOrID, password)) {
            while (!quit) {
                InputController.showMassage("");
                InputController.showMassage("***If You Login for the First Time, Please Change Your Password***");
                InputController.showMassage("Press 1 To View Members ");
                InputController.showMassage("Press 2 To View Books List by Name ");
                InputController.showMassage("Press 3 To View Books List by ID ");
                InputController.showMassage("Press 4 To View Available Books ");
                InputController.showMassage("Press 5 To Add Book ");
                InputController.showMassage("Press 6 To Remove Book ");
                InputController.showMassage("Press 7 To View Advices ");
                InputController.showMassage("Press 8 To Membership Confirmation ");
                InputController.showMassage("Press 9 To Change Your Password ");
                InputController.showMassage("Back ");

                command = InputController.getCommand("");

                switch (command.toUpperCase()) {
                    case "1":
                        if (Decision.viewMembersList()) {
                            InputController.showMassage("There Is No Member ");
                        }
                        break;
                    case "2":
                        if (Decision.viewBookListByName()) {
                            InputController.showMassage("There Is No Book ");
                        }
                        break;
                    case "3":
                        if (Decision.viewBookListByID()) {
                            InputController.showMassage("There Is No Book ");
                        }
                        break;
                    case "4":
                        if (Decision.viewAvailableBook()) {
                            InputController.showMassage("There Is No Available Book ");
                        }
                        break;
                    case "5":
                        int a = Create.createBook();
                        if (a == -2) {
                            InputController.showMassage("Memory Out of Range ");
                            InputController.showMassage("Please Ask Supervisor to Increase Memory ");
                        } else if (a == -1) {
                            InputController.showMassage("The Value Doesn't Uniq. Please Go to Menu->Sort Book List by ID and Check Free ID's");
                        }
                        break;
                    case "6":
                        if (Decision.deleteBook()) {
                            InputController.showMassage("There Is No Book ");
                        }
                        break;
                    case "7":
                        if (Decision.checkAdvices()) {
                            InputController.showMassage("There Is No Advice ");
                        }
                        break;
                    case "8":
                        Decision.membersConfirmationByAdmin();
                        break;
                    case "9":
                        command = InputController.getCommand("Please Enter New Password ");
                        Decision.changePasswordAdmin(command);
                        break;
                    case "BACK":
                        quit = true;
                        break;
                    default:
                        InputController.showMassage("Invalid Command ");
                }
            }
        }
    }

    public static void membersMenu() {
        quit = false;
        while (!quit) {
            InputController.showMassage("_____________");
            InputController.showMassage("Press 1 To Sign up(Get Membership) ");
            InputController.showMassage("Press 2 To Sign in ");
            InputController.showMassage("Press 3 To Sign as Guest ");
            InputController.showMassage("Back ");

            command = InputController.getCommand("");

            switch (command.toUpperCase()) {
                case "1":
                    if (!Create.createMember()) {
                        InputController.showMassage("Memory Out of  Range");
                        InputController.showMassage("Please Ask Supervisor to Increase Memory ");
                    } else {
                        InputController.showMassage(Create.members.get(Member.index-1).getName() + " Your Membership Is " + Create.members.get(Member.index-1).getMembership());
                        InputController.showMassage("Please Wait for Approval by the Administrator ");
                        InputController.getCommand("Press Enter to Continue ");
                    }
                    break;
                case "2":
                    command = InputController.getCommand("Please Enter Your Name or Membership ");
                    String password = InputController.getCommand("Please Enter Your Password ");
                    Member member = Decision.memberConfirm(command, password);
                    if (member != null) {
                        membershipMenu(member);
                    }
                    break;
                case "3":
                    guestMenu();
                    quit = false;
                    break;
                case "BACK":
                    quit = true;
                    break;
                default:
                    InputController.showMassage("**Invalid Command**");
            }
        }
    }

    public static void godMenu() {
        quit = false;
        while (!quit) {
            InputController.showMassage("");
            InputController.showMassage("Press 1 To Increase Memory Range of Admin ");
            InputController.showMassage("Press 2 To Increase Memory Range of Member ");
            InputController.showMassage("Press 3 To Increase Memory Range of Book ");
            InputController.showMassage("Press 4 To Add New Admin ");
            InputController.showMassage("Back ");
            command = InputController.getCommand("");

            switch (command.toUpperCase()) {
                case "1":
                    InputController.showMassage("***Increment Memory Range of Admin***");
                    Admin.increaseMemory(InputController.getInteger("Please Enter The Amount That You Want Increase "));
                    break;
                case "2":
                    InputController.showMassage("***Increment Memory Range of Member***");
                    Member.increaseMemory(InputController.getInteger("Please Enter The Amount That You Want Increase "));
                    break;
                case "3":
                    InputController.showMassage("***Increment Memory Range of Book***");
                    Book.increaseMemory(InputController.getInteger("Please Enter The Amount That You Want Increase "));
                    break;
                case "4":
                    String name = InputController.getCommand("Name: ");
                    String ID = InputController.getCommand("Specific ID: ");
                    Create.createAdminByGOD(name,ID);
                    break;
                case "5":
                    for (Admin admin : Create.admins) {
                        System.out.println(admin.getName() + " " + admin.getId());
                        System.out.println("_________________________");
                    }
                    break;
                case "BACK":
                    quit = true;
                    break;
                default:
                    InputController.showMassage("Invalid Command ");
            }
        }
    }

    private static void guestMenu() {
        quit = false;
        InputController.showMassage("***Welcome***");
        InputController.showMassage("You Signed as a Guest, So There Are Many Limitations ");
        InputController.showMassage("Also, You Can Sign Up for Free ");
        while (!quit) {
            InputController.showMassage("_____________");
            InputController.showMassage("Press 1 to View All Books ");
            InputController.showMassage("Press 2 to View Available Books ");
            InputController.showMassage("Back ");

            command = InputController.getCommand("");

            switch (command.toUpperCase()) {
                case "1":
                    if (Decision.viewBookListByName()) {
                        InputController.showMassage("There Is No Book ");
                    }
                    break;
                case "2":
                    if (Decision.viewAvailableBook()) {
                        InputController.showMassage("There Is No Book ");
                    }
                    break;
                case "BACK":
                    quit = true;
                    break;
                default:
                    InputController.showMassage("Invalid Command ");
            }

        }

    }

    private static void membershipMenu(Member member) {
        quit = false;
        InputController.showMassage("Welcome " + member.getName());

        while (!quit) {
            InputController.showMassage("_____________");
            InputController.showMassage("Press 1 To View Books List by Name ");
            InputController.showMassage("Press 2 To View Books List by ID ");
            InputController.showMassage("Press 3 To View Available Books ");
            InputController.showMassage("Press 4 to Borrow Book(s) ");
            InputController.showMassage("Press 5 to Return Book ");
            InputController.showMassage("Press 6 to Advise a Book ");
            InputController.showMassage("Press 7 to View Taken Book ");
            InputController.showMassage("Back ");

            command = InputController.getCommand("");

            switch (command.toUpperCase()) {
                case "1":
                    if (Decision.viewBookListByName()) {
                        InputController.showMassage("There Is No Book in Repository ");
                    }
                    break;
                case "2":
                    if (Decision.viewBookListByID()) {
                        InputController.showMassage("There Is No Book in Repository ");
                    }
                    break;
                case "3":
                    if (Decision.viewAvailableBook()) {
                        InputController.showMassage("There Is No Available Book ");
                    }
                    break;
                case "4":
                    if (Decision.borrowBook(member)) {
                        while (true) {
                            command = InputController.getCommand("Do You Want Any More Book? (Y/N)");
                            if (command.equalsIgnoreCase("Y")) {
                                Decision.borrowBook(member);
                            } else if (command.equalsIgnoreCase("N")) {
                                break;
                            } else {
                                InputController.showMassage("Invalid Command ");
                            }
                        }
                    }
                    break;
                case "5":
                    if (Decision.returnBook(member)) {
                        while (true) {
                            command = InputController.getCommand("Do You Want Any More Book? (Y/N)");
                            if (command.equalsIgnoreCase("Y")) {
                                Decision.returnBook(member);
                            } else if (command.equalsIgnoreCase("N")) {
                                break;
                            } else {
                                InputController.showMassage("Invalid Command ");
                            }
                        }
                    }
                    break;
                case "6":
                    if (member.getHasAdvice()) {
                        InputController.showMassage("Sorry, You Can Advise Only One Book. ");
                        InputController.showMassage("When Admin Checks Your Recent Suggest, Then You Can Make Another One ");
                    } else {
                        command=InputController.getCommand("Please Enter the Name Of The Book That You Want to Advise ");
                        Decision.makeAdvice(command);
                    }
                    break;
                case "7":
                    if (Decision.viewTakenBooks(member)) {
                        InputController.showMassage("You Have Never Borrowed Any Books before ");
                    }
                    break;
                case "BACK":
                    quit = true;
                    break;
                default:
                    InputController.showMassage("Invalid Command ");
            }
        }
    }
}
