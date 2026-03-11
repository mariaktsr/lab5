package command.noarg;

import command.NoArgCommand;
import handler.CollectionManager;
import request.Request;
import request.Response;

//Команда вывода значений поля mood всех элементов в порядке убывания

public class PrintFieldDescendingMoodCommand extends NoArgCommand {

    private final CollectionManager manager;

    public PrintFieldDescendingMoodCommand(CollectionManager manager) {
        super("print_field_descending_mood", "вывести значения поля mood всех элементов в порядке убывания");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        String result = manager.printMoodDescending();

        if (result == null || result.trim().isEmpty()) {
            return Response.success("Коллекция пуста");
        }

        return Response.success(result);
    }
}