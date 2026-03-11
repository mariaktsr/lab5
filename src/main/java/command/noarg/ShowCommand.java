package command.noarg;

import command.NoArgCommand;
import handler.CollectionManager;
import request.Request;
import request.Response;

//Команда вывода всех элементов коллекции

public class ShowCommand extends NoArgCommand {

    private final CollectionManager manager;

    public ShowCommand(CollectionManager manager) {
        super("show", "вывести все элементы коллекции в строковом представлении");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        String result = manager.showAll();

        if (result == null || result.trim().isEmpty()) {
            return Response.success("Коллекция пуста");
        }

        return Response.success(result);
    }
}