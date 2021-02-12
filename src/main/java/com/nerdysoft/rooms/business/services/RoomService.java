package com.nerdysoft.rooms.business.services;



import com.nerdysoft.rooms.business.entities.Point;
import com.nerdysoft.rooms.business.entities.Room;
import com.nerdysoft.rooms.business.entities.repositories.RoomRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return this.roomRepository.findAll();
    }

    public Room add(final Room room) throws RoomNotValidExceptoin {
        if (checkIfValid(room)) {
            this.roomRepository.add(room);
        }
        return room;
    }

    public Room delete(Integer index) {
        return this.roomRepository.delete(index);
    }

    private Boolean checkIfValid(Room room) throws RoomNotValidExceptoin {
        List<Point> points = room.getRoom();
        int cornersAmount = points.size();

        if (cornersAmount < 4) {
            throw new RoomNotValidExceptoin("Only " + cornersAmount + " points is present. Not able build a layout.");
        }

        for (int i = 0; i < cornersAmount; i++) {
            if (i + 1 < cornersAmount) {
                if (points.get(i).getX() != points.get(i + 1).getX() &&
                        points.get(i).getY() != points.get(i + 1).getY()) {
                    throw new RoomNotValidExceptoin("Diagonal wall is present");
                }
            }
        }
        if (points.get(cornersAmount - 1).getX() != points.get(0).getX() &&
                points.get(cornersAmount - 1).getY() != points.get(0).getY()) {
            throw new RoomNotValidExceptoin("Diagonal wall is present");
        }

        int matrixDet = 0;
        for (int i = 0; i < cornersAmount - 1; i++) {
            if (i + 2 < cornersAmount) {
                matrixDet += countMatrix(points.get(i), points.get(i + 1), points.get(i + 2));
            } else {
                matrixDet += countMatrix(points.get(i), points.get(i + 1), points.get(0));
            }
        }
        if(matrixDet > 0) {
            throw new RoomNotValidExceptoin("Goes counter-clockwise.");
        }
        return true;
    }

    private int countMatrix(Point point1, Point point2, Point point3) {
        return point1.getX() * (point2.getY() - point3.getY()) - point1.getY() *
                (point2.getX() - point3.getX()) + point2.getX() * point3.getY() - point2.getY() * point3.getX();
    }
}
