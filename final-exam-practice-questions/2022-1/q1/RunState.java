public class RunState implements State{
    private static RunState instance;
    private RunState() {};
    public static RunState getInstance() {
        if (instance == null) instance = new RunState();
        return instance;
    }

    @Override
    public void handleInput(Character character, Key event) {
        if (event == Key.LEFT || event == Key.RESET) character.setState(StandState.getInstance());
    }
}
