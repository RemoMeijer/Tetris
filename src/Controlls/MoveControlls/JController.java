package Controlls.MoveControlls;

import Controlls.Controller;
import Tetrominos.Tetromino;
import javafx.geometry.Point2D;

import java.util.ArrayList;

public class JController {
    private ArrayList<Tetromino> activePieces;
    private Tetromino currentTetromino;
    private WallKicks wallKicks;
    private Tetromino oldTetromino;

    public JController(Tetromino currentTetromino, ArrayList<Tetromino> activePieces) {
        this.activePieces = activePieces;
        this.currentTetromino = currentTetromino;
        this.oldTetromino = currentTetromino;
        this.wallKicks = new WallKicks();
    }

    public Tetromino jMoveClockWise() {
        ArrayList<Point2D> newPoints = new ArrayList<>();
        Point2D currentPosition;
        Point2D newPosition;
        boolean validPosition = true;
        switch (currentTetromino.getRotation()) {
            case 0:
                for(int i = 0; i < 4; i++) {
                    currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
                    if(i == 0) {
                        newPosition = new Point2D(currentPosition.getX() + 2, currentPosition.getY());
                        validPosition = checkCollision(newPosition);
                        newPoints.add(newPosition);
                    }
                    if(i == 1) {
                        newPosition = new Point2D(currentPosition.getX() + 1, currentPosition.getY() - 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                    if(i == 2) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
                        newPoints.add(newPosition);
                    }
                    if(i == 3) {
                        newPosition = new Point2D(currentPosition.getX() - 1, currentPosition.getY() + 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                }
                if(!validPosition) {
                    currentTetromino.setShape(newPoints);
                    newPoints.clear();
                    currentTetromino = wallKicks.tests_0to1(currentTetromino, oldTetromino, activePieces);
                }
                if(!newPoints.isEmpty()) {
                    currentTetromino.setShape(newPoints);
                    currentTetromino.setRotation(1);
                }
                break;

            case 1:
                for(int i = 0; i < 4; i++) {
                    currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
                    if(i == 0) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY() + 2);
                        validPosition = checkCollision(newPosition);
                        newPoints.add(newPosition);
                    }
                    if(i == 1) {
                        newPosition = new Point2D(currentPosition.getX() + 1, currentPosition.getY() + 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                    if(i == 2) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
                        newPoints.add(newPosition);
                    }
                    if(i == 3) {
                        newPosition = new Point2D(currentPosition.getX() - 1, currentPosition.getY() - 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }

                        newPoints.add(newPosition);
                    }
                }
                if(!validPosition) {
                    currentTetromino.setShape(newPoints);
                    newPoints.clear();
                    currentTetromino = wallKicks.tests_1to2(currentTetromino, oldTetromino, activePieces);
                }
                if(!newPoints.isEmpty()) {
                    currentTetromino.setShape(newPoints);
                    currentTetromino.setRotation(2);
                }
                break;

            case 2:
                for(int i = 0; i < 4; i++) {
                    currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
                    if(i == 0) {
                        newPosition = new Point2D(currentPosition.getX() - 2, currentPosition.getY());
                        validPosition = checkCollision(newPosition);
                        newPoints.add(newPosition);
                    }
                    if(i == 1) {
                        newPosition = new Point2D(currentPosition.getX() - 1, currentPosition.getY() + 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                    if(i == 2) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
                        newPoints.add(newPosition);
                    }
                    if(i == 3) {
                        newPosition = new Point2D(currentPosition.getX() + 1, currentPosition.getY() - 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                }
                if(!validPosition) {
                    currentTetromino.setShape(newPoints);
                    newPoints.clear();
                    currentTetromino = wallKicks.tests_2to3(currentTetromino, oldTetromino, activePieces);
                }
                if(!newPoints.isEmpty()) {
                    currentTetromino.setShape(newPoints);
                    currentTetromino.setRotation(3);
                }
                break;

            case 3:
                for(int i = 0; i < 4; i++) {
                    currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
                    if(i == 0) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY() - 2);
                        validPosition = checkCollision(newPosition);
                        newPoints.add(newPosition);
                    }
                    if(i == 1) {
                        newPosition = new Point2D(currentPosition.getX() - 1, currentPosition.getY() - 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                    if(i == 2) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
                        newPoints.add(newPosition);
                    }
                    if(i == 3) {
                        newPosition = new Point2D(currentPosition.getX() + 1, currentPosition.getY() + 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                }
                if(!validPosition) {
                    currentTetromino.setShape(newPoints);
                    newPoints.clear();
                    currentTetromino = wallKicks.tests_3to0(currentTetromino, oldTetromino, activePieces);
                }
                if(!newPoints.isEmpty()) {
                    currentTetromino.setShape(newPoints);
                    currentTetromino.setRotation(0);
                }
                break;
        }
        return currentTetromino;
    }

    public Tetromino jMoveCounterClockWise(){
        ArrayList<Point2D> newPoints = new ArrayList<>();
        Point2D currentPosition;
        Point2D newPosition;
        boolean validPosition = true;
        switch (currentTetromino.getRotation()) {
            case 0:
                for(int i = 0; i < 4; i++) {
                    currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
                    if(i == 0) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY() + 2);
                        validPosition = checkCollision(newPosition);
                        newPoints.add(newPosition);
                    }
                    if(i == 1) {
                        newPosition = new Point2D(currentPosition.getX() + 1, currentPosition.getY() + 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                    if(i == 2) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
                        newPoints.add(newPosition);
                    }
                    if(i == 3) {
                        newPosition = new Point2D(currentPosition.getX() - 1, currentPosition.getY() - 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                }
                if(!validPosition) {
                    currentTetromino.setShape(newPoints);
                    newPoints.clear();
                    currentTetromino = wallKicks.tests_0to3(currentTetromino, oldTetromino, activePieces);
                }
                if(!newPoints.isEmpty()) {
                    currentTetromino.setShape(newPoints);
                    currentTetromino.setRotation(3);
                }
                break;

            case 1:
                for(int i = 0; i < 4; i++) {
                    currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
                    if(i == 0) {
                        newPosition = new Point2D(currentPosition.getX() - 2, currentPosition.getY());
                        validPosition = checkCollision(newPosition);
                        newPoints.add(newPosition);
                    }
                    if(i == 1) {
                        newPosition = new Point2D(currentPosition.getX() - 1, currentPosition.getY() + 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                    if(i == 2) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
                        newPoints.add(newPosition);
                    }
                    if(i == 3) {
                        newPosition = new Point2D(currentPosition.getX() + 1, currentPosition.getY() - 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }

                }
                if(!validPosition) {
                    currentTetromino.setShape(newPoints);
                    newPoints.clear();
                    currentTetromino = wallKicks.tests_1to0(currentTetromino, oldTetromino, activePieces);
                }
                if(!newPoints.isEmpty()) {
                    currentTetromino.setShape(newPoints);
                    currentTetromino.setRotation(0);
                }
                break;
            case 2:
                for(int i = 0; i < 4; i++) {
                    currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
                    if(i == 0) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY() - 2);
                        validPosition = checkCollision(newPosition);
                        newPoints.add(newPosition);
                    }
                    if(i == 1) {
                        newPosition = new Point2D(currentPosition.getX() - 1, currentPosition.getY() - 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                    if(i == 2) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
                        newPoints.add(newPosition);
                    }
                    if(i == 3) {
                        newPosition = new Point2D(currentPosition.getX() + 1, currentPosition.getY() + 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }

                }
                if(!validPosition) {
                    currentTetromino.setShape(newPoints);
                    newPoints.clear();
                    currentTetromino = wallKicks.tests_2to1(currentTetromino, oldTetromino, activePieces);
                }
                if(!newPoints.isEmpty()) {
                    currentTetromino.setShape(newPoints);
                    currentTetromino.setRotation(1);
                }
                break;

            case 3:
                for(int i = 0; i < 4; i++) {
                    currentPosition = (Point2D)this.currentTetromino.getShape().get(i);
                    if(i == 0) {
                        newPosition = new Point2D(currentPosition.getX() + 2, currentPosition.getY());
                        validPosition = checkCollision(newPosition);
                        newPoints.add(newPosition);
                    }
                    if(i == 1) {
                        newPosition = new Point2D(currentPosition.getX() + 1, currentPosition.getY() - 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                    if(i == 2) {
                        newPosition = new Point2D(currentPosition.getX(), currentPosition.getY());
                        newPoints.add(newPosition);
                    }
                    if(i == 3) {
                        newPosition = new Point2D(currentPosition.getX() - 1, currentPosition.getY() + 1);
                        if(validPosition) {
                            validPosition = checkCollision(newPosition);
                        }
                        newPoints.add(newPosition);
                    }
                }
                if(!validPosition) {
                    currentTetromino.setShape(newPoints);
                    newPoints.clear();
                    currentTetromino = wallKicks.tests_3to2(currentTetromino, oldTetromino, activePieces);
                }
                if(!newPoints.isEmpty()) {
                    currentTetromino.setShape(newPoints);
                    currentTetromino.setRotation(2);
                }
                break;
        }
        return currentTetromino;
    }

    public boolean checkCollision(Point2D currentPosition) {
        Controller checkCollision = new Controller(currentTetromino, activePieces);
        return checkCollision.checkCollision(currentPosition);
    }
}
