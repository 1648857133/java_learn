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
        private class listnode {
            int val;
            listnode next;
            listnode prev;
            listnode(int val){
                this.val=val;
            }
        }
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
    
    




}