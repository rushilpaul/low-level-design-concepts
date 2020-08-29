package assignment2.screen;

import assignment2.model.Screen;

public class ScreenService {

    private ScreenRepository screenRepository;

    public ScreenService(ScreenRepository screenRepository) {
        this.screenRepository = screenRepository;
    }

    public boolean lockSeat(Long screenId, Long seatId) {

        Screen screen = screenRepository.get(screenId);
        if(screen == null)
           throw new IllegalArgumentException("Screen does not exist");

        if(screen.getSeat(seatId) == null)  // Check if seat with this ID exists
            return false;

        return screen.getSeat(seatId).compareAndSet(false, true);
    }

    public boolean unlockSeat(Long screenId, Long seatId) {

        Screen screen = screenRepository.get(screenId);

        if(screen.getSeat(seatId) == null)
            return false;

        return screen.getSeat(seatId).compareAndSet(true, false);
    }

    public Boolean addLayout(Boolean layout[][], Long screenId) {

        Screen screen = screenRepository.get(screenId);
        if(screen == null)
            screen = new Screen(screenId, layout);

        screenRepository.save(screen);
        return true;
    }
}
