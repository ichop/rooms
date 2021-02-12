package com.nerdysoft.rooms.business.services;

public class RoomNotValidExceptoin extends Exception{
    public RoomNotValidExceptoin(String errorMessage) {
        super(errorMessage);
    }
}
