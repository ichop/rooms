package com.nerdysoft.rooms.controller;


import com.nerdysoft.rooms.business.entities.Error;
import com.nerdysoft.rooms.business.entities.Room;
import com.nerdysoft.rooms.business.services.RoomNotValidExceptoin;
import com.nerdysoft.rooms.business.services.RoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }


    @PostMapping(value = "/validateRoom")
    public ResponseEntity validateRoom(@RequestBody Room room) {
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(roomService.add(room));
        } catch (RoomNotValidExceptoin e) {
            Error error = new Error();
            error.setError(e.getMessage());
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(error);
        }
    }

    @GetMapping(value = "/validateRoom")
    public ResponseEntity getAllRooms() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roomService.findAll());
    }

    @DeleteMapping(value = "/validateRoom")
    public ResponseEntity deleteByIndex(@RequestParam int index) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roomService.delete(index));

    }


}
