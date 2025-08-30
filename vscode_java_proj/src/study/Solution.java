package study;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class Solution {
    // 二分查找
    // 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
    // 写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。
    // 你必须编写一个具有 O(log n) 时间复杂度的算法。
    // 输入: nums = [-1,0,3,5,9,12], target = 9
    // 输出: 4
    // 解释: 9 出现在 nums 中并且下标为 4
    // 2025/7/25
    public int search(int[] nums,int target){
        int left=0;
        int right=nums.length-1;
        while(left<=right){
            int mid=(right+left)/2;
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right=mid-1;
            }else{
                left=mid+1;
            }
            
        }
        return -1;
    }

    // 代码随想录
    // 2025/7/25
    public int search2(int[] nums, int target) {
        int left=0,right=nums.length;
        while(left<right){
            int mid=left+((right-left)>>1);
            if(nums[mid]==target){
                return mid;
            }else if(nums[mid]>target){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return -1;
    }

    // 移除元素
    // 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素。
    // 元素的顺序可能发生改变。然后返回 nums 中与 val 不同的元素的数量。
    // 假设 nums 中不等于 val 的元素数量为 k，要通过此题，您需要执行以下操作：
    // 更改 nums 数组，使 nums 的前 k 个元素包含不等于 val 的元素。
    // nums 的其余元素和 nums 的大小并不重要。
    // 返回 k。
    // 2025/7/25
    public int removeElement(int[] nums, int val) {
        int left=0;
        for(int right=0;right<nums.length;right++){
            if(nums[right]!=val){
                nums[left]=nums[right];
                left++;
            }else{
                continue;
            }
        }
        return left;
    }

    // 代码随想录，暴力法
    // 2025/7/25
    public int removeElement2(int[] nums, int val) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(nums[i]==val){
                for(int j=i+1;j<n;j++){
                    nums[j-1]=nums[j];
                }
                i--;      
                n--;      
            }
        }
        return n;
    }

    // 代码随想录，双向指针
    // 2025/7/25
    public int removeElement3(int[] nums, int val) {
        int left=0,right=nums.length-1;
        while (right>=0&&nums[right]==val) {
            right--;
        }
        while (left<=right) {
            if(nums[left]==val){
                nums[left]=nums[right];
                right--;
            }
            left++;
            while(right>=0&&nums[right]==val) {
                right--;
            }
        }
        return left;
    }

    // 有序数组的平方
    // 给你一个按 非递减顺序 排序的整数数组 nums，
    // 返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
    // 2025/7/25
    public int[] sortedSquares(int[] nums){
        int[] res = new int[nums.length];
        int[] temp = new int[nums.length];
        for(int ii=0;ii<nums.length;ii++){
            temp[ii]=nums[ii]*nums[ii];
        }
        int left=0;
        int right=nums.length-1;
        for(int ii=0;ii<nums.length;ii++){
            if(temp[left]>temp[right]){
                res[nums.length-1-ii]=temp[left];
                left++;
            }else{
                res[nums.length-1-ii]=temp[right];
                right--;
            }
        }
        return res;
    }

    // 有序数组的平方，代码随想录
    // 2025/7/25
    public int[] sortedSquares_1(int[] nums) {
        for(int ii=0;ii<nums.length;ii++){
            nums[ii]=nums[ii]*nums[ii];
        }
        Arrays.sort(nums);
        return nums;
    }

    // 长度最小的子数组
    // 给定一个含有 n 个正整数的数组和一个正整数 target 。
    // 找出该数组中满足其总和大于等于 target 的长度最小的子数组
    //  [numsl, numsl+1, ..., numsr-1, numsr] ，
    // 并返回其长度。如果不存在符合条件的子数组，返回 0 。
    // 输入：target = 7, nums = [2,3,1,2,4,3]
    // 输出：2
    // 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
    // 2025/7/25
    public int minSubArrayLen(int target, int[] nums) {
        int[] sum=new int[nums.length];
        int temp=0;
        for(int ii=0;ii<sum.length;ii++){
            temp=temp+nums[ii];
            sum[ii]=temp;
        }
        int minLength=Integer.MAX_VALUE;
        for(int ii=0;ii<nums.length;ii++){
            for(int jj=ii; jj<nums.length; jj++){
                if(sum[jj]-sum[ii]+nums[ii]>=target){
                    minLength=Math.min(minLength,jj-ii+1);
                    break;
                }
            }
        }
        return minLength==Integer.MAX_VALUE?0:minLength;
    }

    // 代码随想录，长度最小的子数组
    // 2025/7/25
    public int minSubArrayLen_1(int target, int[] nums) {
        int result=Integer.MAX_VALUE;
        int sum=0;
        int subLength=0;
        for(int ii=0;ii<nums.length;ii++){
            sum=0;
            for(int jj=ii;jj<nums.length;jj++){
                sum+=nums[jj];
                if(sum>=target){
                    subLength=jj-ii+1;
                    result=Math.min(result,subLength);
                    break; // 找到满足条件的子数组后，跳出内层循环
                }
            }
        }
        return result==Integer.MAX_VALUE?0:result;
    }

    // 代码随想录，滑块
    // 2025/7/25
    public int minSubArrayLen_2(int target, int[] nums) {
        int left=0;
        int sum=0;
        int result=Integer.MAX_VALUE;
        for(int right=0;right<nums.length;right++){
            sum=sum+nums[right];
            while (sum>=target){
                result=Math.min(result, right-left+1);
                sum=sum-nums[left];
                left++;
            }
        }
        return result==Integer.MAX_VALUE?0:result;
    }

    // 螺旋矩阵II
    // 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，
    // 且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix
    // 输入：n = 3
    // 输出：[[1,2,3],[8,9,4],[7,6,5]]
    // 2025/7/25
    public int[][] generateMatrix(int n) {
        rotmatrix pos=new rotmatrix();
        pos.x_pos=0;
        pos.y_pos=0;
        pos.direction='R'; // 初始方向向右
        pos.turnnum=0;
        int[][] result=new int[n][n];
        for(int count=1;count<=n*n;count++){
            result[pos.x_pos][pos.y_pos]=count;
            pos=nextPos(pos, n);
        }
        return result;
    }
    private class rotmatrix {
        int x_pos;
        int y_pos;
        char direction; // 'R', 'D', 'L', 'U'
        int turnnum;
    }
    private rotmatrix nextPos(rotmatrix pos, int n) {
        switch (pos.direction) {
            case 'R':
                if(pos.y_pos==n-pos.turnnum-1){
                    pos.direction='D';
                    pos.x_pos++;
                }else{
                    pos.y_pos++;
                }
                break;
            case 'D':
                if(pos.x_pos==n-pos.turnnum-1){
                    pos.direction='L';
                    pos.y_pos--;
                }else{
                    pos.x_pos++;
                }
                break;
            case 'L':
                if(pos.y_pos==pos.turnnum){
                    pos.direction='U';
                    pos.x_pos--;
                }else{
                    pos.y_pos--;
                }
                break;
            case 'U':
                if(pos.x_pos==pos.turnnum+1){
                    pos.direction='R';
                    pos.y_pos++;
                    pos.turnnum++;
                }else{
                    pos.x_pos--;
                }
                break;
            default:
                break;
        }
        return pos;
    }

    // 代码随想录，螺旋矩阵II
    // 2025/7/25
    public int[][] generateMatrix_1(int n) {
        int[][] nums=new int[n][n];
        int startx=0,starty=0;
        int offset=1;
        int count=1;
        int loop=1;
        int ii,jj;
        while (loop<=n/2) {
            // 顶部
            for(jj=startx;jj<n-offset;jj++){
                nums[startx][jj]=count++;
            }

            // 右侧
            for(ii=startx;ii<n-offset;ii++){
                nums[ii][jj]=count++;
            }

            // 底部
            for(;jj>starty;jj--){
                nums[ii][jj]=count++;
            }

            // 左侧
            for(;ii>startx;ii--){
                nums[ii][jj]=count++;
            }
            startx++;
            starty++;
            offset++;
            loop++;
        }
        if(n%2==1){
            nums[n/2][n/2]=count;
        }
        return nums;
    }

    // 区间和
    // 给定一个整数数组 Array，请计算该数组在每个指定区间内元素的总和。
    // 输入描述
    // 第一行输入为整数数组 Array 的长度 n，接下来 n 行，每行一个整数，表示数组的元素。
    // 随后的输入为需要计算总和的区间下标：a，b （b > = a），直至文件结束。
    // 输出描述
    // 输出每个指定区间内元素的总和。
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
    // 2025/7/25
    public void intervalSum() {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] sum=new int[n];
        int index=0;
        int temp=0;
        while (n-->0) {
            temp+=sc.nextInt();
            sum[index++]=temp;
        }
        while (sc.hasNextInt()) {
            int a=sc.nextInt();
            int b=sc.nextInt();
            System.out.println(sum[b] - (a == 0 ? 0 : sum[a - 1]));
        }
        sc.close();
    }

    // 开发商购买土地
    // 在一个城市区域内，被划分成了n * m个连续的区块，每个区块都拥有不同的权值，
    // 代表着其土地价值。目前，有两家开发公司，A 公司和 B 公司，希望购买这个城市区域的土地。 
    // 现在，需要将这个城市区域的所有区块分配给 A 公司和 B 公司。
    // 然而，由于城市规划的限制，只允许将区域按横向或纵向划分成两个子区域，
    // 而且每个子区域都必须包含一个或多个区块。 为了确保公平竞争，你需要找到一种分配方式，
    // 使得 A 公司和 B 公司各自的子区域内的土地总价值之差最小。 
    // 注意：区块不可再分。
    // 输入描述
    // 第一行输入两个正整数，代表 n 和 m。 
    // 接下来的 n 行，每行输出 m 个正整数。
    // 输出描述
    // 请输出一个整数，代表两个子区域内土地总价值之间的最小差距。
    // 输入示例
    // 3 3
    // 1 2 3
    // 2 1 3
    // 1 2 3
    // 输出示例
    // 0
    // 2025/7/25
    public void landPurchase() {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int[][] land = new int[n][m];
        for(int ii=0;ii<n;ii++){
            for(int jj=0;jj<m;jj++){
                land[ii][jj]=sc.nextInt();
            }
        }
        sc.close();

        // 行列和
        int[] rowSum = new int[n];
        int[] colSum = new int[m];
        for(int ii=0,rowtemp=0;ii<n;ii++){
            for(int jj=0;jj<m;jj++){
                rowtemp=rowtemp+land[ii][jj];
                rowSum[ii]=rowtemp;
            }
        }
        for(int jj=0,coltemp=0;jj<m;jj++){
            for(int ii=0;ii<n;ii++){
                coltemp=coltemp+land[ii][jj];
                colSum[jj]=coltemp;
            }
        }

        // 遍历
        int minDiff = Integer.MAX_VALUE;
        for(int ii=0;ii<n-1;ii++){
            int difftemp=Math.abs(rowSum[ii] - (rowSum[n-1] - rowSum[ii]));
            minDiff = Math.min(minDiff, difftemp);
        }
        for(int jj=0;jj<m-1;jj++){
            int difftemp=Math.abs(colSum[jj] - (colSum[m-1] - colSum[jj]));
            minDiff = Math.min(minDiff, difftemp);
        }
        System.out.println(minDiff);
    }

    // 代码随想录，开发商购买土地
    // 2025/7/25
    public void landPurchase_1() {
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int m=sc.nextInt();
        int sum=0;
        int[][] land=new int[n][m];
        for(int ii=0;ii<n;ii++){
            for(int jj=0;jj<m;jj++){
                land[ii][jj]=sc.nextInt();
                sum+=land[ii][jj];
            }
        }
        sc.close();
        int minDiff=Integer.MAX_VALUE;
        int count=0;

        // 行切分
        for(int ii=0;ii<n;ii++){
            for(int jj=0;jj<m;jj++){
                count+=land[ii][jj];
                if(jj==m-1){
                    minDiff=Math.min(minDiff, Math.abs(sum-2*count));
                }
            }
        }

        // 列切分
        count=0;
        for(int jj=0;jj<m;jj++){
            for(int ii=0;ii<n;ii++){
                count+=land[ii][jj];
                if(ii==n-1){
                    minDiff=Math.min(minDiff, Math.abs(sum-2*count));
                }
            }
        }
        System.out.println(minDiff);
    }

    // 链表
    public class MyListNode {
        // 节点值
        int val;
        // 下一个节点
        MyListNode next;
        // 构造函数
        public MyListNode() {
        }
        public MyListNode(int val) {
            this.val = val;
        }
        public MyListNode(int val, MyListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // 移除链表元素
    // 给你一个链表的头节点 head 和一个整数 val ，
    // 请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 
    // 输入：head = [1,2,6,3,4,5,6], val = 6
    // 输出：[1,2,3,4,5]
    // 2025/7/26
    public MyListNode removeElements(MyListNode head, int val) {
        MyListNode dummy = new MyListNode();
        dummy.next = head;
        MyListNode current = dummy;
        while (current.next!=null) {
            if(current.val==val){
                current.next = current.next.next;
            }else{
                current = current.next;
            }
        }
        return dummy.next;
    }

    // 移除链表元素，代码随想录，递归
    public MyListNode removeELements_1(MyListNode head,int val){
        if(head==null){
            return head;
        }
        head.next=removeELements_1(head.next, val);
        if(head.val==val){
            return head.next;
        }
        return head;
    }

    // 设计链表
    // 你可以选择使用单链表或者双链表，设计并实现自己的链表。
    // 如果是双向链表，则还需要属性 prev 以指示链表中的上一个节点。
    // 假设链表中的所有节点下标从 0 开始。
    // 实现 MyLinkedList 类：
    // MyLinkedList() 初始化 MyLinkedList 对象。
    // int get(int index) 获取链表中下标为 index 的节点的值。如果下标无效，则返回 -1 。
    // void addAtHead(int val) 将一个值为 val 的节点插入到链表中第一个元素之前。
    // 在插入完成后，新节点会成为链表的第一个节点。
    // void addAtTail(int val) 将一个值为 val 的节点追加到链表中作为链表的最后一个元素。
    // void addAtIndex(int index, int val) 将一个值为 val 的节点插入到链表中下标为 index 的节点之前。
    // 如果 index 等于链表的长度，那么该节点会被追加到链表的末尾。如果 index 比长度更大，该节点将 不会插入 到链表中。
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
    // 2025/7/26
    class MyLinkedList{
        private int len;
        private ListNode dummy;


        private class ListNode{
            int val;
            ListNode next;
            public ListNode(){
            }
            public ListNode(int val){
                this.val=val;
            }
        }

        // 构造函数
        public MyLinkedList() {
            dummy=new ListNode();
            len=0;
        }

        // 获取下标为index的节点的值
        public int get(int index){
            if(index>=this.len||index<0){
                return -1;
            }
            ListNode cur=dummy;
            while(index>=0){
                cur=cur.next;
                index--;
            }
            return cur.val;
        }

        // 讲一个值为val的节点插入到链表第一个元素之前
        public void addAtHead(int val){
            ListNode temp=new ListNode(val);
            temp.next=dummy.next;
            dummy.next=temp;
            len++;
        }

        // 将一个值为val的节点插入到链表中作为链表的最后一个元素
        public void addAtTail(int val){
            ListNode cur=dummy;
            while(cur.next!=null){
                cur=cur.next;
            }
            ListNode temp=new ListNode(val);
            cur.next=temp;
            len++;
        }

        // 讲一个值为val的节点插入到链表中下表为index的节点之前
        // 如果等于链表的长度，那么该节点会被追加到链表的末尾
        public void addAtIndex(int index,int val){
            if(index>len){
                return;
            }else if(index==len){
                this.addAtTail(val);
                return;
            }
            ListNode cur=dummy;
            while(index>0){
                cur=cur.next;
                index--;
            }
            ListNode temp=new ListNode(val);
            temp.next=cur.next;
            cur.next=temp;
            len++;
        }

        // 删除下表为index的节点
        public void deleteAtIndex(int index){
            if(index<0||index>=len){
                return;
            }
            ListNode cur=dummy;
            while(index>0){
                cur=cur.next;
                index--;
            }
            cur.next=cur.next.next;
            len--;
        }
    }

    // 反转链表
    // 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表
    // 输入：head = [1,2,3,4,5]
    // 输出：[5,4,3,2,1]
    // 2025/7/26
    public MyListNode reverseList(MyListNode head) {
        if(head.next==null || head.next == null){
            return head;
        }
        MyListNode lastnode=reverseList(head.next);
        head.next.next=head;
        head.next=null; // 断开原来的链接
        return lastnode;
    }

    // 反转链表，代码随想录，双指针
    // 2025/7/26
    public MyListNode reverseList_1(MyListNode head) {
        MyListNode prev=null;
        MyListNode cur=head;
        MyListNode temp;
        while(cur!=null){
            temp=cur.next;
            cur.next=prev;
            prev=cur;
            cur=temp;
        }
        return prev;
    }

    // 反转链表，代码随想录，递归
    // 2025/7/26
    public MyListNode reverseList_2(MyListNode head) {
        return reverse(null,head);
    }
    private MyListNode reverse(MyListNode prev, MyListNode cur) {
        if(cur==null){
            return prev;
        }
        MyListNode temp=cur.next;
        cur.next=prev;
        return reverse(cur,temp);
    }

    // 反转链表，代码随想录，头结点
    // 2025/7/26
    public MyListNode reverseList_3(MyListNode head) {
        MyListNode dummy=new MyListNode();
        MyListNode cur=head;
        while(cur!=null){
            MyListNode temp=cur.next;
            cur.next=dummy.next;
            dummy.next=cur;
            cur=temp;
        }
        return dummy.next;
    }

    // 反转链表，代码随想录，栈
    // 2025/7/26
    public MyListNode reverseList_4(MyListNode head) {
        Stack<MyListNode> stack=new Stack<>();
        MyListNode cur=head;
        while(cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        MyListNode dummy=new MyListNode();
        cur=dummy;
        while(!stack.isEmpty()){
            MyListNode temp=stack.pop();
            temp.next = null;
            cur.next=temp;
            cur=cur.next;
        }
        return dummy.next;
    }

    // 两两交换链表中的节点
    // 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
    // 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
    // 输入：head = [1,2,3,4]
    // 输出：[2,1,4,3]
    // 2025/7/26
    public MyListNode swapPairs(MyListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        MyListNode dummy = new MyListNode();
        MyListNode cur_res = dummy;
        MyListNode cur_putin= head;
        Stack<MyListNode> stack = new Stack<>();
        while(cur_putin!=null&&cur_putin.next!=null){
            int count=2;
            while(count>0){
                stack.push(cur_putin);
                cur_putin=cur_putin.next;
                count--;
            }
            while(!stack.isEmpty()){
                MyListNode temp = stack.pop();
                temp.next = null; // 断开原来的链接
                cur_res.next = temp; // 将栈顶元素放入结果链表
                cur_res = cur_res.next; // 移动结果链表的指针
            }
        }
        if(cur_putin!=null){
            cur_putin.next = null; // 断开原来的链接
            cur_res.next=cur_putin; // 如果还有一个节点，直接放入结果链表
        }
        return dummy.next;
    }

    // 两两交换链表中的节点，代码随想录，递归
    // 2025/7/26
    public MyListNode swapPairs_1(MyListNode head) {
        if(head==null||head.next==null){
            return head;
        }
        MyListNode first=head;
        MyListNode second=head.next;
        MyListNode temp=swapPairs_1(head.next.next);
        second.next=first;
        first.next=temp;
        return second; // 返回新的头节点
    }

    // 两两交换链表中的节点，代码随想录，遍历
    // 2025/7/26
    public MyListNode swapPairs_2(MyListNode head) {
        MyListNode dummy=new MyListNode();
        dummy.next=head;
        MyListNode cur=dummy;
        while(cur.next!=null&&cur.next.next!=null){
            MyListNode first=cur.next;
            MyListNode second=cur.next.next;
            first.next=second.next;
            second.next=first;
            cur.next=second; // 将第二个节点放入结果链表
            cur=first; // 移动到下一个需要交换的节点
        }
        return dummy.next; // 返回新的头节点
    }

    // 删除链表的倒数第N个节点
    // 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
    // 输入：head = [1,2,3,4,5], n = 2
    // 输出：[1,2,3,5]
    // 2025/7/26
    public MyListNode removeNthFromEnd(MyListNode head, int n) {
        // 节点个数
        int count=0;
        MyListNode dummy=new MyListNode();
        dummy.next=head;
        MyListNode cur=dummy.next;
        while(cur!=null){
            cur=cur.next;
            count++;
        }
        if(count<n){
            return dummy.next; // 如果n大于链表长度，直接返回原链表
        }
        int target=count-n; // 目标节点的下标
        cur=dummy;
        while(target>0){
            cur=cur.next;
            target--;
        }
        cur.next=cur.next.next; // 删除目标节点
        return dummy.next; // 返回新的头节点
    }

    // 删除链表的倒数第N个节点，代码随想录，双指针
    // 2025/7/26
    public MyListNode removeNthFromEnd_1(MyListNode head, int n) {
        MyListNode dummy=new MyListNode();
        dummy.next=head;
        MyListNode fastindex=dummy;
        MyListNode slowindex=dummy;
        for(int ii=0;ii<=n;ii++){
            fastindex=fastindex.next; // 快指针先走n+1步
        }
        while(fastindex!=null){
            fastindex=fastindex.next; // 快指针和慢指针一起走
            slowindex=slowindex.next;
        }
        slowindex.next=slowindex.next.next; // 慢指针的下一个节点就是要删除的节点
        return dummy.next; // 返回新的头节点
    }

    // 删除链表的倒数第N个节点，代码随想录，递归
    // 2025/7/26
    public MyListNode removeNthFromEnd_2(MyListNode head, int n) {
        MyListNode dummy=new MyListNode();
        dummy.next=head;
        countremoveNode(dummy, n);
        return dummy.next; // 返回新的头节点
    }
    private int countremoveNode(MyListNode node, int n) {
        if(node==null){
            return 0; // 递归到链表末尾
        }
        int count=countremoveNode(node.next, n); // 递归获取链表长度
        if(count==n){ // 如果当前节点是倒数第n个节点
            node.next=node.next.next; // 删除当前节点
        }
        return count+1; // 返回当前节点的下标
    }

    // 链表香蕉
    // 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始节点。
    // 如果两个链表没有交点，返回 null 。
    // 图示两个链表在节点 c1 开始相交：
    // 输入：
    // intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
    // 输出：
    // Intersected at '8'
    // 2025/7/26
    public MyListNode getIntersectionNode(MyListNode headA, MyListNode headB) {
        MyListNode curA=headA;
        MyListNode curB=headB;
        int countA=0;
        Stack<MyListNode> stackA=new Stack<>();
        Stack<MyListNode> stackB=new Stack<>();
        while(curA!=null){
            stackA.push(curA);
            curA=curA.next;
            countA++;
        }
        while(curB!=null){
            stackB.push(curB);
            curB=curB.next;
        }
        MyListNode dummyA=new MyListNode();
        dummyA.next=headA;
        int countsame=0;
        while(!stackA.isEmpty()&& !stackB.isEmpty()){
            if(stackA.pop()==stackB.pop()){
                countsame++;
            }else{
                break;
            }
        }
        int count=countA-countsame; // 计算需要移动的节点数
        curA=dummyA;
        while(count>0){
            curA=curA.next; // 移动到相交节点
            count--;
        }
        return curA; // 返回相交节点
    }

    // 链表香蕉，代码随想录，末尾对齐
    // 2025/7/26
    public MyListNode getIntersectionNode_1(MyListNode headA, MyListNode headB) {
        MyListNode curA=headA;
        MyListNode curB=headB;
        int lenA=0,lenB=0;
        while(curA!=null){
            lenA++;
            curA=curA.next;
        }
        while(curB!=null){
            lenB++;
            curB=curB.next;
        }
        curA=lenA>lenB?headA:headB; // 长度较长的链表
        curB=lenA>lenB?headB:headA; // 长度较短的链表
        int count=lenA-lenB;
        while(count>0){
            curA=curA.next; // A链表先走差值步
            count--;
        }
        while(curA!=null&&curB!=null){
            if(curA==curB){ // 如果相等，说明找到了相交节点
                return curA;
            }
            curA=curA.next; // A链表和B链表一起走
            curB=curB.next;
        }
        return null; // 如果没有相交节点，返回null
    }

    // 链表香蕉，代码随想录，跳转链表
    // 2025/7/26
    public MyListNode getIntersectionNode_2(MyListNode headA, MyListNode headB) {
        MyListNode p1=headA;
        MyListNode p2=headB;
        while(p1!=p2){ // 当两个指针不相等时
            p1=p1==null?headB:p1.next; // 如果p1到达末尾，跳转到B链表的头节点
            p2=p2==null?headA:p2.next; // 如果p2到达末尾，跳转到A链表的头节点
        }
        return p1; // 返回相交节点，如果没有相交节点，返回null
    }

    // 环形链表II
    // 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 
    // 如果链表无环，则返回 null。
    // 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，
    // 则链表中存在环。 为了表示给定链表中的环，
    // 评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    // 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，
    // 仅仅是为了标识链表的实际情况。
    // 不允许修改 链表。
    // 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，
    // 则返回 null。
    // 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 
    // 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
    // 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
    // 不允许修改 链表。
    // 2025/7/26
    public MyListNode detectCycle(MyListNode head) {
        HashSet<MyListNode> list = new HashSet<>();
        MyListNode cur = head;
        while (cur != null) {
            if (list.contains(cur)) {
                return cur; // 如果当前节点已经在列表中，说明找到了环的入口
            }
            list.add(cur); // 将当前节点添加到列表中
            cur = cur.next; // 移动到下一个节点
        }
        return null; // 如果没有找到环，返回null
    }

    // 环形链表II，代码随想录，快慢指针
    // 相遇就是环的入口
    // 2025/7/26
    public MyListNode detectCycle_1(MyListNode head) {
        MyListNode slow=head;
        MyListNode fast=head;
        while(fast!=null&&fast.next!=null){
            slow=slow.next;
            fast=fast.next.next; // 快指针每次走两步
            if(slow==fast){ // 如果快慢指针相遇，说明有环
                MyListNode cur=head; // 从头节点开始
                while(cur!=slow){ // 找到环的入口
                    cur=cur.next;
                    slow=slow.next;
                }
                return cur; // 返回环的入口节点
            }
        }
        return null; // 如果没有环，返回null
    }

    // 有效的字母异位词
    // 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的 字母异位词。
    // 输入: s = "anagram", t = "nagaram"
    // 输出: true
    // 2025/7/27
    public boolean isAnagram(String s, String t) {
        // a-z个数
        int[] counts=new int[26];
        int[] countt=new int[26];
        for(int ii=0;ii<s.length();ii++){
            char temp=s.charAt(ii);
            counts[temp-'a']++; // 统计s中每个字母的个数
        }
        for(int ii=0;ii<t.length();ii++){
            char temp=t.charAt(ii);
            countt[temp-'a']++; // 统计t中每个字母的个数
        }
        for(int ii=0;ii<26;ii++){
            if(counts[ii]!=countt[ii]){ // 如果两个字符串中某个字母的个数不相等，返回false
                return false;
            }
        }
        return true; // 如果所有字母的个数都相等，返回true
    }

    // 有效的字母异位词，代码随想录，字典法
    // 2025/7/27
    public boolean isAnagram_1(String s, String t) {
        int[] record=new int[26];
        for(int ii=0;ii<s.length();ii++){
            record[s.charAt(ii)-'a']++; // 统计s中每个字母的个数
        }
        for(int ii=0;ii<t.length();ii++){
            record[t.charAt(ii)-'a']--; // 统计t中每个字母的个数
        }
        for(int count:record){
            if(count!=0){ // 如果有某个字母的个数不为0，说明不是字母异位词
                return false;
            }
        }
        return true; // 如果所有字母的个数都为0，说明是字母异位词
    }

    // 两个数组的交集
    // 给定两个数组 nums1 和 nums2 ，返回 它们的 交集 。
    // 输出结果中的每个元素一定是 唯一 的。我们可以 不考虑输出结果的顺序
    // 输入：nums1 = [1,2,2,1], nums2 = [2,2]
    // 输出：[2]
    // 2025/7/27
    public int[] intersection(int[] nums1, int[] nums2) {
        // nums1加入哈希表，nums2进行查询
        Set<Integer> hashset = new HashSet<>();
        for(int num:nums1){
            hashset.add(num); // 将nums1中的元素加入哈希表
        }
        Set<Integer> result = new HashSet<>();
        for(int nums:nums2){
            if(hashset.contains(nums)){ // 如果nums2中的元素在哈希表中存在
                result.add(nums); // 将该元素加入结果列表
            }
        }
        Object[] resultArray = result.toArray(); // 将结果列表转换为数组
        int[] resultIntArray = new int[resultArray.length];
        for(int ii=0;ii<resultArray.length;ii++){
            resultIntArray[ii]=(int)resultArray[ii]; // 将Object数组转换为int数组
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    // 两个数组的交集，代码随想录，也是HashSet
    // 2025/7/27
    public int[] intersection_1(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
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
        //方法1：将结果集合转为数组
        // return resSet.stream().mapToInt(Integer::intValue).toArray();
        // 方法2：将结果集合转为数组
        int[] res = new int[resSet.size()];
        int index = 0; 
        for (Integer i : resSet) {
            res[index++] = i; // 将结果集合中的元素放入数组
        }
        return res; // 返回结果数组
    }

    // 两个数组的交集，代码随想录，数组记录个数比较
    // 由于0 <= nums1[i], nums2[i] <= 1000，可以用数组记录实现
    // 2025/7/27
    public int[] intersection_2(int[] nums1, int[] nums2) {
        int[] hash1=new int[1002];
        int[] hash2=new int[1002];
        for(int ii:nums1){
            hash1[ii]++; // 统计nums1中每个元素的个数
        }
        for(int ii:nums2){
            hash2[ii]++; // 统计nums2中每个元素的个数
        }
        List<Integer> result = new ArrayList<>();
        for(int ii=0;ii<1002;ii++){
            if(hash1[ii]>0&&hash2[ii]>0){ // 如果两个数组中都有该元素
                result.add(ii); // 将该元素加入结果列表
            }
        }
        int[] resultArray = new int[result.size()];
        for(int ii=0;ii<result.size();ii++){
            resultArray[ii]=result.get(ii); // 将结果列表转换为数组
        }
        return resultArray; // 返回结果数组
    }

    // 快乐数
    // 写一个算法来判断一个数 n 是不是快乐数。
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
    // 2025/7/27
    public boolean isHappy(int n) {
        // 每次计算结果录入Hashset，如果contain了，退出
        Set<Integer> set = new HashSet<>();
        while (n != 1) {
            if(set.contains(n)){
                return false;
            }
            set.add(n);
            int temp=n;
            int sum=0;
            while(temp!=0){
                sum=sum+(temp%10)*(temp%10); // 计算每个位置上的数字的平方和
                temp=temp/10; // 去掉最后一位数字
            }
            n = sum; // 更新n为平方和
        }
        return true; // 如果最后结果为1，返回true
    }

    // 快乐数，代码随想录
    // 和我的方法基本一致
    // 2025/7/27
    public boolean isHappy_1(int n) {
        Set<Integer> record=new HashSet<>();
        while(n!=1&&!record.contains(n)){
            record.add(n);
            n=getNextNumber(n); // 获取下一个数字
        }
        return n==1; // 如果最后结果为1，返回true
    }
    private int getNextNumber(int n) {
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
    // 请你在该数组中找出 和为目标值 target  的那 两个 整数，
    // 并返回它们的数组下标。
    // 你可以假设每种输入只会对应一个答案，并且你不能使用两次相同的元素。
    // 你可以按任意顺序返回答案。
    // 输入：nums = [2,7,11,15], target = 9
    // 输出：[0,1]
    // 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
    // 2025/7/27
    public int[] twoSum(int[] nums, int target) {
        // 双循环遍历
        for(int ii=0;ii<nums.length;ii++){
            for(int jj=ii+1;jj<nums.length;jj++){
                if(nums[ii]+nums[jj]==target){ // 如果找到和为target的两个数
                    return new int[]{ii,jj}; // 返回它们的下标
                }
            }
        }
        return null; // 如果没有找到，返回空数组
    }

    // 两数之和，代码随想录，哈希表
    // 通过map，key为值，value位下标
    // 2025/7/27
    public int[] twoSum_1(int[] nums, int target) {
        int[] result = new int[2];
        if(nums==null||nums.length<2){
            return result; // 如果数组为空或长度小于2，返回空数组
        }
        Map<Integer, Integer> map = new HashMap<>();
        for(int ii=0;ii<nums.length;ii++){
            int temp=target-nums[ii]; // 计算需要的值
            if(map.containsKey(temp)){ // 如果哈希表中已经存在需要的值
                result[0]=map.get(temp); // 获取需要的值的下标
                result[1]=ii; // 当前下标
                return result; // 返回结果
            }
            map.put(nums[ii], ii); // 将当前值和下标加入哈希表
        }
        return result; // 如果没有找到，返回空数组
    }

    // 两数之和，代码随想录，哈希表
    // 省略了结果的先声明
    // 2025/7/27
    public int[] twoSum_2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int ii=0;ii<nums.length;ii++){
            int temp=target-nums[ii]; // 计算需要的值
            if(map.containsKey(temp)){ // 如果哈希表中已经存在需要的值
                return new int[]{map.get(temp), ii}; // 返回需要的值的下标和当前下标
            }
            map.put(nums[ii], ii); // 将当前值和下标加入哈希表
        }
        return new int[0]; // 如果没有找到，返回空数组
    }

    // 两数之和，代码随想录，排序后双指针
    // 注意两边指针移动判决
    // 2025/7/27
    public int[] twoSum_3(int[] nums, int target) {
        int[] copynums=new int[nums.length];
        System.arraycopy(nums, 0, copynums, 0, nums.length); // 复制数组
        Arrays.sort(copynums); // 对复制的数组进行排序
        int[] result = new int[2];
        for(int left=0,right=copynums.length-1;left<right;){
            if(copynums[left]+copynums[right]<target){ // 如果找到和为target的两个数
                left++;
            }else if(copynums[left]+copynums[right]>target){ // 如果和小于target
                right--; 
            }else{
                int val1=copynums[left]; // 找到和为target的两个数
                int val2=copynums[right];
                // 找到原数组中这两个数的下标
                for(int ii=0;ii<nums.length;ii++){
                    if(nums[ii]==val1){ // 如果找到第一个数
                        result[0]=ii; // 记录下标
                        break;
                    }
                }
                for(int ii=0;ii<nums.length;ii++){
                    if(nums[ii]==val2&&ii!=result[0]){ // 如果找到第二个数且下标不等于第一个数的下标
                        result[1]=ii; // 记录下标
                        break;
                    }
                }
                break;
            }
        }
        return result; // 返回结果
    }

    // 四数相加II
    // 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，
    // 数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
    // 0 <= i, j, k, l < n
    // nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
    // 输入：nums1 = [1,2], nums2 = [-2,-1], nums3 = [-1,2], nums4 = [0,2]
    // 输出：2
    // 解释：
    // 两个元组如下：
    // 1. (0, 0, 0, 1) -> nums1[0] + nums2[0] + nums3[0] + nums4[1] = 1 + (-2) + (-1) + 2 = 0
    // 2. (1, 1, 0, 0) -> nums1[1] + nums2[1] + nums3[0] + nums4[0] = 2 + (-1) + (-1) + 0 = 0
    // 2025/7/28
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // 统计nums1+num2记录为map，key两数和，val为个数
        // 统计nums3+num4记录为map，key两数和，val为个数
        Map<Integer, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        for(int ii:nums1){
            for(int jj:nums2){
                int sum=ii+jj; // 计算两数和
                map1.put(sum, map1.getOrDefault(sum, 0)+1); // 将两数和加入map1
            }
        }
        for(int ii:nums3){
            for(int jj:nums4){
                int sum=ii+jj; // 计算两数和
                map2.put(sum, map2.getOrDefault(sum, 0)+1); // 将两数和加入map2
            }
        }
        int count=0; // 统计满足条件的元组个数
        for(Integer key:map1.keySet()){
            if(map2.containsKey(-key)){ // 如果map2中存在与map1中key相反的值
                count+=map1.get(key)*map2.get(-key); // 计算满足条件的元组个数
            }
        }
        return count; // 返回满足条件的元组个数
    }

    // 四数相加II，代码随想录，
    // 同样是Hashmap，但是只用一个map实现，查找为0情况
    // 2025/7/28
    public int fourSumCount_1(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int count=0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int ii:nums1){
            for(int jj:nums2){
                int sum=ii+jj;
                map.put(sum,map.getOrDefault(sum, 0)+1);
            }
        }
        for(int ii:nums3){
            for(int jj:nums4){
                int sum=ii+jj;
                count+=map.getOrDefault(-sum, 0); // 查找map中是否存在与当前和相反的值
            }
        }
        return count; // 返回满足条件的元组个数
    }

    // 赎金信
    // 给你两个字符串：ransomNote 和 magazine ，
    // 判断 ransomNote 能不能由 magazine 里面的字符构成。
    // 如果可以，返回 true ；否则返回 false 。
    // magazine 中的每个字符只能在 ransomNote 中使用一次。
    // 输入：ransomNote = "a", magazine = "b"
    // 输出：false
    // 2025/7/28
    public boolean canConstruct(String ransomNote, String magazine) {
        // 统计magzine中在26个字母中每个出现的次数
        // 减去ransomBote中每个字符出现的次数，判断是否出现负数次数
        int[] counts = new int[26];
        for (char c : magazine.toCharArray()) {
            counts[c - 'a']++; // 统计magazine中每个字符的出现次数
        }
        for (char c : ransomNote.toCharArray()) {
            counts[c - 'a']--; // 减去ransomNote中每个字符的出现次数
            if (counts[c - 'a'] < 0) { // 如果出现负数，说明magazine中没有足够的字符
                return false; // 返回false
            }
        }
        return true; // 如果所有字符都能满足，返回true
    }

    // 赎金信，代码随想录
    // 和我的方法基本一致
    // 2025/7/28
    public boolean canConstruct_1(String ransomNote, String magazine) {
        if(ransomNote.length() > magazine.length()) {
            return false; // 如果ransomNote的长度大于magazine，直接返回false
        }
        int[] record = new int[26]; // 记录magazine中每个字符的出现次数
        for(char c: magazine.toCharArray()) {
            record[c-'a']++; // 统计magazine中每个字符的出现次数
        }
        for(char c: ransomNote.toCharArray()) {
            record[c-'a']--; // 减去ransomNote中每个字符的出现次数 
        }
        for(int i:record) {
            if(i < 0) { // 如果出现负数，说明magazine中没有足够的字符
                return false; // 返回false
            }
        }
        return true; // 如果所有字符都能满足，返回true
    }

    // 三数之和
    // 给你一个整数数组 nums ，
    // 判断是否存在三元组 [nums[i], nums[j], nums[k]] 
    // 满足 i != j、i != k 且 j != k ，
    // 同时还满足 nums[i] + nums[j] + nums[k] == 0 。
    // 请你返回所有和为 0 且不重复的三元组。
    // 注意：答案中不可以包含重复的三元组。
    // 输入：nums = [-1,0,1,2,-1,-4]
    // 输出：[[-1,-1,2],[-1,0,1]]
    // 解释：
    // nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
    // nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
    // nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
    // 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
    // 注意，输出的顺序和三元组的顺序并不重要。
    // 2025/7/28
    public List<List<Integer>> threeSum(int[] nums) {
        // 暴力搜索
        // 找到后排序，判断列表中是否存在
        List<List<Integer>> result = new ArrayList<>();
        for(int ii=0;ii<nums.length-2;ii++){
            for(int jj=ii+1;jj<nums.length-1;jj++){
                for(int kk=jj+1;kk<nums.length;kk++){
                    if(nums[ii]+nums[jj]+nums[kk]==0){ // 如果找到和为0的三元组
                        int sorted[] = {nums[ii], nums[jj], nums[kk]};
                        Arrays.sort(sorted); // 对三元组进行排序
                        List<Integer> temp = Arrays.asList(sorted[0], sorted[1], sorted[2]); // 将三元组转换为列表
                        if(!result.contains(temp)){ // 如果结果列表中不存在该三元组
                            result.add(temp); // 将该三元组加入结果列表
                        }
                    }
                }
            }
        }
        return result; // 返回结果列表
    }

    // 三数之和，代码随想录，排序+双指针
    // 排序后，ii遍历最小值，left和right指针分别指向剩余数组两边，注意左右移动
    // 2025/7/28
    public List<List<Integer>> threeSum_1(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(nums); // 对数组进行排序
        for(int ii=0;ii<nums.length-2;ii++){
            if(nums[ii]>0){ // 如果当前值大于0，说明后面的值都大于0，直接退出
                return result; // 因为数组已经排序，后面的值都大于0，不可能有和为0的三元组
            }
            if(ii>0&&nums[ii]==nums[ii-1]){ // 如果当前值和前一个值相同，跳过
                continue; // 避免重复三元组
            }
            int left=ii+1; // 左指针指向当前值的下一个位置
            int right=nums.length-1; // 右指针指向数组的最后一个位置
            while(left<right){ // 当左指针小于右指针时
                int sum=nums[ii]+nums[left]+nums[right]; // 计算三元组的和
                if(sum==0){ // 如果和为0，说明找到了一个三元组
                    result.add(Arrays.asList(nums[ii], nums[left], nums[right])); // 将三元组加入结果列表
                    while(left<right&&nums[left]==nums[left+1]){ // 左指针向右移动，跳过重复值
                        left++;
                    }
                    while(left<right&&nums[right]==nums[right-1]){ // 右指针向左移动，跳过重复值
                        right--;
                    }
                    left++; // 左指针向右移动
                    right--; // 右指针向左移动
                }else if(sum<0){ // 如果和小于0，说明需要更大的值
                    left++; // 左指针向右移动
                }else{ // 如果和大于0，说明需要更小的值
                    right--; // 右指针向左移动
                }
            }
        }
        return result; // 返回结果列表
    }

    // 三数之和，代码随想录，哈希表
    // 其实就是双指针
    // 2025/7/28
    public List<List<Integer>> threeSum_2(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(nums); // 对数组进行排序
        for(int ii=0;ii<nums.length;ii++){
            if(nums[ii]>0){
                return result; // 如果当前值大于0，说明后面的值都大于0，直接退出
            }
            if(ii>0&&nums[ii]==nums[ii-1]){
                continue; // 如果当前值和前一个值相同，跳过
            }
            Set<Integer> set = new HashSet<>(); // 用于存储已经遍历过的值
            for(int jj=ii+1;jj<nums.length;jj++){
                if(jj>ii+2&&nums[jj]==nums[jj-1]&&nums[jj-1]==nums[jj-2]){
                    continue; // 如果当前值和前一个值相同，跳过
                }
                int target=-nums[ii]-nums[jj]; // 计算需要的值
                if(set.contains(target)){ // 如果已经遍历过该值
                    result.add(Arrays.asList(nums[ii], nums[jj], target)); // 将三元组加入结果列表
                    continue; // 跳过当前值，继续下一个值
                }else{
                    set.add(nums[jj]); // 将当前值加入已遍历的值集合
                }
            }
        }
        return result; // 返回结果列表
    }

    // 四数之和
    // 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
    // 请你找出并返回满足下述全部条件且不重复的四元组 
    // [nums[a], nums[b], nums[c], nums[d]] 
    // （若两个四元组元素一一对应，则认为两个四元组重复）：
    // 0 <= a, b, c, d < n
    // a、b、c 和 d 互不相同
    // nums[a] + nums[b] + nums[c] + nums[d] == target
    // 你可以按 任意顺序 返回答案 。
    // 输入：nums = [1,0,-1,0,-2,2], target = 0
    // 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
    // 2025/7/28
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // 没想出来
        return new ArrayList<>(); // 返回空列表
    }

    // 四数之和，代码随想录，排序+双指针
    // 和三数之和类似，多了一层循环
    // ii遍历最小值，jj遍历第二小值，left和right指针分别指向剩余数组两边，注意左右移动
    // 2025/7/28
    public List<List<Integer>> fourSum_1(int[] nums, int target) {
        List<List<Integer>> result=new ArrayList<>();
        Arrays.sort(nums); // 对数组进行排序
        for(int ii=0;ii<nums.length-3;ii++){
            // 剪枝，注意负数
            if(nums[ii]>target&&nums[ii]>0){ // 如果当前值大于target且大于0，说明后面的值都大于target，直接退出
                return result; // 因为数组已经排序，后面的值都大于target，不可能有和为target的四元组
            }
            // 对nums[k]去重
            if(ii>0&&nums[ii]==nums[ii-1]){ // 如果当前值和前一个值相同，跳过
                continue; // 避免重复四元组
            }
            for(int jj=ii+1;jj<nums.length-2;jj++){
                // 剪枝
                if(nums[ii]+nums[jj]>target&&nums[ii]+nums[jj]>0){
                    break;
                }
                // 去重
                if(ii>0&&nums[jj]==nums[jj-1]){ // 如果当前值和前一个值相同，跳过
                    continue; // 避免重复四元组
                }

                // 双指针
                int left=jj+1; // 左指针指向当前值的下一个位置
                int right=nums.length-1; // 右指针指向数组的最后
                while (left<right) {
                    int sum = nums[ii] + nums[jj] + nums[left] + nums[right]; // 计算四元组的和
                    if(sum==target){
                        result.add(Arrays.asList(nums[ii],nums[jj],nums[left],nums[right])); // 将四元组加入结果列表
                        while(left<right&&nums[left]==nums[left+1]){ // 左指针向右移动，跳过重复值
                            left++;
                        }
                        while(left<right&&nums[right]==nums[right-1]){ // 右指针向左移动，跳过重复值
                            right--;
                        }
                        left++; // 左指针向右移动
                        right--; // 右指针向左移动
                    }else if(sum<target){ // 如果和小于target，说明需要更大的值
                        left++; // 左指针向右移动
                    }else{ // 如果和大于target，说明需要更小的值
                        right--; // 右指针向左移动
                    }
                }
            }
        }
        return result; // 返回结果列表
    }

    // 反转字符串
    // 编写一个函数，其作用是将输入的字符串反转过来。
    // 输入字符串以字符数组 s 的形式给出。
    // 不要给另外的数组分配额外的空间，
    // 你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
    // 输入：s = ["h","e","l","l","o"]
    // 输出：["o","l","l","e","h"]
    // 2025/7/28
    public void reverseString(char[] s) {
        // 左右指针交换
        int left = 0; // 左指针
        int right = s.length - 1; // 右指针
        while (left < right) {
            char temp = s[left]; // 交换左指针和右指针的字符
            s[left] = s[right];
            s[right] = temp;
            left++; // 左指针向右移动
            right--; // 右指针向左移动
        }
    }

    // 反转字符串，代码随想录
    // 通过异或，不需要额外空间
    // 2025/7/28
    public void reverseString_1(char[] s) {
        int left = 0; // 左指针
        int right = s.length - 1; // 右指针
        while (left < right) {
            s[left] = (char) (s[left] ^ s[right]); // 异或交换
            s[right] = (char) (s[left] ^ s[right]);
            s[left] = (char) (s[left] ^ s[right]);
            left++; // 左指针向右移动
            right--; // 右指针向左移动
        }
    }

    // 反转字符串II
    // 给定一个字符串 s 和一个整数 k，从字符串开头算起，
    // 每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
    // 如果剩余字符少于 k 个，则将剩余字符全部反转。
    // 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
    // 输入：s = "abcdefg", k = 2
    // 输出："bacdfeg"
    // 2025/7/28
    public String reverseStr(String s, int k) {
        // 转成字符数组，前k个入栈后存储，后k个直接存储
        char[] chars= s.toCharArray(); // 将字符串转换为字符数组
        char[] result = new char[chars.length]; // 将字符串转换为字符数组
        int indexc=0;
        int indexr=0;
        int count=0; // 计数器
        Stack<Character> stack = new Stack<>(); // 使用栈来存储前k个字符
        while(indexc<chars.length){
            while(count<k&&indexc<chars.length){ // 当计数器小于k且索引小于字符数组长度
                stack.push(chars[indexc]);
                indexc++; // 将前k个字符入栈
                count++; // 计数器加1
            }
            while(!stack.isEmpty()){ // 当栈不为空时
                result[indexr] = stack.pop(); // 将栈顶元素出栈并存储到结果数组中
                indexr++;
            }
            while(count<2*k&&indexc<chars.length){ // 如果计数器小于2k且索引小于字符数组长度
                result[indexr] = chars[indexc]; // 将后k个字符直接存储到结果数组中
                count++; // 计数器加1
                indexc++;
                indexr++;
            }
            count=0; // 重置计数器
        }
        return new String(result); // 将结果字符数组转换为字符串并返回
    }

    // 反转字符串II，代码随想录
    // 采用stringbuilder临时处理结果，方便增删，通过找到两个k长度块处理
    // 2025/7/28
    public String reverseStr_1(String s, int k) {
        StringBuilder result=new StringBuilder(); // 使用StringBuilder来存储结果
        int start=0;
        while(start<s.length()){
            int firstk=(start+k)>s.length()?s.length():start+k; // 计算第一个k长度块的结束位置
            int secondk=(start+2*k)>s.length()?s.length():start+2*k; // 计算第二个k长度块的结束位置
            String first=s.substring(start, firstk); // 获取第一个k长度块
            StringBuilder firstReversed=new StringBuilder(first).reverse(); // 将第一个k长度块反转
            result.append(firstReversed); // 将反转后的第一个k长度块添加到结果中
            if(firstk<secondk){
                String second=s.substring(firstk, secondk); // 获取第二个k长度块
                result.append(second); // 将第二个k长度块添加到结果中
            }
            start+=2*k; // 更新起始位置，跳过两个k长度块
        }
        return result.toString(); // 将结果转换为字符串并返回
    }

    // 反转字符串II，代码随想录
    // for循环，2k偏移
    // 2025/7/28
    public String reverseStr_2(String s, int k) {
        char[] chars = s.toCharArray(); // 将字符串转换为字符数组
        for(int ii=0;ii<chars.length;ii=ii+2*k){
            int left=ii;
            int right=Math.min(chars.length-1,left+k-1);
            while(left<right){
                char temp = chars[left]; // 交换字符
                chars[left] = chars[right];
                chars[right] = temp;
                left++; // 左指针向右移动
                right--; // 右指针向左移动
            }
        }
        return new String(chars); // 将字符数组转换为字符串并返回
    }

    // 替换数字
    // 给定一个字符串 s，它包含小写字母和数字字符，请编写一个函数，
    // 将字符串中的字母字符保持不变，而将每个数字字符替换为number。 
    // 例如，对于输入字符串 "a1b2c3"，函数应该将其转换为 "anumberbnumbercnumber"。
    // 输入示例
    // a1b2c3
    // 输出示例
    // anumberbnumbercnumber
    // 2025/7/28
    public String replaceDigits(String s) {
        // 通过stringbuilder增加
        StringBuilder result = new StringBuilder(); // 使用StringBuilder来存储结果
        for(char ii:s.toCharArray()){
            if(Character.isLetter(ii)){
                result.append(ii); // 如果是字母，直接添加到结果中
            }else{
                result.append("number");
            }
        }
        return result.toString(); // 将结果转换为字符串并返回
    }

    // 替换数字，代码随想录
    // 不使用StringBuilder，先计算出结果长度，
    // 2023/7/28
    public String replaceDigits_1(String s) {
        int count=0;
        for(char ii:s.toCharArray()){
            if(ii>='0'&&ii<='9'){ // 如果是数字
                count+=6; // 如果是数字，增加6个字符的长度
            }else{
                count++; // 如果是字母，增加1个字符的长度
            }
        }
        char[] newS=new char[count]; // 创建新的字符数组
        System.arraycopy(s.toCharArray(), 0, newS, 0, s.length());
        for(int ii=count-1,jj=s.length()-1;jj<ii;jj--,ii--){
            if(!Character.isDigit(newS[jj])){
                newS[ii]=newS[jj];
            }else{
                newS[ii]='r';
                newS[ii-1]='e';
                newS[ii-2]='b';
                newS[ii-3]='m';
                newS[ii-4]='u';
                newS[ii-5]='n';
                ii=ii-5;
            }
        }
        return new String(newS);
    }

    // 翻转字符串中的单词
    // 给你一个字符串 s ，请你反转字符串中 单词 的顺序。
    // 单词 是由非空格字符组成的字符串。s 中使用至少一个空格将字符串中的 单词 分隔开。
    // 返回 单词 顺序颠倒且 单词 之间用单个空格连接的结果字符串。
    // 注意：输入字符串 s中可能会存在前导空格、尾随空格或者单词间的多个空格。
    // 返回的结果字符串中，单词间应当仅用单个空格分隔，且不包含任何额外的空格。
    // 2025/7/28
    public String reverseWords(String s) {
        // Stringbuilder存储
        StringBuilder result = new StringBuilder(); // 使用StringBuilder来存储结果
        String[] words = s.trim().split("\\s+"); // 去除前后空格并按空格分割字符串
        for(int ii=words.length-1;ii>=0;ii--){ // 从后往前遍历单词
            if(!words[ii].isEmpty()){ // 如果单词不为空
                result.append(words[ii]); // 将单词添加到结果中
                if(ii>0){ // 如果不是最后一个单词，添加空格
                    result.append(" ");
                }
            }
        }
        return result.toString(); // 将结果转换为字符串并返回
    }

    // 翻转字符串中的单词，代码随想录
    // 2023/7/28
    public String reverseWords_1(String s) {
        // 去除空格
        StringBuilder sb = removeSpace(s); // 使用StringBuilder来存储结果
        // 反转整个字符串
        sb.reverse(); // 反转字符串
        // 反转每个单词
        reverseEachWord(sb);
        return sb.toString(); // 将结果转换为字符串并返回
    }
    private StringBuilder removeSpace(String s) {
        int left=0;
        int right=s.length()-1;
        while(s.charAt(left)==' '){
            left++; // 去除前导空格
        }
        while(s.charAt(right)==' '){
            right--; // 去除尾随空格
        }
        StringBuilder sb = new StringBuilder(); // 创建StringBuilder来存储结果
        while(left<=right){
            char c= s.charAt(left); // 获取当前字符
            if(c!=' '||sb.charAt(sb.length()-1)!=' '){ // 如果当前字符不是空格
                sb.append(c); // 添加到结果中
            }
            left++; // 左指针向右移动
        }
        return sb;
    }
    private void reverseEachWord(StringBuilder sb) {
        int left=0;
        int right=0;
        while(right<sb.length()){
            while(right<sb.length()&&sb.charAt(right)!=' '){ // 找到单词的结束位置
                right++; // 右指针向右移动
            }
            reverseword(sb, left, right-1); // 反转单词
            left=right+1; // 更新左指针，跳过空格
            right=left; // 更新右指针
        }
    }
    private void reverseword(StringBuilder sb, int left, int right) {
        while(left<right){ // 反转单词
            char temp = sb.charAt(left); // 交换字符
            sb.setCharAt(left, sb.charAt(right));
            sb.setCharAt(right, temp);
            left++; // 左指针向右移动
            right--; // 右指针向左移动
        }
    }

    // 右旋字符串
    // 字符串的右旋转操作是把字符串尾部的若干个字符转移到字符串的前面。
    // 给定一个字符串 s 和一个正整数 k，请编写一个函数，
    // 将字符串中的后面 k 个字符移到字符串的前面，实现字符串的右旋转操作。 
    // 例如，对于输入字符串 "abcdefg" 和整数 2，函数应该将其转换为 "fgabcde"。
    // 2025/7/28
    public String rightRotateString(String s, int n) {
        StringBuilder result = new StringBuilder(); // 使用StringBuilder来存储结果
        result.append(s.substring(n)); // 将前面的部分添加到结果中
        result.append(s.substring(0, n)); // 将后面的部分添加到结果中
        return result.toString(); // 将结果转换为字符串并返回
    }

    // 右旋字符串，代码随想录
    // 先整体反转，再分成两部分分别反转
    // 2025/7/28
    public String rightRotateString_1(String s, int n) {
        char[] chars = s.toCharArray(); // 将字符串转换为字符数组
        reverseString(chars,0, chars.length-1); // 反转整个字符串
        reverseString(chars, 0, n-1); // 反转前n个
        reverseString(chars, n, chars.length-1); // 反转后面的部分
        return new String(chars); // 将字符数组转换为字符串并返回
    }
    private void reverseString(char[] chars, int left, int right) {
        while(left<right){ // 反转字符数组
            char temp = chars[left]; // 交换字符
            chars[left] = chars[right];
            chars[right] = temp;
            left++; // 左指针向右移动
            right--; // 右指针向左移动
        }
    }

    // 实现 strStr()
    // 给你两个字符串 haystack 和 needle ，
    // 请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
    // 如果 needle 不是 haystack 的一部分，则返回  -1 。
    // 输入：haystack = "sadbutsad", needle = "sad"
    // 输出：0
    // 解释："sad" 在下标 0 和 6 处匹配。
    // 第一个匹配项的下标是 0 ，所以返回 0 。
    // 2025/7/28
    public int strStr(String haystack, String needle) {
        int result = -1; // 初始化结果为-1
        for(int ii=0;ii<haystack.length()-needle.length()+1;ii++){ // 遍历haystack字符串
            if(haystack.substring(ii, ii+needle.length()).equals(needle)){ // 如果找到匹配的子串
                result = ii; // 记录下标
                break; // 找到后退出循环
            }
        }
        return result; // 返回结果
    }

    // 实现 strStr()，代码随想录
    // KMP算法，先计算next数组，再进行匹配
    // 2025/7/30
    public int strStr_1(String haystack, String needle) {
        if(needle.isEmpty()) { // 如果needle为空字符串
            return 0; // 返回0
        }
        int[] next = getNext(needle); // 获取needle的next数组
        for(int ii=0,jj=0;ii<haystack.length();){ // 遍历haystack字符串
            if(jj==needle.length()){ // 如果jj指针已经到达needle的末尾
                return ii-jj; // 返回匹配的起始位置
            }
            if(haystack.charAt(ii)==needle.charAt(jj)){ // 如果当前字符匹配
                ii++; // haystack指针向后移动
                jj++; // needle指针向后移动
            }else if(jj>0){ // 如果当前字符不匹配且jj指针大于0
                jj = next[jj-1]; // 回退到next数组中记录的位置
            }else{ // 如果jj指针为0
                ii++; // haystack指针向后移动
            }
        }
        return -1; // 如果没有找到匹配的子串，返回-1
    }
    private int[] getNext(String needle) {
        int[] next=new int[needle.length()]; // 创建next数组
        next[0] = 0; // 第一个字符的next值为0
        int jj=0;
        for(int ii=1;ii<needle.length();ii++){
            while(jj>0&&needle.charAt(ii)!=needle.charAt(jj)){ // 如果当前字符不匹配
                jj = next[jj-1]; // 回退到next数组中记录的位置
            }
            if(needle.charAt(ii)==needle.charAt(jj)){ // 如果当前字符匹配
                jj++; // jj指针向后移动
            }
            next[ii] = jj; // 记录当前字符的next值
        }
        return next; // 返回next数组
    }





















    // 测试代码
    public static void main(String[] args) {
        String testname="testgenerateMatrix";
        switch (testname) {
            case "testsearch":
                testsearch(); // 测试二分法
                break;
            case "testminSubArrayLen":
                testminSubArrayLen(); // 测试长度最小的子数组
                break;
            case "testgenerateMatrix":
                testgenerateMatrix(); // 测试螺旋矩阵II
                break;
            default:
                break;
        }

    }
    // 测试二分法
    private static void testsearch() {
        int[] nums = {1,2,3};
        int target = 3;
        Solution solution = new Solution();
        System.out.println(solution.search(nums, target));
    }
    // 测试长度最小的子数组
    private static void testminSubArrayLen() {
        int[] nums = {2,3,1,2,4,3};
        int target = 7;
        Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(target, nums));
    }
    // 测试螺旋矩阵II
    private static void testgenerateMatrix() {
        int n = 4;
        Solution solution = new Solution();
        int[][] result = solution.generateMatrix(n);
        for (int[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
    
}




















