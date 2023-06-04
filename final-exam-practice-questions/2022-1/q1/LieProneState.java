public class LieProneState implements State{
    private static LieProneState instance;
    private LieProneState() {};
    public static LieProneState getInstance() {
        if (instance == null) instance = new LieProneState();
        return instance;
    }

    @Override
    public void handleInput(Character character, Key event) {
        if (event == Key.UP || event == Key.RESET) character.setState(StandState.getInstance());
        if (event == Key.S) character.setState(ShootState.getInstance());
        if (event == Key.RIGHT) character.setState(CrawlState.getInstance());
    }
}
