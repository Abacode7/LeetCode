package com.company;

public class LC1041_RobotBoundedInCircle {
    public static void main(String[] args){
        System.out.println(isRobotBounded("GGLLGG")); // exp response = true
    }

    public static boolean isRobotBounded(String instructions) {
        // If a robot pattern will form a cycle,
        // it'll take at most 4 iterations of that pattern to
        // show.

        // N = 0, E = 1, S = 2, W =3
        int i = 0, j = 0;
        int direction = 0;

        for(int a=0; a<4; a++){
            for(char c: instructions.toCharArray()){
                if(c == 'G'){
                    if(direction == 0) j++; // move up
                    else if(direction == 2) j--; // move down
                    else if(direction == 1) i++; // move right
                    else i--; // move left
                }else if(c == 'L'){
                    direction--;
                    if(direction < 0) direction = 3;
                }else{
                    direction++;
                    if(direction > 3) direction = 0;
                }
            }

            if(i==0 && j==0 && direction==0) return true;
        }

        return false;
    }
}
