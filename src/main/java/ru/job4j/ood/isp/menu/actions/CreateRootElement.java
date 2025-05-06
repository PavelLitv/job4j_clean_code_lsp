package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.Input;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;

public class CreateRootElement implements UserAction {
    private final Output output;

    public CreateRootElement(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Добавить элемент в корень меню";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        output.println("=== Создание корневого меню ===");
        String name = input.askStr("Введите название меню: ");
        menu.add(null, name,  () -> output.println("Вы вызвали действие меню: " + name));
        return true;
    }
}
