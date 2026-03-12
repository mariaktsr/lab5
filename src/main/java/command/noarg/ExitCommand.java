package command.noarg;

import command.NoArgCommand;
import request.Request;
import request.Response;

//Команда завершения программы

public class ExitCommand extends NoArgCommand {

    public ExitCommand() {
        super("exit", "завершить программу (без сохранения в файл)");
    }

    @Override
    protected Response doExecute(Request request) {
        return Response.success("");
    }
}