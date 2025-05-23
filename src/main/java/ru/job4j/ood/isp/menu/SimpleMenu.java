package ru.job4j.ood.isp.menu;

import java.util.*;

public class SimpleMenu implements Menu {
    private final List<MenuItem> rootElements = new ArrayList<>();

    @Override
    public boolean add(String parentName, String childName, ActionDelegate actionDelegate) {
        SimpleMenuItem childItem = new SimpleMenuItem(childName, actionDelegate);
        findItem(parentName).ifPresentOrElse(
                item -> item.menuItem.getChildren().add(childItem),
                () -> rootElements.add(childItem)
        );
        return true;
    }

    @Override
    public Optional<MenuItemInfo> select(String itemName) {
        return findItem(itemName).map(item -> new MenuItemInfo(item.menuItem, item.number));
    }

    @Override
    public Iterator<MenuItemInfo> iterator() {
        DFSIterator iterator = new DFSIterator();

        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MenuItemInfo next() {
                ItemInfo itemInfo = iterator.next();
                return new MenuItemInfo(itemInfo.menuItem, itemInfo.number);
            }
        };
    }

    private Optional<ItemInfo> findItem(String name) {
        Optional<ItemInfo> item = Optional.empty();
        DFSIterator iterator = new DFSIterator();
        while (iterator.hasNext()) {
            ItemInfo itemInfo = iterator.next();
            if (Objects.equals(name, itemInfo.menuItem.getName())) {
                item = Optional.of(itemInfo);
                break;
            }
        }
        return item;
    }

    private static class SimpleMenuItem implements MenuItem {
        private final String name;

        private final List<MenuItem> children = new ArrayList<>();

        private final ActionDelegate actionDelegate;

        public SimpleMenuItem(String name, ActionDelegate actionDelegate) {
            this.name = name;
            this.actionDelegate = actionDelegate;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<MenuItem> getChildren() {
            return children;
        }

        @Override
        public ActionDelegate getActionDelegate() {
            return actionDelegate;
        }
    }

    private class DFSIterator implements Iterator<ItemInfo> {
        private final Deque<MenuItem> stack = new LinkedList<>();

        private final Deque<String> numbers = new LinkedList<>();

        DFSIterator() {
            int number = 1;
            for (MenuItem item : rootElements) {
                stack.addLast(item);
                numbers.addLast(String.valueOf(number++).concat("."));
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public ItemInfo next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            MenuItem current = stack.removeFirst();
            String lastNumber = numbers.removeFirst();
            List<MenuItem> children = current.getChildren();
            int currentNumber = children.size();
            for (var i = children.listIterator(children.size()); i.hasPrevious();) {
                stack.addFirst(i.previous());
                numbers.addFirst(lastNumber.concat(String.valueOf(currentNumber--)).concat("."));
            }
            return new ItemInfo(current, lastNumber);
        }
    }

    private class ItemInfo {
        private final MenuItem menuItem;

        private final String number;

        public ItemInfo(MenuItem menuItem, String number) {
            this.menuItem = menuItem;
            this.number = number;
        }
    }
}
