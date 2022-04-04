package com.yangyang.test;

import java.util.Scanner;

class boxGame {
    private char[][] map;
    private int x, y;

    public boxGame() {
    }

    //得到地图
    public void setMap(char[][] map) {
        this.map = map;
    }

    //得到人物坐标
    public void setMan(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //游戏开始
    public void show() {
        Scanner sc = new Scanner(System.in);
        boolean finish = false;
            while (true) {// 循环游戏
                for (int i = 0; i < map.length; i++) {
                    for (int j = 0; j < map[i].length; j++) {
                        System.out.print(map[i][j]);
                    }
                    System.out.println();
                }
                System.out.println("--------------------");

                if (finish) {// 如果游戏结束则停止循环
                    System.out.println("The game end !");
                    break;
                }
                System.out.println("a左移，d右移，w上移，s下移，请输入你的指令：");
                String code = sc.nextLine();// 获取玩家输入的指令
                switch (code) {
                    case "a":// 如果输入的是a
                        if (map[x][y - 1] == ' ') {// 如果玩家左边是空区
                            map[x][y] = ' ';// 原位置变为空区
                            map[x][y - 1] = '&';// 玩家移动到新位置
                            y--;// 玩家坐标左移
                        } else if (map[x][y - 1] == 'o') {// 如果玩家左边是箱子
                            if (map[x][y - 2] != 'H') {// 如果箱子左边不是墙
                                if (map[x][y - 2] == '*') {// 如果箱子左边是目的地
                                    finish = true;// 游戏结束
                                }
                                map[x][y] = ' ';// 原位置变为空区
                                map[x][y - 1] = '&';// 玩家移动到新位置
                                map[x][y - 2] = 'o';// 箱子移动到新位置
                                y--;// 玩家位置左移
                            }
                        }
                        break;// 结束判断
                    case "d":// 如果输入的是d
                        if (map[x][y + 1] == ' ') {// 如果玩家右边是空区
                            map[x][y] = ' ';// 原位置变为空区
                            map[x][y + 1] = '&';// 玩家移动到新位置
                            y++;// 玩家坐标右移
                        } else if (map[x][y + 1] == 'o') {// 如果玩家右边是箱子
                            if (map[x][y + 2] != 'H') {// 如果箱子右边不是墙
                                if (map[x][y + 2] == '*') {// 如果箱子右边是目的地
                                    finish = true;// 游戏结束
                                }
                                map[x][y] = ' ';// 原位置变为空区
                                map[x][y + 1] = '&';// 玩家移动到新位置
                                map[x][y + 2] = 'o';// 箱子移动到新位置
                                y++;// 玩家坐标右移
                            }
                        }
                        break;// 结束判断
                    case "w":// 如果输入的是w
                        if (map[x - 1][y] == ' ') {// 如果玩家上方是空区
                            map[x][y] = ' ';// 原位置变为空区
                            map[x - 1][y] = '&';// 玩家移动到新位置
                            x--;// 玩家坐标上移
                        } else if (map[x - 1][y] == 'o') {// 如果玩家上方是箱子
                            if (map[x - 2][y] != 'H') {// 如果箱子上方不是墙
                                if (map[x - 2][y] == '*') {// 如果箱子上方是目的地
                                    finish = true;// 游戏结束
                                }
                                map[x][y] = ' ';// 原位置变为空区
                                map[x - 1][y] = '&';// 玩家移动到新位置
                                map[x - 2][y] = 'o';// 箱子移动到新位置
                                x--;// 玩家坐标上移
                            }
                        }
                        break;// 结束判断
                    case "s":// 如果输入的是s
                        if (map[x + 1][y] == ' ') {// 如果玩家下方是空区
                            map[x][y] = ' ';// 原位置变为空区
                            map[x + 1][y] = '&';// 玩家移动到新位置
                            x++;// 玩家坐标下移
                        } else if (map[x + 1][y] == 'o') {// 如果玩家下方是箱子
                            if (map[x + 2][y] != 'H') {// 如果箱子下方不是墙
                                if (map[x + 2][y] == '*') {// 如果箱子下方是目的地
                                    finish = true;// 游戏结束
                                }
                                map[x][y] = ' ';// 原位置变为空区
                                map[x + 1][y] = '&';// 玩家移动到新位置
                                map[x + 2][y] = 'o';// 箱子移动到新位置
                                x++;// 玩家坐标下移
                            }
                        }
                        break;// 结束判断
                    default:// 如果输入的是其他指令
                        System.out.println("您输入的指令有误！");
                }
                System.out.println("游戏结束");
            }

    }
}
