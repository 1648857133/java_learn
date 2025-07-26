package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;
// import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicInteger;

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

    // 链表
    // 2025/6/29
    public class listnode {
        // 节点值
        int val;

        // 前后指针
        listnode prev;
        listnode next;

        // 构造
        public listnode(){
        }
        public listnode(int valin){
            this.val=valin;
        }
        public listnode(int valin,listnode nextin){
            this.val=valin;
            this.next=nextin;
        }
        public listnode(int valin,listnode nextin,listnode previn){
            this.val=valin;
            this.next=nextin;
            this.prev=previn;
        }
    }

    // 移除链表元素
    // 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回新的头节点 。
    // 输入：head = [1,2,6,3,4,5,6], val = 6
    // 输出：[1,2,3,4,5]
    // 2025/6/29
    public listnode removelistelement(listnode head,int val){
        listnode result=head;
        listnode templn=result;
        // 首位处理
        while(templn!=null){
            if(templn.val==val){
                result=templn.next;
                templn=result;
            }else{
                break;
            }
        }
        while (templn!=null) {
            if(templn.val==val){
                result.next=templn.next;
            }
            templn=templn.next;
        }
        return result;
    }

    // 代码随想录移除链表元素
    // 使用原来的链表操作
    // 2025/6/29
    public listnode removelistelement_1(listnode head,int val){
        while(head!=null&&head.val==val){
            head=head.next;
        }
        listnode curr=head;
        while (curr!=null&&curr.next!=null) {
            if(curr.next.val==val){
                curr.next=curr.next.next;
            }else{
                curr=curr.next;
            }
        }
        return head;
    }

    // 代码随想录移除链表元素
    // 设置虚拟头结点
    // 2025/6/29
    public listnode removelistelement_2(listnode head,int val){
        listnode dummy=new listnode();
        dummy.next=head;
        listnode curr=dummy;
        while (curr.next!=null) {
            if(curr.next.val==val){
                curr.next=curr.next.next;
            }else{
                curr=curr.next;
            }
        }
        return dummy.next;
    }

    // 代码随想录移除链表元素
    // 递归
    // 2025/6/29
    public listnode removelistelement_3(listnode head,int val){
        if(head==null){
            return head;
        }
        // 假设本函数返回后面完整地已经去掉val节点的子链表
        // 在当前递归层用当前节点接住后面的子链表
        // 随后判断当前的node是否需要被删除，如果是，就返回
        // 也可以先判断是否需要删除当前node,但是这样条件语句会比较不好想
        head.next=removelistelement_3(head.next,val);
        if(head.val==val){
            return head.next;
        } 
        return head;
        // 实际上就是怀远一个从尾部开始重新构建链表的过程
    }
    
    // 设计链表
    // 你可以选择使用单链表或者双链表，设计并实现自己的链表。
    // 单链表中的节点应该具备两个属性：val 和 next 。val 是当前节点的值，next 是指向下一个节点的指针/引用。
    // 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。假设链表中的所有节点下标从 0 开始。
    // 实现 MyLinkedList 类：
    // MyLinkedList() 初始化 MyLinkedList 对象。
    // int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
    // void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点。
    // void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
    // void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
    // void deleteAtIndex(int index) 如果下标有效，则删除链表中下标为 index 的节点。
    // 输入
    // ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get", "deleteAtIndex", "get"]
    // [[], [1], [3], [1, 2], [1], [1], [1]]
    // 输出
    // [null, null, null, null, 2, null, 3]
    // 解释
    // MyLinkedList myLinkedList = new MyLinkedList();
    // myLinkedList.addAtHead(1);
    // myLinkedList.addAtTail(3);
    // myLinkedList.addAtIndex(1, 2);    // 链表变为 1->2->3
    // myLinkedList.get(1);              // 返回 2
    // myLinkedList.deleteAtIndex(1);    // 现在，链表变为 1->3
    // myLinkedList.get(1);              // 返回 3
    // 2025/6/29
    public class mylinkedlist_single {// 单链表
        int val;
        mylinkedlist_single next;

        // 虚拟头结点，外部已有定义
        // private class listnode {
        //     int val;
        //     listnode next;
        //     listnode(int val){
        //         this.val=val;
        //     }
        // }
        private listnode head;
        private int size;

        // 初始化MyLinkedList 对象
        public mylinkedlist_single(){
            head=new listnode(0);
            size=0;
        }

        // 获取头结点
        public listnode gethead(){
            return head;
        }

        // 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 
        public int get(int index) {
            if(index<0||index>=size){
                return -1;
            }
            listnode cur=head;
            for(int ii=0;ii<index+1;ii++){
                cur=cur.next;
            }
            return cur.val;
        }

        // 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点
        public void addAtHead(int val){
            listnode temp=new listnode(val);
            temp.next=head.next;
            head.next=temp;
            size=size+1;
        }

        // 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
        public void addAtTail(int val){
            listnode temp=new listnode(val);
            listnode cur=head;
            for(int ii=0;ii<size;ii++){
                cur=cur.next;
            }
            cur.next=temp;
            size=size+1;
        }

        // 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。
        // 如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。
        // 如果 index 比长度更大，该节点将 不会插入 到链表中。
        public void addAtIndex(int index,int val){
            if(index<0||index>size){
                return;
            }
            if(index<size){
                listnode cur=head;
                for(int ii=0;ii<index;ii++){
                    cur=cur.next;
                }
                listnode temp=new listnode(0);
                temp.val=val;
                temp.next=cur.next;
                cur.next=temp;
                size=size+1;
            }else{
                this.addAtTail(val);
            }
        }

        // 如果下标有效，则删除链表中下标为 index 的节点。
        public void deleteAtIndex(int index){
            if(index<0||index>=size){
                return;
            }
            listnode cur=head;
            for(int ii=0;ii<index;ii++){
                cur=cur.next;
            }
            cur.next=cur.next.next;
            size=size-1;
        }

        // 输出链表
        public void printlist(){
            listnode cur=head;
            for(int ii=0;ii<size;ii++){
                cur=cur.next;
                System.out.println(cur.val);
            }
        }
    }

    // 双链表
    // 2025/6/29
    public class mylinkedlist_dou {// 双链表
        int val;
        mylinkedlist_dou next;
        mylinkedlist_dou prev;

        // 虚拟头结点
        // private class listnode {
        //     int val;
        //     listnode next;
        //     listnode prev;
        //     listnode(int val){
        //         this.val=val;
        //     }
        // }
        private listnode head;
        private int size;

        // 初始化MyLinkedList 对象
        public mylinkedlist_dou(){
            head=new listnode(0);
            size=0;
        }

        // 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 
        public int get(int index) {
            if(index<0||index>=size){
                return -1;
            }
            listnode cur=head;
            for(int ii=0;ii<index+1;ii++){
                cur=cur.next;
            }
            return cur.val;
        }

        // 将一个值为 val 的节点插入到链表中第一个元素之前。在插入完成后，新节点会成为链表的第一个节点
        public void addAtHead(int val){
            listnode temp=new listnode(0);
            temp.val=val;
            temp.next=head.next;
            temp.prev=head;
            if(temp.next!=null){
                temp.next.prev=head;
            }
            size=size+1;
        }

        // 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
        public void addAtTail(int val){
            listnode temp=new listnode(0);
            temp.val=val;
            listnode cur=head;
            for(int ii=0;ii<size;ii++){
                cur=cur.next;
            }
            cur.next=temp;
            temp.prev=cur;
            size=size+1;
        }

        // 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。
        // 如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。
        // 如果 index 比长度更大，该节点将 不会插入 到链表中。
        public void addAtIndex(int index,int val){
            if(index<0||index>size){
                return;
            }
            if(index<size){
                listnode cur=head;
                for(int ii=0;ii<index;ii++){
                    cur=cur.next;
                }
                listnode temp=new listnode(0);
                temp.val=val;
                temp.next=cur.next;
                temp.prev=cur;
                cur.next=temp;
                if(temp.next!=null){
                    temp.next.prev=temp;
                }
                size=size+1;
            }else{
                this.addAtTail(val);
            }
        }

        // 如果下标有效，则删除链表中下标为 index 的节点。
        public void deleteAtIndex(int index){
            if(index<0||index>=size){
                return;
            }
            listnode cur=head;
            for(int ii=0;ii<index;ii++){
                cur=cur.next;
            }
            cur.next=cur.next.next;
            cur.next.prev=cur;
            size=size-1;
        }

        // 输出链表
        public void printlist(){
            listnode cur=head;
            for(int ii=0;ii<size;ii++){
                cur=cur.next;
                System.out.println(cur.val);
            }
        }
    }

    // 代码随想录单链表
    // 2025/6/29
    public class mylinkedlist_1{
        private class listnode {
            int val;
            listnode next;
            listnode(int val){
                this.val=val;
            }
        }
        private int size;
        private listnode head;

        // 初始化链表
        public mylinkedlist_1(){
            this.size=0;
            this.head=new listnode(0);
        }

        //获取第index个节点的数值，注意index是从0开始的，第0个节点就是虚拟头结点
        public int get(int index){
            if(index<0||index>=size){
                return -1;
            }
            listnode cur=head;
            //第0个节点是虚拟头节点，所以查找第 index+1 个节点
            for(int ii=0;ii<=index;ii++){
                cur=cur.next;
            }
            return cur.val;
        }

        public void addathead(int val){
            listnode newnode=new listnode(val);
            newnode.next=head.next;
            head.next=newnode;
            size++;
        }

        public void addattail(int val){
            listnode newnode=new listnode(val);
            listnode cur=head;
            while (cur.next!=null) {
                cur=cur.next;
            }
            cur.next=newnode;
            size++;
        }

        // 在第 index 个节点之前插入一个新节点，例如index为0，那么新插入的节点为链表的新头节点。
        // 如果 index 等于链表的长度，则说明是新插入的节点为链表的尾结点
        // 如果 index 大于链表的长度，则返回空
        public void addAtIndex(int index,int val){
            if(index<0||index>size){
                return;
            }

            listnode pre=head;
            for(int ii=0;ii<index;ii++){
                pre=pre.next;
            }
            listnode newnode=new listnode(val);
            newnode.next=pre.next;
            pre.next=newnode;
            size++;
        }

        public void deleteatindex(int index){
            if(index<0||index>size){
                return;
            }

            listnode pre=head;
            for(int ii=0;ii<index;ii++){
                pre=pre.next;
            }
            pre.next=pre.next.next;
            size--;
        }
    }

    // 代码随想录双链表
    // 注意虚拟尾结点也是能用上的
    // 2025/6/29
    public class mylinkedlist_2 {
        class ListNode{
            int val;
            ListNode next, prev;
            ListNode(int val){
                this.val = val;
            }
        }

        // 记录链表中元素的数量
        private int size;
        // 记录链表的虚拟头结点和尾结点
        private ListNode head, tail;
        
        public mylinkedlist_2() {
            // 初始化操作
            this.size = 0;
            this.head = new ListNode(0);
            this.tail = new ListNode(0);
            // 这一步非常关键，否则在加入头结点的操作中会出现null.next的错误！！！
            // 可以避免判断下一个是否存在
            this.head.next = tail;
            this.tail.prev = head;
        }

        public int get(int index) {
            //判断index是否有效
            if(index < 0 || index >= size){
                return -1;
            }
            ListNode cur = head;
            //判断是哪一边遍历时间更短
            if(index >= size / 2){
                //tail开始
                cur = tail;
                for(int i = 0; i < size - index; i++){
                    cur = cur.prev;
                }
            }else{
                for(int i = 0; i <= index; i++){
                    cur = cur.next; 
                }
            }
            return cur.val;
        }

        public void addAtHead(int val) {
            //等价于在第0个元素前添加
            addAtIndex(0, val);
        }
        
        public void addAtTail(int val) {
            //等价于在最后一个元素(null)前添加
            addAtIndex(size, val);
        }
        
        public void addAtIndex(int index, int val) {
            //判断index是否有效
            if(index < 0 || index > size){
                return;
            }

            //找到前驱
            ListNode pre = head;
            for(int i = 0; i < index; i++){
                pre = pre.next;
            }
            //新建结点
            ListNode newNode = new ListNode(val);
            newNode.next = pre.next;
            pre.next.prev = newNode;
            newNode.prev = pre;
            pre.next = newNode;
            size++;
            
        }

        public void deleteAtIndex(int index) {
            //判断index是否有效
            if(index < 0 || index >= size){
                return;
            }

            //删除操作
            ListNode pre = head;
            for(int i = 0; i < index; i++){
                pre = pre.next;
            }
            pre.next.next.prev = pre;
            pre.next = pre.next.next;
            size--;
        }
    }

    // 反转链表
    // 输入：head = [1,2,3,4,5]
    // 输出：[5,4,3,2,1]
    // 2025/6/29
    public listnode reverselist(listnode head) {
        // 处理空链表或单节点链表
        if (head == null || head.next == null) {
            return head;
        }
        // 递归反转剩余链表，la 是新链表的头节点
        listnode la = reverselist(head.next);
        // 关键：将当前节点连接到反转后链表的尾部
        head.next.next = head; // 反转指针
        head.next = null;      // 断开原有链接，防止成环
        return la; // 返回新链表的头节点
    }

    // 代码随想录反转链表
    // 双指针
    // 2025/6/29
    public listnode reverselist_1(listnode head){
        listnode prev=null;
        listnode cur=head;
        listnode temp=null;
        while (cur!=null) {
            temp=cur.next;
            cur.next=prev;
            prev=cur;
            cur=temp;
        }
        return prev;
    }

    // 递归
    // 2025/6/29
    public listnode reverselist_2(listnode head){
        return reverse_recur(null,head);
    }
    private listnode reverse_recur(listnode prev,listnode cur){
        if(cur==null){
            return prev;
        }
        listnode temp=null;
        temp=cur.next;
        cur.next=prev;
        prev=cur;
        cur=temp;
        return reverse_recur(cur, prev);
    }

    // 两两交换链表中的节点
    // 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
    // 输入：head = [1,2,3,4]
    // 输出：[2,1,4,3]
    // 2025/6/30
    public listnode swappairs(listnode head){
        // 虚拟头结点
        listnode prev=new listnode();
        prev.next=head;
        listnode cur=head;
        listnode next=null;
        listnode temp=null;
        listnode start=new listnode();
        start.next=head.next;
        while(cur!=null && cur.next!=null){
            next=cur.next;
            temp=next.next;
            next.next=cur;
            cur.next=temp;
            if(prev!=null){
                prev.next=next;
            }
            prev=cur;
            cur=temp;
        }
        head=start;
        return head.next;
    }

    // deepseek方案，两两交换链表中的节点
    // 仅标记要处理的节点以及前驱节点
    // 2025/6/30
    public listnode swappairs_1(listnode head){
        listnode dummy=new listnode();
        dummy.next=head;
        listnode prev=dummy;
        while (prev.next!=null&&prev.next.next!=null) {
            // 获取要处理的节点
            listnode first=prev.next;
            listnode second=first.next;

            // 交换节点
            first.next=second.next;
            second.next=first;
            prev.next=second;

            // 移动到下一组
            prev=first;
        }
        return dummy.next;
    }

    // 代码随想录方案，两两交换链表中的节点
    // 递归
    // 另一个方法与上面一致
    // 2025/6/30
    public listnode swappairs_2(listnode head) {
        // base case 退出提交
        if(head==null||head.next==null){
            return head;
        }
        // 获取当前节点的下一个节点
        listnode next=head.next;
        // 进行递归
        listnode newnode=swappairs_2(next.next);
        // 交换
        next.next=head;
        head.next=newnode;
        return next;
    }

    // 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
    // 输入：head = [1,2,3,4,5], n = 2
    // 输出：[1,2,3,5]
    // 2025/7/1
    public listnode removenthfromend(listnode head,int n){
        // 虚拟头结点
        listnode dummy=new listnode();
        dummy.next=head;
        removenthfromend_recursion(dummy,n);
        return dummy.next;
    }
    private int removenthfromend_recursion(listnode head,int n){
        if(head==null){
            return 0;
        }
        int count=removenthfromend_recursion(head.next,n);
        if (count == n) {
            head.next = head.next.next; // 删除目标节点
        }
        count++;
        return count;
    }

    // 代码随想录删除链表的倒数第 n 个结点
    // 快慢指针
    // 2025/7/1
    public listnode removenthfromend_1(listnode head,int n){
        // 虚拟头结点
        listnode dummy=new listnode();
        listnode fastindex=dummy;
        listnode slowindex=dummy;

        // 快慢指针相差n个节点
        for(int ii=0;ii<=n;ii++){
            fastindex=fastindex.next;
        }

        while ((fastindex!=null)) {
            fastindex=fastindex.next;
            slowindex=slowindex.next;
        }

        if(slowindex!=null){
            slowindex.next=slowindex.next.next;
        }
        return dummy.next;
    }

    // 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null 。
    // 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
    // 输出：Intersected at '8'
    // 解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。
    // 从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。
    // 在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
    // 2025/7/1
    public listnode gentintersectionnode(listnode heada,listnode headb){
        // 代码破坏了链表原本的结构，这是不被允许的
        listnode result=new listnode();

        // 翻转链表
        listnode lastindexa=reverse_temp(heada);
        listnode lastindexb=reverse_temp(headb);

        // 从末位开始记录
        while(lastindexa==lastindexb){
            result=lastindexa;
            lastindexa=lastindexa.next;
            lastindexb=lastindexb.next;
        }
        return result;
    }
    private listnode reverse_temp(listnode head){
        if(head==null||head.next==null){
            return head;
        }
        listnode ret=reverse_temp(head.next);
        head.next.next=head;
        head.next=null;
        return ret;
    }

    // deepseek方案，返回两个单链表相交的起始节点
    // 方法很好
    // 2025/7/1
    public listnode gentintersectionnode_1(listnode heada,listnode headb){
        listnode p=heada,q=headb;
        while(p!=q){
            p=(p==null)?headb:p.next;
            q=(q==null)?heada:q.next;
        }
        return p;
    }

    // 环形链表II
    // 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
    // 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 
    // 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    // 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
    // 不允许修改 链表。
    // 输入：head = [3,2,0,-4], pos = 1
    // 输出：返回索引为 1 的链表节点
    // 解释：链表中有一个环，其尾部连接到第二个节点。
    // 2025/7/1
    public listnode detectcycle(listnode head){
        // 没想到
        listnode result=head;
        return result;
    }

    // deepseek环形链表II
    // 检测圆环是否存在用快慢指针
    // 2025/7/1
    public listnode detectcycle_1(listnode head){
        if(head==null||head.next==null){
            return null;
        }

        // 检测圆环是否存在
        listnode fastindex=head;
        listnode slowindex=head;
        while (true) {
            // 无环
            if(fastindex==null||fastindex.next==null){
                return null;
            }

            // 不存在
            slowindex=slowindex.next;
            fastindex=fastindex.next.next;
            if(slowindex==fastindex){
                break;// 有环
            }
        }

        // 寻找环入口
        listnode ptr1=head;// 起始点
        listnode ptr2=fastindex;// 相遇点
        while (ptr1!=ptr2) {
            ptr1=ptr1.next;
            ptr2=ptr2.next;
        }
        return ptr1;
    }

    // 代码随想录环形链表II
    // 检测圆环是否存在用快慢指针
    // 2025/7/1
    public listnode detectCycle_2(listnode head) {
        listnode slow = head;
        listnode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {// 有环
                listnode index1 = fast;
                listnode index2 = head;
                // 两个指针，从头结点和相遇结点，各走一步，直到相遇，相遇点即为环入口
                while (index1 != index2) {
                    index1 = index1.next;
                    index2 = index2.next;
                }
                return index1;
            }
        }
        return null;
    }

    // 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
    // 输入: s = "anagram", t = "nagaram"
    // 输出: true
    // 2025/7/4
    public boolean isanagram(String s,String t){
        int[] record=new int[26];// 记录字符出现次数
        // s的各个元素数
        for(int ii=0;ii<s.length();ii++){
            record[s.charAt(ii)-'a']++;
        }
        // 减去t的各个元素个数
        for(int ii=0;ii<t.length();ii++){
            record[t.charAt(ii)-'a']--;
        }
        // 判断不为0情况
        for(int count:record){
            if(count!=0){
                return false;
            }
        }
        return true;
    }

    // 给定两个数组 nums1 和 nums2 ，返回 它们的 交集 。
    // 输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序 。
    // 输入：nums1 = [1,2,2,1], nums2 = [2,2]
    // 输出：[2]
    // 2025/7/4
    public int[] intersection(int[] nums1,int[] nums2){
        Stack<Integer> stackboth=new Stack<>();

        // 重复元素入栈
        for(int ii=0;ii<nums1.length;ii++){
            for(int jj=0;jj<nums2.length;jj++){
                if(nums1[ii]==nums2[jj]){
                    stackboth.push(nums1[ii]);
                }
            }
        }

        // 栈去重
        // 使用LinkedHashSet去重
        Set<Integer> set = new LinkedHashSet<>();
        set.addAll(stackboth); // 将栈中的元素添加到set中，自动去重
        stackboth.clear(); // 清空栈
        for (Integer num : set) {
            stackboth.push(num); // 将set中的元素重新添加到栈中
        }

        // 栈转换成数组
        // 使用toArray()方法转换
        Integer[] temp = stackboth.toArray(new Integer[0]); // 注意：需要传递一个泛型数组以匹配返回的数组类型
       
        // 格式化输出
        int[] result = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            result[i] = temp[i];
        }
        return result;
    }

    // 代码随想录两个数组的交集
    // 哈希数据结构hashset
    // 2025/7/5
    public int[] intersection_1(int[] nums1,int[] nums2){
        // 空集判断
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }

        // 唯一set
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> resSet = new HashSet<>();
        //遍历数组1
        for (int i : nums1) {
            set1.add(i);
        }

        //遍历数组2的过程中判断哈希表中是否存在该元素
        for (int i : nums2) {
            if (set1.contains(i)) {
                resSet.add(i);
            }
        }

        // 格式化输出
        return resSet.stream().mapToInt(Integer::intValue).toArray();
    }

    // 代码随想录两个数组的交集
    // hash数组
    // 要求知道数据长度上限
    // ArrayList 类是一个可以动态修改的数组，与普通数组的区别就是它是没有固定大小的限制，我们可以添加或删除元素。
    // 2025/7/5
    public int[] javaintersection_2(int[] nums1,int[] nums2){
        // 统计数值出现次数
        int[] hash1 = new int[1002];
        int[] hash2 = new int[1002];
        for(int i : nums1)
            hash1[i]++;
        for(int i : nums2)
            hash2[i]++;
        
        // 统计交集
         List<Integer> resList = new ArrayList<>();
        for(int i = 0; i < 1002; i++)
            if(hash1[i] > 0 && hash2[i] > 0)
                resList.add(i);
        
        // 转换成数组
        int index = 0;
        int res[] = new int[resList.size()];
        for(int i : resList)
            res[index++] = i;
        return res;
    }

    // 编写一个算法来判断一个数 n 是不是快乐数。
    // 「快乐数」 定义为：
    // 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
    // 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
    // 如果这个过程 结果为 1，那么这个数就是快乐数。
    // 如果 n 是 快乐数 就返回 true ；不是，则返回 false 。
    // 输入：n = 19
    // 输出：true
    // 解释：
    // 12 + 92 = 82
    // 82 + 22 = 68
    // 62 + 82 = 100
    // 12 + 02 + 02 = 1
    // 2025/7/5
    public boolean ishappy(int n){
        int nin=n;
        int count=0;

        // 循环判断
        while(true){
            //  判断几位数
            int M=(int)Math.log10(nin)+1;
            int sum=0;
            for(int ii=0;ii<M;ii++){
                int temp=(int)Math.pow(10,ii);
                sum=sum+(int)Math.pow(nin/temp%10,2);
            }
            nin=sum;
            count++;
            if(sum==1){
                return true;
            }

            // 循环过多退出
            if(count>1000){
                return false;
            }
        }
    }

    // 代码随想录快乐数
    // 哈希表
    // 2025/7/5
    public boolean ishappy_1(int n){
        // 考虑数字是否出现过，就使用哈希法
        Set<Integer> record=new HashSet<>();
        while(n!=1&&!record.contains(n)){
            record.add(n);
            n=getnextnumber(n);
        }
        return n==1;
    }
    private int getnextnumber(int n){
        int sum=0;
        while(n>0){
            int digit=n%10; // 获取最后一位数字
            sum+=digit*digit; // 计算平方和
            n/=10; // 去掉最后一位数字
        }
        return sum; // 返回平方和
    }

    // 两数之和
    // 给定一个整数数组 nums 和一个整数目标值 target，
    // 请你在该数组中找出 和为目标值 target  的那 两个 整数，并返回它们的数组下标。
    // 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
    // 你可以按任意顺序返回答案。
    // 输入：nums = [2,7,11,15], target = 9
    // 输出：[0,1]
    // 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
    // 2025/7/5
    public int[] twosum(int[] nums,int target){
        // 暴力搜索
        for(int ii=0;ii<nums.length;ii++){
            for(int jj=0;jj<nums.length;jj++){
                if(ii!=jj&&nums[ii]+nums[jj]==target){
                    return new int[]{ii,jj};
                }
            }
        }
        return new int[0]; // 如果没有找到，返回空数组
    }

    // 代码随想录两数之和
    // 哈希表
    // 2025/7/5
    public int[] twosum_1(int[] nums,int target){
        Map<Integer,Integer> map=new HashMap<>();
        for(int ii=0;ii<nums.length;ii++){
            // 匹配的值
            int complement=target-nums[ii];
            
            // 哈希表中存在
            if(map.containsKey(complement)){
                return new int[]{map.get(complement),ii};
            }

            // 记录
            map.put(nums[ii],ii);
        }
        return new int[0]; // 如果没有找到，返回空数组
    }

    // 代码随想录两数之和
    // 双指针
    // 2025/7/5
    public int[] twosum_2(int[] nums,int target){
        int[] result=new int[2];
        int val1=0;
        int val2=0;
        // 拷贝
        int[] copynums=Arrays.copyOf(nums, nums.length);

        // 排序
        Arrays.sort(copynums);

        // 双指针搜索
        for(int ii=0,jj=nums.length-1;ii<jj;){
            if(copynums[ii]+copynums[jj]==target){
                val1=copynums[ii];
                val2=copynums[jj];
                break;
            }else if(copynums[ii] + copynums[jj] <target){
                ii++;
            }else{
                jj--;
            }
        }

        // 由于排序重新搜索
        for(int ii=0;ii<nums.length;ii++){
            if(nums[ii]==val1){
                result[0]=ii;
                break;
            }
        }
        for(int ii=0;ii<nums.length;ii++){
            if(nums[ii]==val2&&ii!=result[0]){
                result[1]=ii;
                break;
            }
        }
        return result;
    }

    // 给你四个整数数组 nums1、nums2、nums3 和 nums4 
    // 数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
    // 0 <= i, j, k, l < n
    // nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
    // 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
    // 输出：2
    // 解释：
    // 两个元组如下：
    // 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
    // 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
    // 2025/7/5
    public int foursumcount(int[] nums1,int[] nums2,int[] nums3,int[] nums4){
        // 暴力搜索
        // int count=0;
        // for(int ii=0;ii<nums1.length;ii++){
        //     for(int jj=0;jj<nums2.length;jj++){
        //         for(int kk=0;kk<nums3.length;kk++){
        //             for(int ll=0;ll<nums4.length;ll++){
        //                 if(nums1[ii]+nums2[jj]+nums3[kk]+nums4[ll]==0){
        //                     count++;
        //                 }
        //             }
        //         }
        //     }
        // }
        // return count; // 返回满足条件的元组数量

        // // 哈希表
        // Map<Integer,Integer> map1=new HashMap<>();
        // Map<Integer,Integer> map2=new HashMap<>();
        // Map<Integer,Integer> map3=new HashMap<>();
        // Map<Integer,Integer> map4=new HashMap<>();

        // // 数组值与个数映射
        // for(int ii=0;ii<nums1.length;ii++){
        //     if(map1.containsKey(nums1[ii])){
        //         map1.compute(nums1[ii],(key,val)->val+1 );
        //     }else{
        //         map1.put(nums1[ii], 1);
        //     }
        // }
        // for(int ii=0;ii<nums2.length;ii++){
        //     if(map2.containsKey(nums2[ii])){
        //         map2.compute(nums2[ii],(key,val)->val+1 );
        //     }else{
        //         map2.put(nums2[ii], 1);
        //     }
        // }
        // for(int ii=0;ii<nums3.length;ii++){
        //     if(map3.containsKey(nums3[ii])){
        //         map3.compute(nums3[ii],(key,val)->val+1 );
        //     }else{
        //         map3.put(nums3[ii], 1);
        //     }
        // }
        // for(int ii=0;ii<nums4.length;ii++){
        //     if(map4.containsKey(nums4[ii])){
        //         map4.compute(nums4[ii],(key,val)->val+1 );
        //     }else{
        //         map4.put(nums4[ii], 1);
        //     }
        // }

        // // 计数
        // AtomicInteger count = new AtomicInteger(0);
        // map1.forEach((key1,val1)->{
        //     map2.forEach((key2,val2)->{
        //         map3.forEach((key3,val3)->{
        //             map4.forEach((key4,val4)->{
        //                 if(key1+key2+key3+key4==0){
        //                      count.addAndGet(val1 * val2 * val3 * val4);
        //                 }
        //             });
        //         });
        //     });
        // });
        // return count.get(); // 返回满足条件的元组数量

        // 两两优化，O(n^2)
        Map<Integer,Integer> mapsum12=new HashMap<>();
        Map<Integer,Integer> mapsum34=new HashMap<>();
        for(int ii=0;ii<nums1.length;ii++){
            for(int jj=0;jj<nums2.length;jj++){
                int sum=nums1[ii]+nums2[jj];
                // 记录和的个数，如果没有则初始化为1
                mapsum12.put(sum,mapsum12.getOrDefault(sum,0)+1);
            }
        }
        for(int ii=0;ii<nums3.length;ii++){
            for(int jj=0;jj<nums4.length;jj++){
                int sum=nums3[ii]+nums4[jj];
                // 记录和的个数，如果没有则初始化为1
                mapsum34.put(sum,mapsum34.getOrDefault(sum,0)+1);
            }
        }
        AtomicInteger count= new AtomicInteger(0);
        mapsum12.forEach((key1,val1)->{
            if(mapsum34.containsKey(-key1)){
                // 如果存在，则计算个数
                count.addAndGet(val1 * mapsum34.get(-key1));
            }
        });
        return count.get(); // 返回满足条件的元组数量
    }

    // 代码随想录四数之和
    // 哈希表，同样也是两两求
    // 2025/7/5
    public int foursumcount_1(int[] nums1,int[] nums2,int[] nums3,int[] nums4){
        // 两两求和
        Map<Integer,Integer> map=new HashMap<>();
        for(int ii=0;ii<nums1.length;ii++){
            for(int jj=0;jj<nums2.length;jj++){
                int sum=nums1[ii]+nums2[jj];
                map.put(sum,map.getOrDefault(sum,0)+1);
            }
        }

        // 计数
        int count=0;
        for(int ii=0;ii<nums3.length;ii++){
            for(int jj=0;jj<nums4.length;jj++){
                int sum=-(nums3[ii]+nums4[jj]);
                count+=map.getOrDefault(sum,0);
            }
        }
        return count;
    }

    // 救赎信
    // 给你两个字符串：ransomNote 和 magazine ，判断 ransomNote 能不能由 magazine 里面的字符构成。
    // 如果可以，返回 true ；否则返回 false 。
    // magazine 中的每个字符只能在 ransomNote 中使用一次。
    // 示例 1：
    // 输入：ransomNote = "a", magazine = "b"
    // 输出：false
    // 示例 2：
    // 输入：ransomNote = "aa", magazine = "ab"
    // 输出：false
    // 示例 3：
    // 输入：ransomNote = "aa", magazine = "aab"
    // 输出：true
    // 2025/7/5
    public boolean canconstruct(String ransomnote,String magazine){
        // 统计字符出现次数
        int[] recordr=new int[26];
        int[] recordm=new int[26];
        for(int ii=0;ii<ransomnote.length();ii++){
            recordr[ransomnote.charAt(ii)-'a']++;
        }
        for(int ii=0;ii<magazine.length();ii++){
            recordm[magazine.charAt(ii)-'a']++;
        }

        // 减去magazine的字符出现次数
        for(int ii=0;ii<26;ii++){
            if(recordr[ii]>recordm[ii]){
                return false; // 如果ransomNote的字符出现次数大于magazine，则无法构成
            }
        }
        return true; // 如果所有字符都满足条件，则可以构成 
    }

    // 代码随想录救赎信
    // 和我的方法基本一致，过
    // 2025/7/5
    public boolean canconstruct_1(String ransomnote,String magazine){
        return false;
    }

    // 三数之和
    // 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j],
    // nums[k]] 满足 i != j、i != k 且 j != k ，
    // 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
    // 注意：答案中不可以包含重复的三元组。
    // 输入：nums = [-1,0,1,2,-1,-4]
    // 输出：[[-1,-1,2],[-1,0,1]]
    // 解释：
    // nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
    // nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
    // nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
    // 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
    // 注意，输出的顺序和三元组的顺序并不重要。
    // 2025/7/5
    public List<List<Integer>> threesum(int[] nums){
        // // 暴力搜索
        // List<List<Integer>> result=new ArrayList<>();
        // for(int ii=0;ii<nums.length;ii++){
        //     for(int jj=ii+1;jj<nums.length;jj++){
        //         for(int kk=jj+1;kk<nums.length;kk++){
        //             if(nums[ii]+nums[jj]+nums[kk]==0&&ii!=jj&&ii!=kk&&jj!=kk){
        //                 List<Integer> temp=new ArrayList<>();
        //                 int[] sorted = {nums[ii], nums[jj], nums[kk]};
        //                 Arrays.sort(sorted); // 排序以便去重
        //                 temp.add(sorted[0]);
        //                 temp.add(sorted[1]);
        //                 temp.add(sorted[2]);
        //                 // 去重
        //                 if(!result.contains(temp)){
        //                     result.add(temp);
        //                 }
        //             }
        //         }
        //     }
        // }
        // return result; // 返回满足条件的三元组列表
        List<List<Integer>> result=new ArrayList<>();
        return result;
    }

    // 字符串翻转
    // 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
    // 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
    // 输入：s = ["h","e","l","l","o"]
    // 输出：["o","l","l","e","h"]
    // 2025/7/20
    public void reverseString(char[] s){
        int left=0;
        int right=s.length-1;
        while(left<right){
            // 交换字符
            char temp=s[left];
            s[left]=s[right];
            s[right]=temp;
            left++;
            right--;
        }
    }

    // 字符串翻转，代码随想录
    // 异或
    // 2025/7/20
    public void reverseString_1(char[] s){
        int left=0;
        int right=s.length-1;
        while(left<right){
            // 交换字符
            s[left] ^=s[right];
            s[right] ^=s[left];
            s[left] ^=s[right];
        }
    }

    // 反转字符串II
    // 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
    // 如果剩余字符少于 k 个，则将剩余字符全部反转。
    // 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
    // 输入：s = "abcdefg", k = 2
    // 输出："bacdfeg"
    // 2025/7/20
    public String reverseStr(String s, int k) {
        char[] result=s.toCharArray(); // 将字符串转换为字符数组
        int index=0;
        int preindex=0;
        for(int ii=0;ii<s.length();ii++,index++){
            if((index+1)%(2*k)==0){
                int left=preindex;
                int right=preindex+k-1; // 计算反转的右边界
                while(left<right){
                    // 交换字符
                    char temp=result[left];
                    result[left]=result[right];
                    result[right]=temp;
                    left++;
                    right--;
                }
                preindex=index+1; // 更新前一个索引
            }
            // 剩余字符小于k处理
            if(s.length()-1-preindex<k){
                int left=preindex;
                int right=s.length()-1; // 计算反转的右边界
                while(left<right){
                    // 交换字符
                    char temp=result[left];
                    result[left]=result[right];
                    result[right]=temp;
                    left++;
                    right--;
                }
                break; // 处理完剩余字符后退出循环
            }
            // 剩余字符大于k小于2k处理
            if(s.length()-1-preindex>=k&&s.length()-1-preindex<2*k){
                int left=preindex;
                int right=preindex+k-1; // 计算反转的右边界
                while(left<right){
                    // 交换字符
                    char temp=result[left];
                    result[left]=result[right];
                    result[right]=temp;
                    left++;
                    right--;
                }
                break; // 处理完剩余字符后退出循环
            }
            
        }
        return new String(result); // 返回反转后的字符串
    }

    // 反转字符串II，代码随想录
    // 2025/7/20
    public String reverseStr_1(String s, int k) {
        StringBuffer res = new StringBuffer();
        int length = s.length();
        int start = 0;
        while (start < length) {
            // 找到k处和2k处
            StringBuffer temp = new StringBuffer();
            // 与length进行判断，如果大于length了，那就将其置为length
            int firstK = (start + k > length) ? length : start + k;
            int secondK = (start + (2 * k) > length) ? length : start + (2 * k);

            //无论start所处位置，至少会反转一次
            temp.append(s.substring(start, firstK));
            res.append(temp.reverse());

            // 如果firstK到secondK之间有元素，这些元素直接放入res里即可。
            if (firstK < secondK) { //此时剩余长度一定大于k。
                res.append(s.substring(firstK, secondK));
            }
            start += (2 * k);
        }
        return res.toString();
    }

    // 反转字符串II，代码随想录
    // 2025/7/20
    public String reverseStr_2(String s, int k) {
        char[] ch = s.toCharArray();
        for(int i = 0;i < ch.length;i += 2 * k){
            int start = i;
            // 判断尾数够不够k个来取决end指针的位置
            int end = Math.min(ch.length - 1,start + k - 1);
            while(start < end){
                
                char temp = ch[start];
                ch[start] = ch[end];
                ch[end] = temp;

                start++;
                end--;
            }
        }
        return new String(ch);
    }

    // 替换数字
    // 给定一个字符串 s，它包含小写字母和数字字符，
    // 请编写一个函数，将字符串中的字母字符保持不变，而将每个数字字符替换为number。 
    // 例如，对于输入字符串 "a1b2c3"，函数应该将其转换为 "anumberbnumbercnumber"。
    // 输入描述
    // 输入一个字符串 s,s 仅包含小写字母和数字字符。
    // 输出描述
    // 打印一个新的字符串，其中每个数字字符都被替换为了number
    // 输入示例
    // a1b2c3
    // 输出示例
    // anumberbnumbercnumber
    // 2025/7/20
    public void replaceNumberWithWord() {
        Scanner sc=new Scanner(System.in);
        String s = sc.nextLine(); // 读取输入字符串
        sc.close(); // 关闭Scanner
        StringBuffer result = new StringBuffer();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                result.append("number");
            } else {
                result.append(c);
            }
        }
        System.out.println(result.toString()); // 输出结果
    }

    // 替换数字，代码随想录
    // 2025/7/20
    public void replaceNumberWithWord_1() {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        sc.close();
        int len = s.length();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= 0 && s.charAt(i) <= '9') {
                len += 5;
            }
        }
        
        char[] ret = new char[len];
        for (int i = 0; i < s.length(); i++) {
            ret[i] = s.charAt(i);
        }
        for (int i = s.length() - 1, j = len - 1; i >= 0; i--) {
            if ('0' <= ret[i] && ret[i] <= '9') {
                ret[j--] = 'r';
                ret[j--] = 'e';
                ret[j--] = 'b';
                ret[j--] = 'm';
                ret[j--] = 'u';
                ret[j--] = 'n';
            } else {
                ret[j--] = ret[i];
            }
        }
        System.out.println(ret);
    }

    // 反转字符串中的单词
    // 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
    // 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
    // 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
    // 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。
    // 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
    // 输入：s = "the sky is blue"
    // 输出："blue is sky the"
    // 2025/7/22
    public String reverseWords(String s){
        String[] words = s.trim().split("\\s+"); // 使用正则表达式分割单词，去除多余空格
        StringBuffer result= new StringBuffer();
        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]); // 反向添加单词
            if (i != 0) {
                result.append(" "); // 添加空格，避免最后一个单词后面有空格
            }
        }
        return result.toString(); // 返回反转后的字符串
    }

    // 反转字符串中的单词，代码随想录
    // 2025/7/22
    public String reverseWords_1(String s) {
        // System.out.println("ReverseWords.reverseWords2() called with: s = [" + s + "]");
        // 1.去除首尾以及中间多余空格
        StringBuilder sb = removeSpace(s);
        // 2.反转整个字符串
        reverseString(sb, 0, sb.length() - 1);
        // 3.反转各个单词
        reverseEachWord(sb);
        return sb.toString();
    }
    private StringBuilder removeSpace(String s) {
        // System.out.println("ReverseWords.removeSpace() called with: s = [" + s + "]");
        int start = 0;
        int end = s.length() - 1;
        while (s.charAt(start) == ' ') start++;
        while (s.charAt(end) == ' ') end--;
        StringBuilder sb = new StringBuilder();
        while (start <= end) {
            char c = s.charAt(start);
            if (c != ' ' || sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            start++;
        }
        // System.out.println("ReverseWords.removeSpace returned: sb = [" + sb + "]");
        return sb;
    }
    /**
     * 反转字符串指定区间[start, end]的字符
     */
    public void reverseString(StringBuilder sb, int start, int end) {
        while (start < end) {
            char temp = sb.charAt(start);
            sb.setCharAt(start, sb.charAt(end));
            sb.setCharAt(end, temp);
            start++;
            end--;
        }
    }
    private void reverseEachWord(StringBuilder sb) {
        int start = 0;
        int end = 1;
        int n = sb.length();
        while (start < n) {
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            reverseString(sb, start, end - 1);
            start = end + 1;
            end = start + 1;
        }
    }

    // 右旋字符串
    // 字符串的右旋转操作是把字符串尾部的若干个字符转移到字符串的前面。
    // 给定一个字符串 s 和一个正整数 k，请编写一个函数，
    // 将字符串中的后面 k 个字符移到字符串的前面，实现字符串的右旋转操作。 
    // 例如，对于输入字符串 "abcdefg" 和整数 2，函数应该将其转换为 "fgabcde"
    // 输入示例
    // 2
    // abcdefg
    // 输出示例
    // fgabcde
    // 2025/7/22
    public void rightRotateString() {
        Scanner sc=new Scanner(System.in);
        int k=Integer.parseInt(sc.nextLine()); // 读取右旋转的字符数
        String s = sc.nextLine(); // 读取输入字符串
        sc.close();
        StringBuffer result=new StringBuffer();
        result.append(s.substring(s.length()-k)); // 添加后面k个字符
        result.append(s.substring(0,s.length()-k)); // 添加前面剩余的
        System.out.println(result.toString()); // 输出结果
    }

    // 找出字符串中第一个匹配项的下标
    // 给你两个字符串 haystack 和 needle ，
    // 请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标
    // （下标从 0 开始）。如果 needle 不是 haystack 的一部分，则返回  -1 。
    // 输入：haystack = "sadbutsad", needle = "sad"
    // 输出：0
    // 解释："sad" 在下标 0 和 6 处匹配。
    // 第一个匹配项的下标是 0 ，所以返回 0 。
    // 2025/7/22
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public int strStr_1(String haystack, String needle) {
        int result=-1;
        for(int ii=0;ii<=haystack.length()-needle.length();ii++){
            if(needle.equals(haystack.substring(ii,ii+needle.length()))){
                result=ii; // 返回第一个匹配项的下标
                break;
            }
        }
        return result;
    }

    // 重复的子字符串
    // 给定一个非空字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
    // 输入: s = "abab"
    // 输出: true
    // 解释: 可由子串 "ab" 重复两次构成。
    // 2025/7/25
    public boolean repeatedSubstringPattern(String s) {
        for(int len=1;len<s.length();len++){
            if(s.length()%len!=0){
                continue; // 如果长度不能被len整除，则跳过
            }
            String substring=s.substring(0,len); // 获取子串
            boolean isMatch = true; // 标记是否匹配
            for(int ii=0;ii<s.length();ii++){
                if(s.charAt(ii) != substring.charAt(ii%len)){
                    isMatch = false; // 如果字符不匹配，则标记为不匹配
                    break; // 如果子串不匹配，则跳出循环
                }
            }
            if(isMatch) {
                return true; // 只有当所有字符都匹配时才返回true
            }
        }
        return false; // 如果没有找到符合条件的子串，则返回false
    }

    // 用栈实现队列
    // 2023/7/25
    class MyQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x); // 将元素压入栈1
        }

        public int pop() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop()); // 将栈1的元素转移到栈2
                }
            }
            return stack2.pop(); // 从栈2弹出元素
        }

        public int peek() {
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop()); // 将栈1的元素转移到栈2
                }
            }
            return stack2.peek(); // 返回栈2的顶部元素
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty(); // 检查两个栈是否都为空
        }
    }

    // 用队列实现栈
    // 2023/7/25
    class MyStack {
        private Queue<Integer> queue;

        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.offer(x); // 将元素添加到队列
            for (int i = 0; i < queue.size() - 1; i++) {
                queue.offer(queue.poll()); // 将队列的前面元素移动到队列的后面
            }
        }

        public int pop() {
            return queue.poll(); // 从队列中弹出元素
        }

        public int top() {
            return queue.peek(); // 返回队列的顶部元素
        }

        public boolean empty() {
            return queue.isEmpty(); // 检查队列是否为空
        }
    }

    // 给出由小写字母组成的字符串 s，重复项删除操作会选择两个相邻且相同的字母，
    // 并删除它们。
    // 在 s 上反复执行重复项删除操作，直到无法继续删除。
    // 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
    // 输入："abbaca"
    // 输出："ca"
    // 解释：
    // 例如，在 "abbaca" 中，我们可以删除 "bb" 由于两字母相邻且相同，
    // 这是此时唯一可以执行删除操作的重复项。之后我们得到字符串 "aaca"，
    // 其中又只有 "aa" 可以执行重复项删除操作，所以最后的字符串为 "ca"。
    // 2025/7/25
    public String removeDuplicates(String s) {
        StringBuffer result = new StringBuffer();
        for (char c : s.toCharArray()) {
            if (result.length() > 0 && result.charAt(result.length() - 1) == c) {
                result.deleteCharAt(result.length() - 1); // 删除最后一个字符
            } else {
                result.append(c); // 添加当前字符
            }
        }
        return result.toString(); // 返回最终的字符串
    }

    // 逆波兰表达式求值
    // 给你一个字符串数组 tokens ，表示一个根据 逆波兰表示法 表示的算术表达式。 
    // 请你计算该表达式。返回一个表示表达式值的整数。
    // 注意：
    // 有效的算符为 '+'、'-'、'*' 和 '/' 。
    // 每个操作数（运算对象）都可以是一个整数或者另一个表达式。
    // 两个整数之间的除法总是 向零截断 。
    // 表达式中不含除零运算。
    // 输入是一个根据逆波兰表示法表示的算术表达式。
    // 答案及所有中间计算结果可以用 32 位 整数表示。
    // 输入：tokens = ["2","1","+","3","*"]
    // 输出：9
    // 2025/7/25
    




        
    



    




}