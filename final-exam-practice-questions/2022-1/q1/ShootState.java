public class ShootState implements State {
    private static ShootState instance;
    private ShootState() {};
    public static ShootState getInstance() {
        if (instance == null) instance = new ShootState();
        return instance;
    }

    @Override
    public void handleInput(Character character, Key event) {
        if (event == Key.L) character.setState(LieProneState.getInstance());
        if (event == Key.RESET) character.setState(StandState.getInstance());
    }
}
