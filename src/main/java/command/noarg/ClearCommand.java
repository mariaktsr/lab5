package command.noarg;

import command.NoArgCommand;
import handler.CollectionManager;
import request.Request;
import request.Response;

//Команда очистки коллекции

public class ClearCommand extends NoArgCommand {

    private final CollectionManager manager;

    public ClearCommand(CollectionManager manager) {
        super("clear", "очистить коллекцию");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        manager.clear();
        return Response.success("Коллекция очищена");
    }
}