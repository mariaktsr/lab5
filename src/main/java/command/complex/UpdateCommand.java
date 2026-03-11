package command.complex;

import command.ComplexCommand;
import handler.CollectionManager;
import handler.InputHelper;
import model.*;
import request.Request;
import request.Response;

//Команда обновления элемента по ID

public class UpdateCommand extends ComplexCommand {

    private final CollectionManager manager;

    public UpdateCommand(CollectionManager manager) {
        super("update", "обновить значение элемента коллекции, id которого равен заданному");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        try {
            if (args == null || args.length == 0) {
                return Response.error("Команда update требует ID элемента!");
            }

            long id = Long.parseLong(args[0]);
            HumanBeing existing = manager.findById(id);

            if (existing == null) {
                return Response.error("Элемент с ID=" + id + " не найден!");
            }

            System.out.println("\nОбновление элемента ID=" + id);
            System.out.println("Текущие данные: " + existing.getName());
            System.out.println("Оставьте поле пустым, чтобы не изменять\n");

            String name = InputHelper.readString("Имя [" + existing.getName() + "]: ", true);
            if (name.isEmpty()) name = existing.getName();

            System.out.println("Координаты (текущие: " + existing.getCoordinates() + "):");
            double x = InputHelper.readDouble("  X [" + existing.getCoordinates().getX() + "]: ");
            long y = InputHelper.readLong("  Y [" + existing.getCoordinates().getY() + "] (> -228): ", -228L);
            Coordinates coordinates = new Coordinates(x, y);

            Boolean realHero = InputHelper.readBoolean("Реальный герой [" + existing.getRealHero() + "]: ");

            boolean hasToothpick = InputHelper.readBoolean("Есть зубочистка [" + existing.isHasToothpick() + "]: ");

            Long impactSpeed = InputHelper.readLong("impactSpeed [" + existing.getImpactSpeed() + "] (> -428): ", -428L);

            WeaponType weaponType = InputHelper.readEnum("weaponType [" + existing.getWeaponType() + "]", WeaponType.class);

            Mood mood = InputHelper.readEnum("mood [" + existing.getMood() + "]", Mood.class);

            Car car = existing.getCar();
            while (true) {
                System.out.print("Изменить данные автомобиля? (y/n): ");
                String carChoice = InputHelper.readString("", true).trim().toLowerCase();

                if (carChoice.equals("y") || carChoice.equals("yes")) {
                    String carName = InputHelper.readString("  Имя автомобиля: ", true);
                    if (!carName.isEmpty()) {
                        car = new Car(carName);
                    }
                    break;
                } else if (carChoice.equals("n") || carChoice.equals("no")) {
                    break;
                } else {
                    System.out.println("  Введите y (да), n (нет)");
                }
            }

            HumanBeing updated = new HumanBeing(
                    existing.getId(),
                    name,
                    coordinates,
                    existing.getCreationDate(),
                    realHero,
                    hasToothpick,
                    impactSpeed,
                    weaponType,
                    mood,
                    car
            );

            boolean success = manager.update(id, updated);
            if (success) {
                return Response.success("Элемент с ID=" + id + " обновлён");
            } else {
                return Response.error("Не удалось обновить элемент");
            }

        } catch (NumberFormatException e) {
            return Response.error("Ошибка формата числа: " + e.getMessage());
        } catch (Exception e) {
            return Response.error("Ошибка при обновлении: " + e.getMessage());
        }
    }
}