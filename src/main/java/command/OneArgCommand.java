package command;

import request.Request;
import request.Response;

//Абстрактный класс для команд с ОДНИМ аргументом
//remove_by_id id, filter_contains_name name, ...

public abstract class OneArgCommand extends AbstractCommand {

    public OneArgCommand(String name, String description) {
        super(name, description);
    }

    @Override
    protected final Response run(Request request, String... args) {
        if (args == null || args.length != 1) {
            return Response.error(
                    "Команда '" + name + "' требует 1 аргумент!\n" +
                            "Использование: " + name + " {аргумент}"
            );
        }
        if (args[0] == null || args[0].trim().isEmpty()) {
            return Response.error(
                    "Аргумент не может быть пустым!\n" +
                            "Использование: " + name + " {аргумент}"
            );
        }
        return doExecute(request, args);
    }

    protected abstract Response doExecute(Request request, String... args);
}