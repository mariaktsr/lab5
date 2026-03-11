package command.registry;

import command.Command;
import command.noarg.*;
import command.onearg.*;
import command.complex.*;
import handler.CollectionManager;
import handler.FileManager;
import handler.ScriptExecutor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Реестр команд (хранит все доступные команды и предоставляет доступ к ним)

public class CommandRegistry {

    private final Map<String, Command> commands;

    public CommandRegistry(CollectionManager manager, FileManager fileManager, ScriptExecutor scriptExecutor) {
        commands = new HashMap<>();

        //NoArg команды
        register(new HelpCommand(this));
        register(new InfoCommand(manager));
        register(new ShowCommand(manager));
        register(new ClearCommand(manager));
        register(new SortCommand(manager));
        register(new SumOfImpactSpeedCommand(manager));
        register(new PrintFieldDescendingMoodCommand(manager));
        register(new SaveCommand(manager, fileManager));
        register(new ExitCommand());

        //OneArg команды
        register(new RemoveByIdCommand(manager));
        register(new RemoveAtCommand(manager));
        register(new FilterContainsNameCommand(manager));
        register(new ExecuteScriptCommand(scriptExecutor));

        //Complex команды
        register(new AddCommand(manager));
        register(new InsertAtCommand(manager));
        register(new UpdateCommand(manager));
    }

    private void register(Command command) {
        commands.put(command.getName().toLowerCase(), command);
    }

    public Command getCommand(String name) {
        return commands.get(name.toLowerCase());
    }

    public Set<String> getCommandNames() {
        return commands.keySet();
    }
}