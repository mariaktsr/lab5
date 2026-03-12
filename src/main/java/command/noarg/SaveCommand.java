package command.noarg;

import command.NoArgCommand;
import handler.CollectionManager;
import handler.FileManager;
import request.Request;
import request.Response;

//Команда сохранения коллекции в файл

public class SaveCommand extends NoArgCommand {

    private final CollectionManager manager;
    private final FileManager fileManager;

    public SaveCommand(CollectionManager manager, FileManager fileManager) {
        super("save", "сохранить коллекцию в файл");
        this.manager = manager;
        this.fileManager = fileManager;
    }

    @Override
    protected Response doExecute(Request request) {
        try {
            fileManager.save(manager.getCollection());
            return Response.success("Коллекция сохранена в файл");
        } catch (Exception e) {
            return Response.error("Ошибка при сохранении: " + e.getMessage());
        }
    }
}