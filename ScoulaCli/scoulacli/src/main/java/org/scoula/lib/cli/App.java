package org.scoula.lib.cli;

import org.scoula.lib.cli.command.*;
import org.scoula.lib.cli.exception.BadMenuException;
import org.scoula.lib.cli.ui.Menu;
import org.scoula.lib.cli.ui.MenuItem;

public abstract class App {
    Menu menu;
    Command[] commands;

    public App() {

    }

    public void executeCommand(int selectNo) {
        Command command = commands[selectNo-1];
        command.execute();
    }


    public void init() {
        menu = new Menu();
        createMenu(menu);
        menu.add(new MenuItem("종료", new ExitCommand()));
    }

    public void createMenu(Menu menu) {
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void run() {
        init();
        while (true) {
            menu.printMenu();
            try {
                Command command = menu.getSelect();
                command.execute();
            } catch (BadMenuException e) {
                e.printStackTrace();
//                System.out.println(e.getMessage());
            }
        }
    }

}
