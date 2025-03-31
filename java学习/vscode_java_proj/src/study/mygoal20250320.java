package study;
public class mygoal20250320 {
    int a;
    public void test(){
        System.out.println("第一种输出");
    }
    public void test(int ...a){
        System.out.println("第二种输出");
    }

    // 构造方法
    public mygoal20250320(){
        System.out.println("构造方法");
    }

    // 构造方法重载
    public mygoal20250320(int ...a){
        System.out.println("构造方法重载");
    }

    // 测试this关键字
    public void showthis(){
        System.out.println("打印this的内容："+this);
    }

    // 通过this访问对象的属性
    public void changea(){
        this.a=10;
    }

    // 测试后private
    // 只有自身可以访问，子类不能继承，其他类不能访问
    private int ap;

    // 创建接口
    public interface AD{
        public void physicAttack();
    }

    public static void main(String[] args) {
        int values[]=new int[]{1,2,3,4,5};
        // 常规for循环
        for(int ii=0;ii<values.length;ii++){
            int each=values[ii];
            System.out.println(each);
        }

        // 增强for循环
        for(int each:values){
            System.out.println(each);
        }

        // 数组复制
        int vaules2[]=new int[5];
        System.arraycopy(values, 0, vaules2, 0, 3);
        System.out.println(vaules2[2]);

        // 二维数组
        int b[][]=new int[][]{
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        
        // 测试构造函数
        mygoal20250320 test=new mygoal20250320();
        test.showthis();

    }
}
