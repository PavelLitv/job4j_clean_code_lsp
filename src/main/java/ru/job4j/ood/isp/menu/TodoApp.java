package ru.job4j.ood.isp.menu;

import ru.job4j.ood.isp.menu.actions.*;

import java.util.List;
import java.util.Scanner;

/**
 * 6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
 * Добавить элемент в корень меню;
 * Добавить элемент к родительскому элементу;
 * Вызвать действие, привязанное к пункту меню (действие можно сделать константой,
 * например, ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
 * Вывести меню в консоль.
 */
public class TodoApp {
    private final Output output;
    private final Menu menu = new SimpleMenu();

    public TodoApp(Output output) {
        this.output = output;
    }

    public void run(Input input, List<UserAction> actions) {
        boolean running = true;
        while (running) {
            showMenu(actions);
            int select = new Scanner(System.in).nextInt();
            if (select < 0 || select >= actions.size()) {
                System.out.println("Неверный ввод, вы можете выбрать: 0 ... " + (actions.size() - 1));
                continue;
            }
            UserAction action = actions.get(select);
            running = action.execute(input, menu);
        }
    }

    private void showMenu(List<UserAction> actions) {
        output.println("Меню:");
        for (int i = 0; i < actions.size(); i++) {
            output.println(i + ". " + actions.get(i).name());
        }
    }

    public static void main(String[] args) {
        Output output = new ConsoleOutput();
        Input input = new ConsoleInput();
        TodoApp app = new TodoApp(output);
        List<UserAction> actions = List.of(
                new CreateRootElement(output),
                new CreateChildElement(output),
                new ExecuteDelegate(output),
                new PrintUserMenu(output),
                new ExitApp(output)
        );
        app.run(input, actions);
    }
}
