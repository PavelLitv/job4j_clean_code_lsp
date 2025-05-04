package ru.job4j.ood.isp.menu;

public class Printer implements MenuPrinter {
    private final Output output;

    public Printer(Output output) {
        this.output = output;
    }

    @Override
    public void print(Menu menu) {
        menu.forEach(i -> output.println(i.getNumber() + i.getName()));
    }
}
