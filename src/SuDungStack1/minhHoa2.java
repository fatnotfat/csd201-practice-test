//Tính trị biểu thức số nguyên dạng hậu tố có toàn bộ + - * /
package SuDungStack1;

import java.util.Stack;
import java.util.StringTokenizer;

public class minhHoa2 {
    static double evaluatePostFixExp(String exp) {
        double result = 0;
        //Cắt biểu thức thành các chuỗi con
        StringTokenizer stk = new StringTokenizer(exp, "() ");
        Stack<Double> stack = new Stack<Double>();
        while (stk.hasMoreTokens()) {
            String S = stk.nextToken();
            if (!S.equals("+") && !S.equals("-") && !S.equals("*") && !S.equals("/")) 
                stack.push(Double.parseDouble(S));
                //Cất toán hạng vào stack
             else {
                //Lấy toán hạng trong stack ra để được thực hiện phép toán 
                double n1 = stack.pop();
                double n2 = stack.pop();
                if (S.equals("+")) 
                    result = n1 + n2;
                 else if (S.equals("-")) 
                    result = n1 - n2;
                 else if (S.equals("*")) 
                    result = n1 * n2;
                 else if (S.equals("/")) {
                    if (n2 == 0.0) 
                        throw new RuntimeException("Divide by 0!");
                     else 
                        result = n1 / n2;
                    }
                 else 
                    throw new RuntimeException("This operator is not supported!");
                    stack.push(result); //Cắt kết quả trung gian vào stack
            }
        }
        return result;
    }
    public static void main(String[] args) {
        String exp = "1 2 * 3 4 * + 5 6 * + 2 *";
        System.out.println(evaluatePostFixExp(exp));
    }
}
