package com.artyom;

import static com.artyom.KnightChessMain.*;

public class Knight {
    public static int flagNoSolutions = 0;

    static boolean exist(int x, int y, int s) {
        for (int i = 0; i <= s; i++) {
            if (rx[i]==x && ry[i]==y) return true;
        }
        return false;
    }

    // function to find possible moves
    static boolean findmove(int s) {
        if (s + 1 >= WIDTH * HEIGHT) return true;

        // All possible moves of a knight
        for (int dx=-2; dx<=2; dx++) {
            for (int dy=-2; dy<=2; dy++) {
                if (Math.abs(dx) + Math.abs(dy) != 3) continue;

                // Position of knight after move
                int xn=rx[s] + dx, yn = ry[s] + dy;

                // count valid moves
                if (xn < 1 || xn > WIDTH || yn < 1 || yn > HEIGHT) continue;

                if (exist(xn, yn, s)) continue;

                rx[s+1] = xn;
                ry[s+1] = yn;

                if (findmove(s+1)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void calculate () throws InterruptedException {
        if (findmove(0)) {
            System.out.print("Possible moves (from left to right): ");
            for (int i = 0; i < WIDTH * HEIGHT; i++) {
                if (i% 10 == 0) {System.out.println();}
                System.out.print("(" + rx[i] + "," + ry[i] + ") ");
            }
        } else {
            flagNoSolutions = 1;
            System.out.println("No solutions!");
        }
    }
}
