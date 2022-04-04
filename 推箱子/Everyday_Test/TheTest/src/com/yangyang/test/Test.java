package com.yangyang.test;

import java.util.Scanner;
import java.util.Arrays;
//推箱子小游戏d


public class Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[][] map = Map.maps();//得到地图

        map[1][1] = '&';
        int x = 1, y = 1;
        //人物 坐标1,1
        char box = 'o';
        map[2][3] = box;
        //箱子 坐标2,2
        boxGame s = new boxGame();
        s.setMap(map);
        s.setMan(x, y);
        s.show();

    }

}

