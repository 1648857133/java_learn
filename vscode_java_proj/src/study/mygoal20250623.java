package study;

public class mygoal20250623 {

    public static void main(String[] args){
        String testnum="testbuyingland";

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
        // 区间和
        mysolution test=new mysolution();
        test.buyingland();
    }
}







