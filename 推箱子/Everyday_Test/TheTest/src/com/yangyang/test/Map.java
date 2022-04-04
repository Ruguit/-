package com.yangyang.test;
import java.util.Scanner;
class Map {
    public static char[][]maps(){
        char [][]mapss=new char[10][20];
        //第一行
        for(int i=0;i<mapss[0].length;i++){
            mapss[0][i]='H';
        }

        //中间行
        for(int i=1;i< mapss.length;i++){
            mapss[i][0]='H';
            for(int j=1;j< 20;j++){
                mapss[i][j]=' ';
            }
            mapss[i][19]='H';
        }
        //最后一行
        for(int i=0;i< mapss[mapss.length-1].length;i++){
            mapss[mapss.length-1][i]='H';
        }

        //障碍
        for(int i=1;i<6;i++){
            mapss[i][6]='H';
        }
        for(int i= mapss.length-2;i>3;i--){
            mapss[i][10]='H';
        }
        //目的地
        mapss[4][17]='*';

        return mapss;
    }
}
