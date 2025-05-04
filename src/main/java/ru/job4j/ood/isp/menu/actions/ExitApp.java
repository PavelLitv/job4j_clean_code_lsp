package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.Input;
import ru.job4j.ood.isp.menu.Menu;
import ru.job4j.ood.isp.menu.Output;

public class ExitApp implements UserAction {
    private final Output output;

    public ExitApp(Output output) {
        this.output = output;
    }

    @Override
    public String name() {
        return "Выход";
    }

    @Override
    public boolean execute(Input input, Menu menu) {
        output.println("=== Выход из приложения ===");
        return false;
    }
}
