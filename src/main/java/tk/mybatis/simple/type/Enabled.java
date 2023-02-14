package tk.mybatis.simple.type;

/**
 * @author mwstart
 * @create 2023-02-13-16:48
 */
public enum Enabled {
    disabled(0), //禁用
    enabled(1);  //启用

    private final int value;

    Enabled(int value) {
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
