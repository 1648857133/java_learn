package study;

public class Mygoal20250321 {
    // 物理攻击接口
    public interface AD {
        public void physicAttack();
    }

    // 魔法攻击接口
    public interface AP{
        public void magicAttack();
    }

    // 设计英雄
    // public class ADhero extends Mygoal20250321 implements AD{
    //     @Override
    //     public void physicAttack() {
    //         System.out.println("进行物理攻击");
    //     }
    // }

    



    public static void main(String[] args){
        Item lp=new Item();
        lp.effect();

        // 测试多态
        int a=5;
        String b="5";
        System.out.println(a+b);
    }
}
