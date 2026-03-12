package command;

import request.Request;
import request.Response;

//Абстрактный класс для команд БЕЗ аргументов
//help, info, show, clear, sort, ...

public abstract class NoArgCommand extends AbstractCommand {

    public NoArgCommand(String name, String description) {
        super(name, description);
    }

    @Override
    protected final Response run(Request request) {
        if (request.getArguments() != null && request.getArguments().length > 0) {
            return Response.error(
                    "Команда '" + name + "' не принимает аргументы!\n" +
                            "Использование: " + name
            );
        }
        return doExecute(request);
    }

    protected abstract Response doExecute(Request request);
}