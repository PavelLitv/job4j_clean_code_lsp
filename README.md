# job4j_clean_code_lsp

Задание

1. Реализуйте поиск в методе findItem() на основе DFS итератора.
2. На основе метода findItem() реализуйте методы add() и select().
3. Также на основе существующего итератора реализуйте метод iterator()
4. Создайте класс-реализацию MenuPrinter. Он должен печатать меню в консоль, но только с отступами, как в примере в самом начале задания.
5. Допишите тесты на метод select() и на вывод.
6. Создайте простенький класс TodoApp. Этот класс будет представлять собой консольное приложение, которое позволяет:
   - Добавить элемент в корень меню;
   - Добавить элемент к родительскому элементу;
   - Вызвать действие, привязанное к пункту меню (действие можно сделать константой, например, ActionDelegate DEFAULT_ACTION = () -> System.out.println("Some action") и указывать при добавлении элемента в меню);
   - Вывести меню в консоль. 