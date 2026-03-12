package command;

//Базовый абстрактный класс для всех команд

import request.Request;
import request.Response;

public abstract class AbstractCommand implements Command {

    protected String name;
    protected String description;

    public AbstractCommand(String name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getDescription() {
        return description;
    }
    @Override
    public final Response execute(Request request) {
        try {
            return run(request);
        } catch (Exception e) {
            return Response.error("Ошибка при выполнении команды: " + e.getMessage());
        }
    }

    protected abstract Response run(Request request);

    protected boolean isLong(String arg) {
        try {
            Long.parseLong(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}