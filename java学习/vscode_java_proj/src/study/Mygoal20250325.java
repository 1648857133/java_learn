package study;
import java.util.Arrays;

public class Mygoal20250325 {

    public static int removeElement(int[] nums, int val) {
        int count=0;
        for(int ii=0;ii<nums.length;ii++){
            if(nums[ii]==val){
                for(int jj=ii+1;jj<nums.length-count;jj++){
                    int temp=nums[jj];
                    nums[jj-1]=temp;
                }
                nums[nums.length-count-1]=0;
                count=count+1;
                ii--;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] nums={0,1,2,2,3,0,4,2};
        int val=2;
        System.out.println(removeElement(nums, val));
        System.out.println(Arrays.toString(nums));
    }
    
}
