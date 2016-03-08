package com.upmc.dar.apps.pointapp;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by mohameddd on 3/4/16.
 */
public class PointApp{

    static int currentId = 0 ;

    private static HashMap<Integer, Point> points = new HashMap<>();

    static int addPoint(int x, int y) {
        while(points.containsKey(currentId)) {
            currentId++;
        }
        points.put(currentId, new Point(x,y));

        return currentId;
    }

    static void deletePoint(int id) {
        points.remove(id);
    }

    static void modifyPointX(int id, int x) {
        points.get(id).x = x;
    }

    static void modifyPointY(int id, int y) {
        points.get(id).y = y;
    }

    static void modifyPointXY(int id, int x, int y) {
        points.get(id).x = x;
        points.get(id).y = y;
    }

    static int getPointX(int id) {
        return points.get(id).x;
    }

    static int getPointY(int id) {
        return points.get(id).y;
    }

    static Point getPoint(int id) {
        return points.get(id);
    }

    static String pointsToString() {
        StringBuilder builder = new StringBuilder();
        for(int id : points.keySet()) {
            builder.append("ID : " + id + ", X : " + points.get(id).x + ", Y : " + points.get(id).y + "\n");
        }
        return builder.toString();
    }

}
