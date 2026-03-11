package command.onearg;

import command.OneArgCommand;
import handler.CollectionManager;
import request.Request;
import request.Response;

//Команда удаления элемента по ID

public class RemoveByIdCommand extends OneArgCommand {

    private final CollectionManager manager;

    public RemoveByIdCommand(CollectionManager manager) {
        super("remove_by_id", "удалить элемент из коллекции по его id");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        if (!isLong(args[0])) {
            return Response.error("ID должен быть числом!");
        }

        Long id = Long.parseLong(args[0]);
        boolean removed = manager.removeById(id);

        if (removed) {
            return Response.success("Элемент с ID " + id + " удалён");
        } else {
            return Response.error("Элемент с ID " + id + " не найден");
        }
    }
}
