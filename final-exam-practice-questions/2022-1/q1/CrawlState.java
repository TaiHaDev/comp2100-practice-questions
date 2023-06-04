public class CrawlState implements State{
    private static CrawlState instance;
    private CrawlState() {};
    public static CrawlState getInstance() {
        if (instance == null) instance = new CrawlState();
        return instance;
    }

    @Override
    public void handleInput(Character character, Key event) {
        if (event == Key.LEFT) character.setState(LieProneState.getInstance());
        if (event == Key.RESET) character.setState(StandState.getInstance());
    }
}
