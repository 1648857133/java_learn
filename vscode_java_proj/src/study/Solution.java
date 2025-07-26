package study;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;
import java.util.ArrayList;
import java.util.HashSet;

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




















