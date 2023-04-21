public class SuspendState implements State {
    private static State instance;
    private SuspendState(){}
    public static State getInstance() {
        if (instance == null) instance = new SuspendState();
        return instance;
    }
    @Override
    public void handleInput(Character character, Key event) {
        if (event == Key.DOWN) {
            character.setState(StandState.getInstance());
        }
    }
}
