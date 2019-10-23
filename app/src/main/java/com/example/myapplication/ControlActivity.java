package com.example.myapplication;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class ControlActivity {

    static int[][] transformArr(int arr[][]) {
        int[][] upDownArr = new int[4][4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                upDownArr[i][j] = arr[j][i];
            }
        }

        return upDownArr;
    }

    static void setTextButton(int arr[][], Button[][] buttons) {
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

    static void addRandom(int arr[][]) {
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

    static void push0(int arr[][]) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                for (int t = 0; t < 3; t++) {
                    if (arr[i][j] == 0) {
                        for (int k = j; k < 3; k++) {
                            arr[i][k] = arr[i][k + 1];
                        }
                        arr[i][3] = 0;
                    }
                }
            }
        }
    }

    static void pushLeft(int arr[][]) {
        int[][] pushedArr = arr.clone();
        push0(arr);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++)
                if (arr[i][j] == arr[i][j + 1]) {
                    arr[i][j] *= 2;
                    for (int k = j+1; k < 2; k++) {
                        arr[i][k] = arr[i][k + 1];
                    }
                    arr[i][3] = 0;
                }
        }

        if (Arrays.equals(pushedArr, arr)) {            addRandom(arr);

        } else {
            addRandom(arr);
        }
    }

}
