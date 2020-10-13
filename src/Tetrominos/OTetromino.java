package Tetrominos;

import javafx.geometry.Point2D;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class OTetromino implements Tetromino {

    private Color color;
    private ArrayList<Point2D> pointList;
    private int rotation;
    private Point2D pointBlock1;
    private Point2D pointBlock2;
    private Point2D pointBlock3;
    private Point2D pointBlock4;
    private Image image;


    public OTetromino(){
        this.color = new java.awt.Color(174, 151, 36);
        try{
            File path = new File("C:\\Users\\Remco\\Documents\\IdeaProjects\\TMCProjects\\2017_avans_ti_breda-2D_Graphics-2019\\Tetris\\Recources\\oPiece.png");
            this.image = ImageIO.read(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.pointList = new ArrayList<>();
        this.rotation = 0;
        this.pointBlock1 = new Point2D(4, -1);
        this.pointBlock2 = new Point2D(4, 0);
        this.pointBlock3 = new Point2D(5, -1);
        this.pointBlock4 = new Point2D(5, 0);

        pointList.add(pointBlock1);
        pointList.add(pointBlock2);
        pointList.add(pointBlock3);
        pointList.add(pointBlock4);
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public ArrayList getShape() {
        pointList.clear();
        pointList.add(pointBlock1);
        pointList.add(pointBlock2);
        pointList.add(pointBlock3);
        pointList.add(pointBlock4);

        return pointList;
    }

    @Override
    public void setShape(ArrayList<Point2D> points) {
        if(!points.isEmpty()) {
            pointBlock1 = points.get(0);
            pointBlock2 = points.get(1);
            pointBlock3 = points.get(2);
            pointBlock4 = points.get(3);
        }
    }

    public int getRotation(){
        return this.rotation;
    }

    public void setRotation(int rotation){
        this.rotation = rotation;
    }

    public String getPieceName(){
        return "o";
    }

    @Override
    public Image getImage() {
        return this.image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }
}
