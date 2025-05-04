package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.Input;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;

import java.util.Optional;

public class ExecuteDelegate implements UserAction {
    private final Output output;

    public ExecuteDelegate(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Вызвать действие";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        output.println("=== Вызов действия меню ===");
        String name = input.askStr("Введите название меню: ");
        Optional<Menu.MenuItemInfo> menuItem = menu.select(name);
        if (menu.select(name).isEmpty()) {
            output.println(name + " - такой элемент меню не найден");
        } else {
            menuItem.get().getActionDelegate().delegate();
        }
        return true;
    }
}
