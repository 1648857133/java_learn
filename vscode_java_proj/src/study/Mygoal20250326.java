package study;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.*;
import java.util.*;

import myclass.Myclass;

public class Mygoal20250326 {
    public int[][] generateMatrix(int n) {
        int[][] result=new int[n][n];
        int ucount=0,dcount=0,lcount=0,rcount=0;
        int count=1;
        int ii=0,jj=0;
        boolean ifup=true,ifri=true;
        while(true){
            // 上下排布
            if(ifup){
                for(jj=lcount;jj<=n-rcount-1;jj++){
                    result[ii][jj]=count;
                    count++;
                }
                ucount++;
                jj--;
            }else{
                for(jj=n-rcount-1;jj>=lcount;jj--){
                    result[ii][jj]=count;
                    count++;
                }
                dcount++;
                jj++;
            }
            ifup=!ifup;
            if(count==n*n){
                break;
            }
            
            // 右左排布
            if(ifri){
                for(ii=ucount;ii<=n-dcount-1;ii++){
                    result[ii][jj]=count;
                    count++;
                }
                rcount++;
                ii--;
            }else{
                for(ii=n-dcount-1;ii>=ucount;ii--){
                    result[ii][jj]=count;
                    count++;
                }
                lcount++;
                ii++;
            }
            ifri=!ifri;
            if(count-1==n*n){
                break;
            }
        }
        return result;
    }

    public int[] outsum(int n,int[] putin,int[][] areaputin){
        int linenum=areaputin.length;
        int[] result=new int[linenum];
        for(int ii=0;ii<linenum;ii++){
            int sum=0;
            int[] temp=Arrays.copyOfRange(putin,areaputin[ii][1],areaputin[ii][2]);
            for(int element:temp){
                sum+=element;
            }
            result[ii]=sum;
        }
        return result;
    }

    public void sumarea(){
        // 接收输入
        Scanner sc=new Scanner(System.in);
        // 接受整型
        int n=sc.nextInt();
        int[] vec=new int[n];
        int[] p=new int[n];

        int presum=0;
        for(int ii=0;ii<n;ii++){
            vec[ii]=sc.nextInt();
            presum+=vec[ii];
            p[ii]=presum;
        }

        // 接收求和区间
        while (sc.hasNext()) {
            // 空格输入可以连续接收
            int a=sc.nextInt();
            int b=sc.nextInt();

            int sum;
            if(a==0){
                sum=p[b];
            }else{
                sum=p[b]-p[a-1];
            }
            System.out.println(sum);
        }
        sc.close();
    }

    public static void main(String[] args) {
        Mygoal20250326 mg=new Mygoal20250326();
        // int[][] a=mg.generateMatrix(1);
        // Myclass.print2DArray(a);

        mg.sumarea();
    }
}
