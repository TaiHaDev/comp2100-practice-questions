public class StandState implements State {
    private static State state;

    @Override
    public void handleInput(Character character, Key event) {
        if (event == Key.UP) {
            character.setState(SuspendState.getInstance());
        } else if (event == Key.RIGHT) {
            character.setState(WalkState.getInstance());
        } else if (event == Key.DOWN) {
            character.setState(SquatState.getInstance());
        }
    }

    private StandState() {
    }
    public static State getInstance() {
        if (state == null) state = new StandState();
        return state;
    }

}
