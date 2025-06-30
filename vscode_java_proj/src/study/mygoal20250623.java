package study;

import study.mysolution.listnode;

public class mygoal20250623 {

    public static void main(String[] args){
        String testnum="testswappairs";

        switch (testnum) {
            case "testsearch":
                testsearch();// 二分法
                break;
            case "testremoveelement":
                testremoveelement();// 移除元素
                break;
            case "testminsubarrayen":
                testminsubarrayen();// 长度最小子数组
                break;
            case "testgeneratematrix":
                testgeneratematrix();// 螺旋矩阵
                break;
            case "testintersum":
                testintersum();// 区间和
                break;
            case "testbuyingland":
                testbuyingland();// 买土地
                break;
            case "testremovelistelement":
                testremovelistelement();// 移除链表元素
                break;
            case "testmylinkedlist_single":
                testmylinkedlist_single();// 测试链表操作
                break;
            case "testswappairs":
                testswappairs();// 测试凉凉交换链表节点
                break;
            default:
                break;
        }

    }

    private static void testsearch(){
        // 测试二分法
        int[] nums = {-1,0,3,5,9,12};
        int target = 9 ;
        mysolution test=new mysolution();
        System.out.println(test.search(nums, target));
    }

    private static void testremoveelement(){
        // 测试移除元素
        int[] nums = {3,2,3,3};
        int val = 3 ;
        mysolution test=new mysolution();
        System.out.println(test.removeelement(nums, val));
    }

    private static void testminsubarrayen(){
        // 长度最小子数组
        int[] nums = {1,2,3,4,5};
        int s = 15 ;
        mysolution test=new mysolution();
        System.out.println(test.minsubarrayen(s,nums));
    }

    private static void testgeneratematrix(){
        // 螺旋矩阵
        int n=5;
        mysolution test=new mysolution();
        int[][] result=test.generatematrix_1(n);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println(); // 换行
        }
    }

    public static void testintersum(){
        // 区间和
        mysolution test=new mysolution();
        test.intersum();
    }

    public static void testbuyingland(){
        // 开发商买土地
        mysolution test=new mysolution();
        test.buyingland();
    }

    public static void testremovelistelement(){
        // 移除链表元素
        mysolution test=new mysolution();
        int[] vals={1,2,1};
        int val=1;
        mysolution.listnode listhead=test.new listnode(vals[0]);
        mysolution.listnode listcurrent=listhead;
        for(int ii=1;ii<vals.length;ii++){
            mysolution.listnode temp=test.new listnode(vals[ii]);
            listcurrent.next=temp;
            listcurrent=temp;
        }

        mysolution.listnode listresult=test.removelistelement(listhead,val);
        listcurrent=listresult;
        while (listcurrent!=null) {
            System.out.println(listcurrent.val);
            listcurrent=listcurrent.next;

        }
    }

    public static void testmylinkedlist_single(){
        // 测试链表操作
        mysolution test=new mysolution();
        mysolution.mylinkedlist_single myLinkedList = test.new mylinkedlist_single();
        myLinkedList.addAtHead(7);
        myLinkedList.addAtHead(2);
        myLinkedList.addAtHead(1);
        myLinkedList.addAtIndex(3, 0);    
        myLinkedList.deleteAtIndex(2);    
        myLinkedList.printlist();
    }

    public static void testswappairs(){
        // 测试两两交换链表中的节点
        mysolution test=new mysolution();
        mysolution.mylinkedlist_single mylinklist = test.new mylinkedlist_single();
        mylinklist.addAtTail(1);
        mylinklist.addAtTail(2);
        mylinklist.addAtTail(3);
        mylinklist.addAtTail(4);
        mylinklist.addAtTail(5);
        listnode resultlistnode=test.swappairs_2(mylinklist.gethead().next);
        while(resultlistnode!=null){
            System.err.println(resultlistnode.val);
            resultlistnode=resultlistnode.next;
        }
    }

    
}







