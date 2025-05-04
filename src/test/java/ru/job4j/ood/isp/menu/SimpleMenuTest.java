package ru.job4j.ood.isp.menu;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class SimpleMenuTest {

    public static final ActionDelegate STUB_ACTION = System.out::println;

    @Test
    public void whenAddThenReturnSame() {
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        assertThat(new Menu.MenuItemInfo("Сходить в магазин",
                List.of("Купить продукты"), STUB_ACTION, "1."))
                .isEqualTo(menu.select("Сходить в магазин").get());
        assertThat(new Menu.MenuItemInfo(
                "Купить продукты",
                List.of("Купить хлеб", "Купить молоко"), STUB_ACTION, "1.1."))
                .isEqualTo(menu.select("Купить продукты").get());
        assertThat(new Menu.MenuItemInfo(
                "Покормить собаку", List.of(), STUB_ACTION, "2."))
                .isEqualTo(menu.select("Покормить собаку").get());
        menu.forEach(i -> System.out.println(i.getNumber() + i.getName()));
    }

    @Test
    public void whenAddThenPrintMenu() {
        StringBuilder expectedOut = new StringBuilder()
                .append("1.Сходить в магазин").append(System.lineSeparator())
                .append("1.1.Купить продукты").append(System.lineSeparator())
                .append("1.1.1.Купить хлеб").append(System.lineSeparator())
                .append("1.1.2.Купить молоко").append(System.lineSeparator())
                .append("2.Покормить собаку").append(System.lineSeparator());
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        StringBuilder buffer = new StringBuilder();
            Printer printer = new Printer(
                    object -> buffer.append(object).append(System.lineSeparator())
            );
            printer.print(menu);
        assertThat(buffer.toString()).isEqualTo(expectedOut.toString());
    }

    @Test
    public void whenAddThenSelectItem() {
        Menu menu = new SimpleMenu();
        List<String> expectedChildren = List.of(
                "Купить хлеб",
                "Купить молоко"
        );
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        Menu.MenuItemInfo itemMenuInfo = menu.select("Купить продукты").get();
        assertThat(itemMenuInfo.getName()).isEqualTo("Купить продукты");
        assertThat(itemMenuInfo.getNumber()).isEqualTo("1.1.");
        assertThat(itemMenuInfo.getActionDelegate()).isEqualTo(STUB_ACTION);
        assertThat(itemMenuInfo.getChildren()).isEqualTo(expectedChildren);
    }
}