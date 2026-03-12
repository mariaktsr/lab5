import handler.*;
import command.invoker.CommandInvoker;
import command.registry.CommandRegistry;
import model.HumanBeing;

public class Main {

    private static final String FILE_ENV_VAR = "HUMAN_BEING_FILE";

    public static void main(String[] args) {

        String fileName = System.getenv(FILE_ENV_VAR);
        if (fileName == null || fileName.isEmpty()) {
            System.err.println("Ошибка: переменная окружения '" + FILE_ENV_VAR + "' не установлена.");
            System.exit(1);
        }

        FileManager fileManager = new FileManager(fileName);
        CollectionManager collectionManager = new CollectionManager();

        collectionManager.getCollection().clear();
        for (HumanBeing human : fileManager.load()) {
            collectionManager.add(human);
        }

        ScriptExecutor scriptExecutor = new ScriptExecutor(collectionManager, fileManager);

        CommandRegistry registry = new CommandRegistry(collectionManager, fileManager, scriptExecutor);

        CommandInvoker invoker = new CommandInvoker(registry);

        scriptExecutor.setInvoker(invoker);

        CliHandler cli = new CliHandler(invoker, collectionManager);
        cli.start();
    }
}