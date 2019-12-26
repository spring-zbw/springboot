package com.zbw.springboot.controller;

/**
 * Created by 郑博文 on 2019/12/26.
 */
public class TextController {

    public static void main(String[] args){
//        //数组下标
//        int[] duplication={0,1,2,3,4};
//        //数组长度
//        int length=5;
//        //数组本身
//        int[] num={0,2,3,3,4};
//        boolean theValue=duplicate(num,length,duplication);
//        System.out.println("这是结果"+theValue+"  "+duplication[0]);
//        int[][] matrix={{1,   4,  7, 11, 15},{2,   5,  8, 12, 19},{3,   6,  9, 16, 22},{10, 13, 14, 17, 24},{18, 21, 23, 26, 30}
//    };
//        int target=5;
//        boolean theValue=Find(target,matrix);
        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("A B C");
        String theValue =replaceSpace(stringBuffer);
        System.out.println("这是结果"+theValue);
    }
    //判断数组是否重复
    public static boolean duplicate(int[] nums, int length, int[] duplication) {
        //如果长度为0则直接返回不用判断
        if (nums == null || length <= 0){
            return false;
        }
       //遍历循环
        for (int i = 0; i < length; i++) {
            //当数值不等于下标时
            while (nums[i] != i) {
                //如果
                if (nums[i] == nums[nums[i]]) {
                    //将值换到第一个位置 可打印输出
                    duplication[0] = nums[i];
                    return true;
                }
                //将值换到对应的下标
                swap(nums, i, nums[i]);
            }
        }
        return false;
    }

    //数值位置交换（本身是几就交换到那个位置）
    private static void swap(int[] nums, int i, int j) {

        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    /**
     * 二维数组中的查找算法
     * @param target
     * @param matrix
     * @return
     */
    public static boolean Find(int target, int[][] matrix) {
        //如果二维数组长度是0则直接返回
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        //行
        int rows = matrix.length,
                //竖
                cols = matrix[0].length;
        // 从右上角开始
        int r = 0, c = cols - 1;
        while (r <= rows - 1 && c >= 0) {
            if (target == matrix[r][c]){
                return true;
            }
            //如果大于就就下移
            else if (target > matrix[r][c]){
                r++;
            }
            //如果小于则左移
            else{
                c--;
            }

        }
        return false;
    }

    /**
     * 字符串空格替换算法
     * @param str
     * @return
     */
    public static String replaceSpace(StringBuffer str){
        //原来字符串长度
        int P1=str.length()-1;
        //遍历循环 如果有一个空格就在后边加两个空格
        for(int i=0;i<=P1;i++){
            if(str.charAt(i)==' '){
                str.append("  ");

            }
        }
        //新的字符串长度
         int P2=str.length()-1;
        //当整体向左移动
        while (P1>=0&&P2>P1){
            //  从P1开始向前获取值
            char c=str.charAt(P1--);
               //如果值为空
                if(c==' '){
                    str.setCharAt(P2--,'0');
                    str.setCharAt(P2--,'2');
                    str.setCharAt(P2--,'%');
                }else {
                    str.setCharAt(P2--,c);
                }

        }
        return str.toString();
    }

}
