package command.noarg;

import command.NoArgCommand;
import command.Command;
import command.registry.CommandRegistry;
import request.Request;
import request.Response;

import java.util.Set;

//Команда вывода справки по доступным командам

public class HelpCommand extends NoArgCommand {

    private final CommandRegistry registry;

    public HelpCommand(CommandRegistry registry) {
        super("help", "вывести справку по доступным командам");
        this.registry = registry;
    }

    @Override
    protected Response doExecute(Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append("Доступные команды:\n");
        sb.append("--------------------------------------------------------\n");

        Set<String> commandNames = registry.getCommandNames();

        for (String name : commandNames) {
            Command cmd = registry.getCommand(name);
            if (cmd != null) {
                sb.append("  ").append(name);
                for (int i = name.length(); i < 25; i++) {
                    sb.append(" ");
                }
                sb.append(" : ").append(cmd.getDescription()).append("\n");
            }
        }

        sb.append("--------------------------------------------------------\n");
        sb.append("Введите имя команды для выполнения.");

        return Response.success(sb.toString());
    }
}