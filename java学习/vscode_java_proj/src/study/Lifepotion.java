package study;

// 重写物品
public class Lifepotion extends Item{
    @Override
    public void effect(){
        System.out.println("血瓶使用后，可以回血");
    }
} 
