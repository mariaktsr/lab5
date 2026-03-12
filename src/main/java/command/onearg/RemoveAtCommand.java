package command.onearg;

import command.OneArgCommand;
import handler.CollectionManager;
import request.Request;
import request.Response;

//Команда удаления элемента по индексу

public class RemoveAtCommand extends OneArgCommand {

    private final CollectionManager manager;

    public RemoveAtCommand(CollectionManager manager) {
        super("remove_at", "удалить элемент, находящийся в заданной позиции коллекции (index)");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request) {
        String substring = request.getArguments()[0];
        if (!isLong(substring)) {
            return Response.error("Индекс должен быть целым числом!");
        }

        int index = Integer.parseInt(substring);

        try {
            manager.removeAt(index);
            return Response.success("Элемент в позиции " + index + " удалён");
        } catch (IndexOutOfBoundsException e) {
            return Response.error("Индекс вне диапазона (0-" + (manager.getSize() - 1) + ")");
        }
    }
}