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

        // 虚拟头结点
        private class listnode {
            int val;
            listnode next;
            listnode(int val){
                this.val=val;
            }
        }
        private listnode head;
        private int size;

        // 初始化MyLinkedList 对象
        public mylinkedlist_single(){
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
            head.next=temp;
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








}