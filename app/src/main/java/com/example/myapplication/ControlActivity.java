package com.example.myapplication;

import android.annotation.SuppressLint;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;


public class ControlActivity {

    private static int[][] transformArr(int[][] arr) {
        int[][] upDownArr = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                upDownArr[i][j] = arr[j][i];
            }
        }

        return upDownArr;
    }

    private static int[][] reverseArr(int[][] arr) {
        int[][] newArr = new int[4][4];

        for (int i = 0; i < 4; i++) {
            int tam = 3;
            for (int j = 0; j < 4; j++) {
                newArr[i][j] = arr[i][tam];
                tam--;
            }
        }

        return newArr;
    }

    @SuppressLint("SetTextI18n")
    static void setTextButton(int[][] arr, Button[][] buttons) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] != 0) {
                    buttons[i][j].setText("" + arr[i][j]);
                } else {
                    buttons[i][j].setText("");
                }
            }
        }
    }

    static void addRandom(int[][] arr) {
        ArrayList<int[]> emptyArr = new ArrayList<>();
        int[] arrRandomNumbers = new int[]{2, 2, 2, 4};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr[i][j] == 0) {
                    int[] position = new int[]{i, j};
                    emptyArr.add(position);
                }
            }
        }
        int[] addNumberAt = emptyArr.get((int) (Math.random() * emptyArr.size()));
        int numberRandom = arrRandomNumbers[(int) (Math.random() * arrRandomNumbers.length)];

        arr[addNumberAt[0]][addNumberAt[1]] = numberRandom;
    }

    private static int[][] filter0(int[][] arr) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int t = 0; t < 3; t++) {
                    if (arr[i][j] == 0) {
                        System.arraycopy(arr[i], j + 1, arr[i], j, 3 - j);
                        arr[i][3] = 0;
                    }
                }
            }
        }

        return arr;
    }

    private static int[][] copyArr(int[][] arr) {
        int[][] newArr = new int[4][4];
        for (int i = 0; i < 4; i++) {
            System.arraycopy(arr[i], 0, newArr[i], 0, 4);
        }
        return newArr;
    }

    private static boolean compareArr(int[][] arr1, int[][] arr2) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (arr1[i][j] != arr2[i][j])
                    return false;
            }
        }

        return true;
    }

    static int[][] pushLeft(int[][] arr) {
        int[][] newArr = copyArr(arr);
        int[][] pushedArr = filter0(newArr);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++)
                if (pushedArr[i][j] == pushedArr[i][j + 1]) {
                    pushedArr[i][j] *= 2;
                    for (int k = j + 1; k < 3; k++) {
                        pushedArr[i][k] = pushedArr[i][k + 1];
                    }
                    pushedArr[i][3] = 0;
                }
        }

        if (!compareArr(pushedArr, arr))
            addRandom(pushedArr);

        return pushedArr;
    }

    static int[][] pushRight(int[][] arr) {
        int[][] newArr = reverseArr(arr);
        return reverseArr(pushLeft(newArr));
    }

    static int[][] pushUp(int[][] arr) {
        int[][] upDownArr = transformArr(arr);
        return transformArr(pushLeft(upDownArr));
    }

    static int[][] pushDown(int[][] arr) {
        int[][] upDownArr = transformArr(arr);
        return transformArr(pushRight(upDownArr));
    }

}
