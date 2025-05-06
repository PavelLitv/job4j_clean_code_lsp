package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.Input;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;

public class CreateChildElement implements UserAction {
    private final Output output;

    public CreateChildElement(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Добавить элемент к родительскому элементу";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        output.println("=== Создание дочернего меню ===");
        String parentName = input.askStr("Введите название родительского меню: ");
        if (menu.select(parentName).isEmpty()) {
            output.println(parentName + " - такой элемент меню не найден");
        } else {
            String childName = input.askStr("Введите название дочернего меню: ");
            menu.add(parentName, childName, () -> output.println("Вы вызвали действие меню: " + childName));
        }
        return true;
    }
}
