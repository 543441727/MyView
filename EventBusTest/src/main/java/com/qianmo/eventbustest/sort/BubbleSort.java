package com.qianmo.eventbustest.sort;

/**
 * Created by wangjitao on 2016/10/18 0018.
 * 冒泡排序
 */
public class BubbleSort {
    /**
     * 冒泡排序
     * 比较两个相邻的元素，将最大值放到最右边
     */
    public static void bubbleSort(int[] data) {
        int length = data.length;
        for (int i = 1; i < length; i++) {
            for (int j = 1; j < length; j++) {
                if (data[j - 1] > data[j]) {
                    int temp = data[j - 1];
                    data[j - 1] = data[j];
                    data[j] = temp;
                }
            }
        }
    }

    /**
     *选择排序
     * 每一趟选择最小值
     */

    public static void main(String args[]) {
        int[] data = {15, 18, 12, 4, 29};
        bubbleSort(data);
        for (int i = 0; i < data.length; i++) {
            System.out.println("position:" + data[i]);
        }
    }
}
