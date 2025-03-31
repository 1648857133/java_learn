package study;

import java.util.Arrays;

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
            for(int ii=iiindex;ii>nup-1;ii--){
                result[ii][jjindex]=count;
                count=count+1;
            }
            nup=nup+1;
            iiindex=nup;
            
            // 退出
            if(count>=n*n){
                break;
            }
        }
 
        return result;
    }













}