package assignment2.screen;

import assignment2.model.Screen;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ScreenRepository {

    private Map<Long, Screen> screenById = new ConcurrentHashMap<>();

    public void save(Screen screen) {
        screenById.put(screen.getScreenId(), screen);
    }

    public Screen get(Long id) {
        return screenById.get(id);
    }
}
