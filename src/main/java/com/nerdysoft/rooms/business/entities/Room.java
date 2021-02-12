package com.nerdysoft.rooms.business.entities;


import java.util.List;

public class Room {
    private List<Point> room;

    public Room() {
    }

    public List<Point> getRoom() {
        return room;
    }

    public void setRoom(List<Point> room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Room{" +
                "points=" + room +
                '}';
    }
}
