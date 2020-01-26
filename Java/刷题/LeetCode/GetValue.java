public class GetValue{
	public static int getValue(int i){
        // case 后面没有 break，执行完一个 case 后会继续执行下一个 case 语句，直到遇到 break 或 switch 结束
        int result = 0;
        switch (i){
            case 1:
                result = result + i;
            case 2:
                result = result + i * 2;
            case 3:
                result = result + i * 3;
        }
        return result;
    }

    public static void main(String[] args) {
        int n = getValue(2);
        System.out.println(n);  // 10
    }
}