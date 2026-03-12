package command.noarg;

import command.NoArgCommand;
import handler.CollectionManager;
import request.Request;
import request.Response;

//Команда вывода суммы значений поля impactSpeed

public class SumOfImpactSpeedCommand extends NoArgCommand {

    private final CollectionManager manager;

    public SumOfImpactSpeedCommand(CollectionManager manager) {
        super("sum_of_impact_speed", "вывести сумму значений поля impactSpeed для всех элементов коллекции");
        this.manager = manager;
    }

    @Override
    protected Response doExecute(Request request) {
        long sum = manager.sumOfImpactSpeed();
        return Response.success("Сумма значений impactSpeed: " + sum);
    }
}