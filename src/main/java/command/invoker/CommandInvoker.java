package command.invoker;

import command.Command;
import command.registry.CommandRegistry;
import request.Request;
import request.Response;

//Инвокер команд (отвечает за выполнение команд)

public class CommandInvoker {

    private final CommandRegistry registry;

    public CommandInvoker(CommandRegistry registry) {
        this.registry = registry;
    }

    public Response execute(Request request) {
        String commandName = request.getCommandName();

        Command command = registry.getCommand(commandName);

        if (command == null) {
            return Response.error(
                    "Неизвестная команда: '" + commandName + "'\n" +
                            "Введите 'help' для списка доступных команд"
            );
        }

        try {
            return command.execute(request);
        } catch (Exception e) {
            return Response.error("Внутренняя ошибка при выполнении команды: " + e.getMessage());
        }
    }
}