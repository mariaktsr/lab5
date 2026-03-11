package command.noarg;

import command.NoArgCommand;
import handler.CollectionManager;
import request.Request;
import request.Response;

//Команда вывода информации о коллекции

public class InfoCommand extends NoArgCommand {

    private final CollectionManager manager;

    public InfoCommand(CollectionManager manager) {
        super("info", "вывести информацию о коллекции (тип, дата инициализации, количество элементов)");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        String info = manager.getInfo();
        return Response.success(info);
    }
}
