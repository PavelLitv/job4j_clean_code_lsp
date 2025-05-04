package ru.job4j.ood.isp.menu.actions;

import ru.job4j.ood.isp.menu.Input;
import ru.job4j.ood.isp.menu.Menu;

public interface UserAction {
    String name();

    boolean execute(Input input, Menu menu);
}
