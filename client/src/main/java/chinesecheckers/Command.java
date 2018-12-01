package chinesecheckers;

import com.google.gson.Gson;

public class Command {

    CommandType commandType;
    String content;

    public String toJSON(){

        String[] classContent = new String[2];
        classContent[0] = String.valueOf(commandType.ordinal());
        classContent[1] = content;

        Gson gson = new Gson();

        return gson.toJson(classContent);
    }

    public static Command fromJSON(String JSON){


        Gson gson = new Gson();

        String[] classContent = gson.fromJson(JSON, String[].class);

        Command command = new Command();
        command.commandType = CommandType.values()[Integer.parseInt(classContent[0])];
        command.content = classContent[1];

        return command;
    }
}
