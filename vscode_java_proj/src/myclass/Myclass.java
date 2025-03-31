package myclass;

public class Myclass {
    // 打印二维数组到控制台
    public static void print2DArray(int[][] array){
        for(int ii=0;ii<array.length;ii++){
            for(int jj=0;jj<array[ii].length;jj++){
                System.out.print(array[ii][jj]+" ");
            }
            System.out.println();
        }
    }
}
