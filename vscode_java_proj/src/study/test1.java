package study;
public class test1 {
    // 注意类的第一个字母要大写
    String name; //姓名
    float hp; //血量
    float armor; //护甲
    int moveSpeed; //移动速度

    void keng(){
        System.out.println("坑队友");
    }

    float getarmor(){
        return armor;
    }

    // 学习数据类型
    byte b=1;
    short s=2;
    int i=300;
    long l=400;
    char c='z';
    float f=1.5f;

    // 联系
    float l1=3.14f;
    double l2=2.769343;
    int l3=365;
    char l4='吃';
    boolean l5=true;

    // 测试整型
    long v1=26L;
    int v2=0x321;
    int v3=0b1110;
    int v4=012;
     
    public static void main(String[] args) {
        test1 garen =  new test1();
        garen.name = "盖伦";
        garen.hp = 616.28f;
        garen.armor = 27.536f;
        garen.moveSpeed = 350;
         
        test1 teemo =  new test1();
        teemo.name = "提莫";
        teemo.hp = 383f;
        teemo.armor = 14f;
        teemo.moveSpeed = 330;
    }  
}
