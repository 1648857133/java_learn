package study;

import java.util.Arrays;
import java.util.Scanner;

public class mysolution {
    // 二分法
    // 2025/6/25
    public int search(int[] nums,int target){
        // 边界
        if (target<nums[0] || target>nums[nums.length-1]){
            return -1;
        }

        int left=0;
        int right=nums.length-1;
        while (true) {
            int temp=(left+right)/2;
            if(target>nums[temp]){
                left=temp;
            }else if(target<nums[temp]){
                right=temp;
            }else{
                return temp;
            }

            // 特殊情况
            if(right-left==1){
                if(target==nums[left]){
                    return left;
                }else if(target==nums[right]){
                    return right;
                }else{
                    return -1;
                }
            }
        }
    }

    // 代码随想录二分法，左闭右闭
    // 2025/6/25
    public int search_1(int[] nums,int target){
        // 边界
        if(target<nums[0] || target>nums[nums.length-1]){
            return -1;
        }

        int left=0, right = nums.length - 1;
        while(left<=right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid-1;
            }
        }

        return -1;
    }

    // 代码随想录二分法，左闭右开
    // 2025/6/25
    public int search_2(int[] nums,int target){
        int left=0, right = nums.length - 1;
        while(left<right){
            int mid=left+(right-left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]<target){
                left=mid+1;
            }else{
                right=mid;
            }
        }
        return -1;
    }

    // 移除元素
    // 2025/6/25
    public int removeelement(int[] nums,int val){
        int difcount=0;
        for(int ii=0;ii<nums.length;ii++){
            if(nums[ii]==val){
                difcount=difcount+1;
                for(int jj=ii;jj<nums.length-difcount;jj++){
                    nums[jj]=nums[jj+1];
                }
                ii=ii-1;//避免跳跃
            }
            // 避免重复计算尾部元素
            if(ii+difcount+1==nums.length){
                break;
            }
        }
        return nums.length-difcount;
    }

    // 代码随想录移除元素，双指针法
    // 上面算法属于暴力搜索，时间复杂度O(n^2)
    // 双指针法其实就是一个覆写位置，一个遍历位置
    // 2025/6/25
    public int removeelement_1(int[] nums,int val){
        int preindex=0;
        for(int lastindex=0;lastindex<nums.length-1;lastindex++){
            if(nums[lastindex]!=val){
                nums[preindex]=nums[lastindex];
                preindex++;
            }
        }
        return preindex;
    }

    // 有序数组的平方
    // 输入：nums = [-4,-1,0,3,10]
    // 输出：[0,1,9,16,100]
    // 解释：平方后，数组变为 [16,1,0,9,100]
    // 排序后，数组变为 [0,1,9,16,100]
    // 2025/6/25
    public int[] sortedequares(int[] nums){
        for(int ii=0;ii<nums.length;ii++){
            nums[ii]=nums[ii]*nums[ii];
        }
        Arrays.sort(nums);
        return nums;
    }

    // 代码随想录双指针法
    // 之前对双指针法的理解有误，双指针法实际应该是想办法一边遍历一边写入
    // 这里可以发现最大值只会出现在两边，因此对比两边即可得到最大的值，依次遍历
    // 2025/6/25
    public int[] sortedequares_1(int[] nums){
        int left=0,right=nums.length-1,index=nums.length-1;
        int[] result=new int[nums.length];
        for(int ii=0;ii<nums.length;ii++){
            if(nums[left]*nums[left]>nums[right]*nums[right]){
                result[index]=nums[left]*nums[left];
                index--;
                left++;
            }else{
                result[index]=nums[right]*nums[right];
                index--;
                left++;
            }
        }
        return result;
    }

    // 长度最小的子数组
    // 给定一个含有 n 个正整数的数组和一个正整数 target 。
    // 找出该数组中满足其总和大于等于 target 的长度最小的子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
    // 输入：target = 7, nums = [2,3,1,2,4,3]
    // 输出：2
    // 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
    // 2025/6/25
    public int minsubarrayen(int s,int[] nums){
        int[] sum=new int[nums.length+1];
        int temp=0;
        sum[0]=0;
        for(int ii=0;ii<nums.length;ii++){
            temp=temp+nums[ii];
            sum[ii+1]=temp;
        }
        if(s>sum[sum.length-1]){
            return 0;
        }
        int mincount=Integer.MAX_VALUE;// 可以用infinity
        int preindex=0;
        int lastindex=1;
        while (true) {
            if(lastindex>=nums.length+1){
                break;
            }
            if(sum[lastindex]-sum[preindex]>=s){
                mincount=(mincount<lastindex-preindex)?mincount:lastindex-preindex;
                if(lastindex>preindex+1){
                    preindex=preindex+1;
                }else{
                    preindex=preindex+1;
                    lastindex=lastindex+1;
                }
            }else{
                lastindex=lastindex+1;
            }
        }
        return mincount;
    }

    // 代码随想录长度最小的子数组
    // 滑块思想，其实和上面写的差不多
    // 2025/6/25
    public int minsubarrayen_1(int s,int[] nums){
        int left=0;
        int sum=0;
        int result=Integer.MAX_VALUE;
        for(int right=0;right<nums.length;right++){
            sum=sum+nums[right];
            while (sum>=s) {
                result=Math.min(result,right-left+1);
                sum=sum-nums[left];
                left=left+1;
            }
        }
        return result==Integer.MAX_VALUE?0:result;
    }

    // 螺旋矩阵
    // 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
    // 输入：n = 3
    // 输出：[[1,2,3],[8,9,4],[7,6,5]]
    // 2025/6/25
    public int[][] generatematrix(int n){
        int[][] result=new int[n][n];
        int nup=0,ndo=0,nle=0,nri=0;
        int count=1;
        int jjindex=0,iiindex=0;
        while (true) {
            // 上右
            for(int jj=jjindex;jj<n-nri;jj++){
                result[iiindex][jj]=count;
                count=count+1;
            }
            nup=nup+1;
            jjindex=n-nri-1;

            // 右下
            for(int ii=iiindex+1;ii<n-ndo;ii++){
                result[ii][jjindex]=count;
                count=count+1;
            }
            nri=nri+1;
            iiindex=n-ndo-1;

            // 下左
            for(int jj=jjindex-1;jj>nle-1;jj--){
                result[iiindex][jj]=count;
                count=count+1;
            }
            ndo=ndo+1;
            jjindex=nle;

            // 左上
            for(int ii=iiindex-1;ii>nup-1;ii--){
                result[ii][jjindex]=count;
                count=count+1;
            }
            nle=nle+1;
            iiindex=nle;
            jjindex=iiindex;
            
            // 退出
            if(count-1>=n*n){
                break;
            }
        }
 
        return result;
    }

    // 代码随想录螺旋矩阵
    // 思路和我的差不多
    // 我的思路没有进行系统化，就是先完成基础功能，遇到问题解决问题
    // 循环不变量原则
    // 2025/6/28
    public int[][] generatematrix_1(int n){
        int[][] nums=new int[n][n];
        int startx=0,starty=0;// 每圈起始点
        int offset=1;
        int count=1;
        int loop=1;
        int i,j;

        while(loop<=n/2){
            // 顶部
            // 左闭右开
            for(j=starty;j<n-offset;j++){
                nums[startx][j]=count++;
            }

            // 右列
            // 左闭右开
            for(i=startx;i<n-offset;i++){
                nums[i][j]=count++;
            }

            // 下列
            // 左闭右开
            for(;j>starty;j--){
                nums[i][j]=count++;
            }

            // 左列
            for(;i>startx;i--){
                nums[i][j]=count++;
            }
            startx++;
            starty++;
            offset++;
            loop++;
        }
        if(n%2==1){
            nums[startx][starty]=count;
        }
        return nums;
    }

    // 区间和
    // 给定一个整数数组Array，计算改数组在每个指定区间内元素的总和
    // 第一行输入为整数数组Array的长度n，接下来n行，每行一个整数，表示数组的元素，随后输入为需要计算总和的区间下标a,b（b>=a），直到文件结束
    // 输入示例
    // 5
    // 1
    // 2
    // 3
    // 4
    // 5
    // 0 1
    // 1 3
    // 输出示例
    // 3
    // 9
    // 2025/6/28
    public void intersum(){
        Scanner sc=new Scanner(System.in);// 控制台读取一行
        if(sc.hasNextLine()){
            int n=sc.nextInt();
            int[] nums=new int[n];
            int[] numssum=new int[n];
            int ii=0;
            int sum=0;
            while(n-->0){
                nums[ii]=sc.nextInt();
                sum=sum+nums[ii];
                numssum[ii]=sum;
                ii++;
            }
            while(sc.hasNextInt()){
                int a=sc.nextInt();
                int b=sc.nextInt();
                System.out.println(numssum[b]-numssum[a]+nums[a]);
            }
        }
        sc.close();
    }

    // 代码随想录区间和
    // 和我的方法基本一致
    // 2025/6/28
    public void intersum_1(){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] vec = new int[n];
        int[] p = new int[n];
        int presum = 0;
        for (int i = 0; i < n; i++) {
            vec[i] = scanner.nextInt();
            presum += vec[i];
            p[i] = presum;
        }

        while (scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            int sum;
            if (a == 0) {
                sum = p[b];
            } else {
                sum = p[b] - p[a - 1];
            }
            System.out.println(sum);
        }
        scanner.close();
    }

    // 购买土地 
    // 在一个城市区域内，被划分成了n * m个连续的区块，每个区块都拥有不同的权值，代表着其土地价值。
    // 目前，有两家开发公司，A 公司和 B 公司，希望购买这个城市区域的土地。 
    // 现在，需要将这个城市区域的所有区块分配给 A 公司和 B 公司。
    // 然而，由于城市规划的限制，只允许将区域按横向或纵向划分成两个子区域，而且每个子区域都必须包含一个或多个区块。 
    // 为了确保公平竞争，你需要找到一种分配方式，使得 A 公司和 B 公司各自的子区域内的土地总价值之差最小。 注意：区块不可再分。
    // 输入
    // 第一行输入两个正整数，代表 n 和 m。 
    // 接下来的 n 行，每行输出 m 个正整数。
    // 输出
    // 请输出一个整数，代表两个子区域内土地总价值之间的最小差距。
    // 输入
    // 3 3
    // 1 2 3
    // 2 1 3
    // 1 2 3
    // 输出
    // 0
    // 2025/6/28
    public void buyingland(){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[][] datmap=new int[n][m];
        int[] rowsum=new int[n];
        int[] colsum=new int[m];
        for(int ii=0;ii<n;ii++){
            for(int jj=0;jj<m;jj++){
                datmap[ii][jj]=sc.nextInt();
            }
        }
        // 行列前缀和
        for(int ii=0,rowtemp=0;ii<n;ii++){
            for(int jj=0;jj<m;jj++){
                rowtemp=rowtemp+datmap[ii][jj];
                rowsum[ii]=rowtemp;
            }
        }
        for(int jj=0,coltemp=0;jj<m;jj++){
            for(int ii=0;ii<n;ii++){
                coltemp=coltemp+datmap[ii][jj];
                colsum[jj]=coltemp;
            }
        }
        // 遍历找最优
        int compval=Integer.MAX_VALUE;
        for(int ii=0;ii<n-1;ii++){
            compval=Math.min(Math.abs(rowsum[rowsum.length-1]-rowsum[ii]-rowsum[ii]), compval);
        }
        for(int jj=0;jj<m-1;jj++){
            compval=Math.min(Math.abs(colsum[colsum.length-1]-colsum[jj]-colsum[jj]), compval);
        }

        sc.close();
        System.out.println(compval);
    }

    // 代码随想录购买土地 
    // 同样是采用前缀和的方式
    // 2025/6/28
    // 方法不如我，略










}