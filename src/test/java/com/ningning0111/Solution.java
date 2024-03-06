import java.util.Scanner;

/**
 * @Project: com.ningning0111
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/3/6 16:53
 * @Description:
 */
public class Solution {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        while (n != 0){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(a + b);
            n--;
        }
    }
}
