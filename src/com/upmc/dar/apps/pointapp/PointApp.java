package com.upmc.dar.apps.pointapp;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by mohameddd on 3/4/16.
 */
public class PointApp{

    static int currentId = 0 ;

    private static HashMap<Integer, Point> points = new HashMap<>();

    public static int addPoint(int x, int y) {
        while(points.containsKey(currentId)) {
            currentId++;
        }
        points.put(currentId, new Point(x,y));

        return currentId;
    }

    public static void deletePoint(int id) {
        points.remove(id);
    }

    public static void modifyPointX(int id, int x) {
        points.get(id).x = x;
    }

    public static void modifyPointY(int id, int y) {
        points.get(id).y = y;
    }

    public static void modifyPointXY(int id, int x, int y) {
        points.get(id).x = x;
        points.get(id).y = y;
    }

    public static int getPointX(int id) {
        return points.get(id).x;
    }

    public static int getPointY(int id) {
        return points.get(id).y;
    }

    public static Point getPoint(int id) {
        return points.get(id);
    }

    public static String pointsToString() {
        StringBuilder builder = new StringBuilder();
        for(int id : points.keySet()) {
            builder.append("ID : " + id + ", X : " + points.get(id).x + ", Y : " + points.get(id).y + "\n");
        }
        return builder.toString();
    }

}
