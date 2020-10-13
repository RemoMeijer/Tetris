package Tetrominos;

import javafx.geometry.Point2D;

import java.awt.*;
import java.util.ArrayList;

public interface Tetromino {
    Color getColor();
    ArrayList getShape();
    void setShape(ArrayList<Point2D> points);
    int getRotation();
    void setRotation(int rotation);
    String getPieceName();
    Image getImage();
    void setImage(Image image);


}
