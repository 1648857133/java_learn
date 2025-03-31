package study;

public class mygoal20250623 {

    public static void main(String[] args){
        String testnum="testgeneratematrix";

        switch (testnum) {
            case "testsearch":
                testsearch();
                break;
            case "testremoveelement":
                testremoveelement();
                break;
            case "testminsubarrayen":
                testminsubarrayen();
                break;
            case "testgeneratematrix":
                testgeneratematrix();
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
        // 测试二分法
        int n=4;
        mysolution test=new mysolution();
        int[][] result=test.generatematrix(n);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println(); // 换行
        }
    }
}







