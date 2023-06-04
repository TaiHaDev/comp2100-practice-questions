public class StandState implements State {
    private static StandState instance;
    private StandState() {};
    public static StandState getInstance() {
        if (instance == null) instance = new StandState();
        return instance;
    }

    @Override
    public void handleInput(Character character, Key event) {
        if (event == Key.DOWN) character.setState(LieProneState.getInstance());
        if (event == Key.RIGHT) character.setState(RunState.getInstance());
    }
}
