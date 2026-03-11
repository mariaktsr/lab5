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
    protected Response doExecute(Request request, String... args) {
        System.out.println("Завершение программы...");
        System.exit(0);
        return Response.success("Программа завершена");
    }
}