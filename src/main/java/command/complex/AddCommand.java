package command.complex;

import command.ComplexCommand;
import handler.CollectionManager;
import handler.InputHelper;
import model.*;
import request.Request;
import request.Response;

import java.time.ZonedDateTime;

//Команда добавления нового элемента в коллекцию

public class AddCommand extends ComplexCommand {

    private final CollectionManager manager;

    public AddCommand(CollectionManager manager) {
        super("add", "добавить новый элемент в коллекцию");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        try {
            System.out.println("\nДобавление нового элемента");

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

            manager.add(human);
            return Response.success("Элемент успешно добавлен с ID: " + human.getId());

        } catch (Exception e) {
            return Response.error("Ошибка при добавлении: " + e.getMessage());
        }
    }
}