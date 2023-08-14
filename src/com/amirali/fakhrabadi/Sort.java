package com.amirali.fakhrabadi;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Sort {

    public static LinkedList<String> sortByName() {
        LinkedList<String> sortedByName = new LinkedList<>();
        for (int i = 0; i < Book.index; i++) {
            addInOrder(sortedByName, Create.books.get(i).getName());
        }
        System.out.print(Create.books.size());
        return sortedByName;
    }

    private static void addInOrder(LinkedList<String> linkedList, String name) {
        ListIterator<String> iterator = linkedList.listIterator();
        while (iterator.hasNext()) {
            int comparison = iterator.next().compareTo(name);
            if (comparison == 0) {
                iterator.add(name);
                return;
            } else if (comparison > 0) {
                iterator.previous();
                iterator.add(name);
                return;
            }
        }
        iterator.add(name);
    }

    public static void sortByID(List<Book> list) {
        for (int i = 0; i < Book.index - 1; i++) {
            for (int j = i + 1; j < Book.index; j++) {
                int tempA = Create.books.get(i).getID();
                int tempB = Create.books.get(j).getID();
                if (tempA > tempB) {
                    Collections.swap(list, i, j);
                }
            }
        }
        System.out.print(Create.books.size());
    }
}

