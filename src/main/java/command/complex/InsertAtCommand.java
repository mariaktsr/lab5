package command.complex;

import command.ComplexCommand;
import handler.CollectionManager;
import handler.InputHelper;
import model.*;
import request.Request;
import request.Response;

import java.time.ZonedDateTime;

//Команда вставки элемента в заданную позицию

public class InsertAtCommand extends ComplexCommand {

    private final CollectionManager manager;

    public InsertAtCommand(CollectionManager manager) {
        super("insert_at", "добавить новый элемент в заданную позицию");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        try {
            if (args == null || args.length == 0) {
                return Response.error("Команда требует 1 аргумент (индекс)!\nИспользование: insert_at index");
            }

            String indexArg = args[0];

            int index;
            try {
                index = Integer.parseInt(indexArg);
            } catch (NumberFormatException e) {
                return Response.error("Индекс должен быть целым числом!");
            }

            if (index < 0 || index > manager.getSize()) {
                return Response.error("Индекс вне диапазона (0-" + manager.getSize() + ")");
            }

            System.out.println("\nВставка элемента в позицию " + index);

            String name = InputHelper.readString("Введите имя: ", false);

            System.out.println("Введите координаты:");
            double x = InputHelper.readDouble("  X (double): ");
            long y = InputHelper.readLong("  Y (> -228): ", -228L);
            Coordinates coordinates = new Coordinates(x, y);

            boolean realHero = InputHelper.readBoolean("Реальный герой? (true/false): ");

            boolean hasToothpick = InputHelper.readBoolean("Есть зубочистка? (true/false): ");

            long impactSpeed = InputHelper.readLong("impactSpeed (> -428): ", -428L);

            WeaponType weaponType = InputHelper.readEnum("weaponType", WeaponType.class);

            Mood mood = InputHelper.readEnum("mood", Mood.class);

            Car car = null;
            while (true) {
                System.out.print("Ввести данные автомобиля? (y/n): ");
                String carChoice = InputHelper.readString("", false).trim().toLowerCase();

                if (carChoice.equals("y") || carChoice.equals("yes")) {
                    String carName = InputHelper.readString("  Имя автомобиля: ", true);
                    if (!carName.isEmpty()) {
                        car = new Car(carName);
                    }
                    break;
                } else if (carChoice.equals("n") || carChoice.equals("no")) {
                    car = null;
                    break;
                } else {
                    System.out.println("   Введите y (да) или n (нет)");
                }
            }

            HumanBeing human = new HumanBeing(
                    manager.generateId(),
                    name,
                    coordinates,
                    ZonedDateTime.now(),
                    realHero,
                    hasToothpick,
                    impactSpeed,
                    weaponType,
                    mood,
                    car
            );

            manager.insertAt(index, human);
            return Response.success("Элемент вставлен в позицию " + index + " с ID: " + human.getId());

        } catch (Exception e) {
            return Response.error("Ошибка при вставке: " + e.getMessage());
        }
    }
}