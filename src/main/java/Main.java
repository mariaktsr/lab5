import command.registry.CommandRegistry;
import command.invoker.CommandInvoker;
import handler.CollectionManager;
import handler.FileManager;
import handler.ScriptExecutor;
import model.HumanBeing;
import request.Request;
import request.Response;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("HumanBeing Collection Manager\n");

        Scanner scanner = null;

        try {
            String fileName = "data.csv";
            System.out.println("Используется: " + fileName);

            FileManager fileManager = new FileManager(fileName);
            CollectionManager collectionManager = new CollectionManager();

            System.out.println("Загрузка данных из файла: " + fileName);
            for (HumanBeing human : fileManager.load()) {
                collectionManager.add(human);
            }
            System.out.println("Загружено элементов: " + collectionManager.getSize() + "\n");

            ScriptExecutor scriptExecutor = new ScriptExecutor(collectionManager, fileManager);
            CommandRegistry registry = new CommandRegistry(collectionManager, fileManager, scriptExecutor);
            CommandInvoker invoker = new CommandInvoker(registry);
            scriptExecutor.setInvoker(invoker);

            scanner = new Scanner(System.in);
            System.out.println("Приложение запущено. Введите 'help' для справки.");

            while (true) {
                System.out.print("\n> ");

                if (!scanner.hasNextLine()) {
                    System.out.println("\nВходной поток закрыт. Завершение работы...");
                    break;
                }

                String input = scanner.nextLine().trim();

                if (input.isEmpty()) {
                    continue;
                }

                String[] tokens = input.split("\\s+", 2);
                String commandName = tokens[0].toLowerCase();
                String[] commandArgs;
                if (tokens.length > 1) {
                    commandArgs = tokens[1].split(" ");
                } else {
                    commandArgs = new String[0];
                }

                Request request = new Request(commandName, commandArgs);
                Response response = invoker.execute(request);

                if (response != null && response.getMessage() != null) {
                    if (response.isSuccess()) {
                        System.out.println(response.getMessage());
                    } else {
                        System.err.println("Ошибка: " + response.getMessage());
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Критическая ошибка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}