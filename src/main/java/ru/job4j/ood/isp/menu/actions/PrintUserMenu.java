package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.Input;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;
import ru.job4j.ood.isp.menu.Printer;

public class PrintUserMenu implements UserAction {
    private final Output output;

    public PrintUserMenu(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Вывести созданное меню";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        output.println("=== Вывод созданного меню ===");
        Printer printer = new Printer(output);
        printer.print(menu);
        return true;
    }
}
