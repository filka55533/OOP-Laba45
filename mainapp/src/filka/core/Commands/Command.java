package filka.core.Commands;

public abstract class Command {

    ApplicationController app;

    public Command(ApplicationController app){

        this.app = app;

    }

    public abstract boolean execute();


}