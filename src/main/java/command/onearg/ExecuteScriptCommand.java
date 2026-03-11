package command.onearg;

import command.OneArgCommand;
import handler.ScriptExecutor;
import request.Request;
import request.Response;

//Команда выполнения скрипта из файла

public class ExecuteScriptCommand extends OneArgCommand {

    private final ScriptExecutor executor;

    public ExecuteScriptCommand(ScriptExecutor executor) {
        super("execute_script", "считать и исполнить скрипт из указанного файла");
        this.executor = executor;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        String fileName = args[0].trim();

        if (fileName.isEmpty()) {
            return Response.error("Имя файла не может быть пустым!");
        }

        try {
            executor.executeScript(fileName);
            return Response.success("Скрипт выполнен: " + fileName);
        } catch (Exception e) {
            return Response.error("Ошибка при выполнении скрипта: " + e.getMessage());
        }
    }
}