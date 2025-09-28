package kg.attractor.java.task1;

import java.util.Comparator;

public class Main {

    public static void main(String[] args) {

        var cats = Cat.makeCats(10);
        Printer.print(cats);

        // А сюда добавьте код, который будет сортировать коллекцию котов
        // используйте лямбда-выражения и ссылки на методы
        cats.sort(Comparator.comparing(cat -> cat.getBreed().name()));
        Printer.print(cats);

        cats.sort(Comparator.comparing(Cat::getName).thenComparing(Cat::getAge));
        Printer.print(cats);

        cats.removeIf(cat -> cat.getColor() == Cat.Color.TABBY);
        Printer.print(cats);
    }

}
