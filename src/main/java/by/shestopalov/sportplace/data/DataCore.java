package by.shestopalov.sportplace.data;

import by.shestopalov.sportplace.entity.*;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DataCore {
    public static Set<User> users = new HashSet<>();
    public static Set<Role> roles = new HashSet<>();
    public static Set<Place> places = new HashSet<>();
    public static Set<Event> events = new HashSet<>();
    public static Set<Comment> comments = new HashSet<>();

    static {
        Place pl1=new Place(1L, "Minsk-arena", "Belarus", "Minsk", "Sverdlova", 13, new HashSet<>());
        Place pl2=new Place(2L, "Chizhovka-arena", "Belarus", "Minsk", "Uborevich", 13, new HashSet<>());
        Place pl3 = new Place(3L, "Dinamo", "Belarus", "Minsk", "Kirova", 13, new HashSet<>());
        places.add(pl1);
        places.add(pl2);
        places.add(pl3);

        Event ev1 = new Event(1L, "Football", new Date(), new Date(), pl1, new HashSet<>());
        Event ev2 = new Event(2L, "Basketball", new Date(), new Date(), pl2, new HashSet<>());
        Event ev3 = new Event(3L, "Volleyball", new Date(), new Date(), pl3, new HashSet<>());

        events.add(ev1);
        events.add(ev2);
        events.add(ev3);
    }

}
