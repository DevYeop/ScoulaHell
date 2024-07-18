package org.scoula.lib.cli.ui;

import org.scoula.lib.cli.command.Command;
import org.scoula.lib.cli.exception.BadMenuException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {


    private List<MenuItem> menu = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void add(MenuItem item) {
        menu.add(item);
    }

    public void printMenu() {
        System.out.println("--------------------------------");
        for (int i = 0; i < menu.size(); i++) {
            System.out.print((i + 1) + "." + menu.get(i).getTitle());
            System.out.print(" ");
        }
        System.out.println();
        System.out.println("--------------------------------");
    }

    public Command getSelect() throws BadMenuException {
        System.out.print("Select option: ");
        int choice = scanner.nextInt();
        if (choice < 1 || choice > menu.size()) {
            throw new BadMenuException();
        }
        return menu.get(choice - 1).getCommand();
    }
}
