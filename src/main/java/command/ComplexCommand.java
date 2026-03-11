package command;

import request.Request;
import request.Response;

//Абстрактный класс для сложных команд
//add {element}, insert_at index {element}, update id {element}

public abstract class ComplexCommand extends AbstractCommand {

    public ComplexCommand(String name, String description) {
        super(name, description);
    }

    @Override
    protected final Response run(Request request, String... args) {
        try {
            return doExecute(request, args);
        } catch (Exception e) {
            return Response.error("Ошибка при выполнении команды: " + e.getMessage());
        }
    }

    protected abstract Response doExecute(Request request, String... args);
}