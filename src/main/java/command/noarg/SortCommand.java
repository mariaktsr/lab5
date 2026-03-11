package command.noarg;

import command.NoArgCommand;
import handler.CollectionManager;
import request.Request;
import request.Response;

//Команда сортировки коллекции

public class SortCommand extends NoArgCommand {

    private final CollectionManager manager;

    public SortCommand(CollectionManager manager) {
        super("sort", "отсортировать коллекцию в естественном порядке");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        manager.sort();
        return Response.success("Коллекция отсортирована");
    }
}