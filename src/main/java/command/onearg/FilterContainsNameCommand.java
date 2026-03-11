package command.onearg;

import command.OneArgCommand;
import handler.CollectionManager;
import request.Request;
import request.Response;

//Команда фильтрации элементов по подстроке в имени

public class FilterContainsNameCommand extends OneArgCommand {

    private final CollectionManager manager;

    public FilterContainsNameCommand(CollectionManager manager) {
        super("filter_contains_name", "вывести элементы, значение поля name которых содержит заданную подстроку");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request, String... args) {
        String result = manager.filterContainsName(args[0]);

        if (result == null || result.trim().isEmpty()) {
            return Response.success("Элементы с именем, содержащим '" + args[0] + "', не найдены");
        }

        return Response.success(result);
    }
}