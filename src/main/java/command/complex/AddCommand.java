package command.complex;

import command.ComplexCommand;
import handler.CollectionManager;
import model.HumanBeing;
import request.Request;
import request.Response;

//Команда добавления нового элемента в коллекцию

public class AddCommand extends ComplexCommand {

    private final CollectionManager manager;

    public AddCommand(CollectionManager manager) {
        super("add", "добавить новый элемент в коллекцию");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request) {
        HumanBeing human = (HumanBeing) request.getData(HumanBeing.class);

        if (human == null) {
            return Response.error("Внутренняя ошибка: объект HumanBeing не передан");
        }

        manager.add(human);
        return Response.success("Элемент успешно добавлен с ID: " + human.getId());
    }
}