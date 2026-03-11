package request;

//Запрос от пользователя

public class Request {
    private final String commandName;
    private final String[] arguments;

    public Request(String commandName, String[] arguments) {
        this.commandName = commandName;
        if (arguments != null) {
            this.arguments = arguments;
        } else {
            this.arguments = new String[0];
        }
    }

    public String getCommandName() {
        return commandName;
    }
    public String[] getArguments() {
        return arguments;
    }

    @Override
    public String toString() {
        return "Request{command='" + commandName + "', args=" + arguments.length + "}";
    }
}