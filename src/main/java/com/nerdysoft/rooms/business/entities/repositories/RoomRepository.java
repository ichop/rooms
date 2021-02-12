package com.nerdysoft.rooms.business.entities.repositories;



import com.nerdysoft.rooms.business.entities.Point;
import com.nerdysoft.rooms.business.entities.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoomRepository {
    private final List<Room> rooms = new ArrayList<Room>();

    public RoomRepository() {
        super();

        List<Point> points1 =  Arrays.asList(new Point(1,0), new Point(1,1),
        new Point(0, 1), new Point(0, 2),new Point(2, 2),new Point(2, 0)
        );

        List<Point> points2 =  Arrays.asList(new Point(1,1), new Point(1,2),
                new Point(2, 2), new Point(2, 1)
        );

        Room room1 = new Room();
        room1.setRoom(points1);

        Room room2 = new Room();
        room2.setRoom(points2);

        this.rooms.add(room1);
        this.rooms.add(room2);
    }

    public List<Room> findAll() {
        return new ArrayList<Room>(this.rooms);
    }

    public void add(final Room room) {
        this.rooms.add(room);
    }
    public Room delete(int index){
        return this.rooms.remove(index);
    }
}
