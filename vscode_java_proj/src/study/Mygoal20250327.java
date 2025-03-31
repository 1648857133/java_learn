package study;
import java.util.Scanner;

public class Mygoal20250327 {
    public void buyland(){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[][] land=new int[n][m];
        int[][] landlie=new int[m][n];

        // 接收数据
        while (sc.hasNextInt()) {
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    land[i][j]=sc.nextInt();
                    landlie[j][i]=land[i][j];
                }
            }
        }

        // 行划分
        int mindat=Integer.MAX_VALUE;
        for(int ii=0;ii<n-1;ii++){
            int temp1=0;int temp2=0;
            for(int jj=0;jj<=ii;jj++){  
                for(int element:land[jj]){
                    temp1+=element;
                }
            }
            for(int jj=ii+1;jj<=n-1;jj++){  
                for(int element:land[jj]){
                    temp2+=element;
                }
            }
            mindat=Math.min(mindat,Math.abs(temp1-temp2));
        }
        
        // 列划分
        for(int ii=0;ii<m-1;ii++){
            int temp1=0;int temp2=0;
            for(int jj=0;jj<=ii;jj++){  
                for(int element:landlie[jj]){
                    temp1+=element;
                }
            }
            for(int jj=ii+1;jj<=m-1;jj++){  
                for(int element:landlie[jj]){
                    temp2+=element;
                }
            }
            mindat=Math.min(mindat,Math.abs(temp1-temp2));
        }
        System.out.println(mindat);
        sc.close();
    }

    public static void main(String[] args){
        Mygoal20250327 mg=new Mygoal20250327();

    }
    
}
