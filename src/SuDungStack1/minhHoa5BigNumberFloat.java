/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SuDungStack1;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class minhHoa5BigNumberFloat {

    public static String addFloat(String numStr1, String numStr2) {
        String Str1[];
        String Str2[];
        //lay vi tri dau thap phan
        int x1 = numStr1.indexOf(",");
        int x2 = numStr2.indexOf(",");
        //
        Str1 = numStr1.split(",");
        Str2 = numStr2.split(",");

        int Length1 = Str1[1].length();
        int Length2 = Str2[1].length();
        if (Length1 < Length2) { //Cộng thêm chuỗi ngắn bang chuỗi dài 
            for (int i = 0; i < (Length2 - Length1); i++) {
                Str1[1] = Str1[1] + "0";
            }
        } else if (Length1 > Length2) {
            for (int i = 0; i < (Length1 - Length2); i++) {
                Str2[1] = Str2[1] + "0";
            }
        }

        numStr1 = Str1[0].concat(Str1[1]);
        numStr2 = Str2[0].concat(Str2[1]);
        char[] S1 = numStr1.toCharArray(); //Dung stack dung mang
        char[] S2 = numStr2.toCharArray();
        int L1 = S1.length;
        int L2 = S2.length;
        //Cho S1 là chuỗi kết  quả ban đầu: chuỗi dài 
        if (L1 < L2) { //Cộng thêm chuỗi ngắn vào chuỗi dài 
            char[] t = S1;
            S1 = S2;
            S2 = t;
            int tt = L1;
            L1 = L2;
            L2 = tt;
        }
        int k = L1 - 1; //CHỉ số đỉnh stack
        int carry = 0; //Khởi tạo carry, phần nhớ của phép cộng trước
        //Cộng từ cuối lên, Xử lý phần chung
        int j = L2 - 1; //Chỉ số cuối của số thứ hai, chuỗi ngắn hơn
        while (k >= 0 && j >= 0) {//Tính toán trên giá trị của ký số
            int sum = Character.digit(S1[k], 10) + Character.digit(S2[j], 10) + carry;
            carry = sum >= 10 ? 1 : 0;
            if (carry > 0) {
                sum -= 10;
            }
            S1[k] = Character.forDigit(sum, 10);//Chuyển số thành ký số(thành char)
            j--;
            k--;//giảm chỉ số đỉnh stack
        }
        //Xử lý phần dư ở đâu
        while (k >= 0 & carry == 1) {
            int sum = Character.digit(S1[k], 10) + carry;
            carry = sum > 10 ? 1 : 0;
            if (carry > 0) {
                sum -= 10;
            }
            S1[k--] = Character.forDigit(sum, 10);
        }

        //them dau thap phan
        char[] x = new char[L1];
        char[] y = new char[L1];
        if (x1 >= x2) {
            for (int i = 0; i < x1; i++) {
                x[i] = S1[i];
            }

            for (int i = x1; i < S1.length; i++) {
                y[i] = S1[i];
            }
        } else if (x1 < x2) {
            for (int i = 0; i < x2; i++) {
                x[i] = S1[i];
            }

            for (int i = x2; i < S1.length; i++) {
                y[i] = S1[i];
            }
        }

        //Xử lý carry cuối cùng
        return carry > 0 ? "1" + new String(x) + "," + new String(y) : new String(x) + "," + new String(y);
    }
    
    public static String add(String numStr1, String numStr2){
        char[] S1 = numStr1.toCharArray(); //Dung stack dang mang
        char[] S2 = numStr2.toCharArray();
        int L1 = S1.length;
        int L2 = S2.length;
        //Cho S1 là chuỗi kết  quả ban đầu: chuỗi dài 
        if(L1 < L2){ //Cộng thêm chuỗi ngắn vào chuỗi dài 
           char[] t = S1;
           S1 = S2; 
           S2 = t;
           int tt = L1;
           L1 = L2;
           L2 = tt;
        }
        int k = L1-1; //CHỉ số đỉnh stack
        int carry=0; //Khởi tạo carry, phần nhớ của phép cộng trước
        //Cộng từ cuối lên, Xử lý phần chung
        int j = L2-1; //Chỉ số cuối của số thứ hai, chuỗi ngắn hơn
        while(k>=0 && j>=0){//Tính toán trên giá trị của ký số
            int sum = Character.digit(S1[k], 10) + Character.digit(S2[j], 10) + carry;
            carry = sum>=10?1:0;
            if(carry>0) sum-=10;
            S1[k] = Character.forDigit(sum, 10);//Chuyển số thành ký số(thành char)
            j--;
            k--;//giảm chỉ số đỉnh stack
        }
        //Xử lý phần dư ở đâu
        while(k>=0 & carry==1){
            int sum= Character.digit(S1[k], 10) + carry;
            carry = sum>10?1:0;
            if(carry>0) sum -= 10;
            S1[k--] = Character.forDigit(sum, 10);
        }
        //Xử lý carry cuối cùng
        return carry > 0 ? "1" + new String(S1): new String(S1);
    }

    //Dịch trái 1 chuỗi số , thêm 0 vào bên trái
    public static String shiftLeft(String numStr, int n) {
        if (numStr.equals("0")) {
            return "0";
        }
        String extra = "";
        for (int i = 0; i < n; i++) {
            extra += "0";
        }
        return numStr + extra;
    }

    //Nhân 1 ký số với 1 chuỗi số ---> phép nhân cơ bản 
    public static String multiply(String numStr, char c) {
        if (c == '0') {
            return "0";
        }
        char[] S1 = numStr.toCharArray();
        char[] S = new char[S1.length + 1];
        int carry = 0;
        int k = S.length - 1;//Chỉ số đỉnh stack
        //Nhân ngược từ cuối lên
        for (int i = k - 1; i >= 0; i--) {
            int result = (c - '0') * (S1[i] - '0') + carry;
            carry = result / 10;
            S[k] = Character.forDigit(result % 10, 10);
            k--;//Giảm chỉ số đỉnh stack
        }
        //xử lý carry cuối cùng 
        if (carry > 0) {
            return Character.forDigit(carry, 10) + new String(S1);
        }
        return new String(S1);
    }

    //Nhân 2 chuỗi số nguyên dương
    public static String multiply(String numStr1, String numStr2) {
        String Str1[];
        String Str2[];
        //lay vi tri dau thap phan
        int x1 = numStr1.indexOf(",");
        int x2 = numStr2.indexOf(",");
        //
        Str1 = numStr1.split(",");
        Str2 = numStr2.split(",");

        int Length1 = Str1[1].length();
        int Length2 = Str2[1].length();
        numStr1 = Str1[0].concat(Str1[1]);
        numStr2 = Str2[0].concat(Str2[1]);
        int L = numStr2.length() - 1;
        String S = "0", multResult, shiftResult;
        for (int i = L; i >= 0; i--) {
            multResult = multiply(numStr1, numStr2.charAt(i));
            shiftResult = shiftLeft(multResult, L - i);
            S = add(S, shiftResult);
        }
        return S;
    }


    public static void main(String[] args) {
        //123456789012345678901234567890l
        String S1 = "98256189530800219992349703,7034132456";
        //123456789012345678901234567890123456
        String S2 = "732588835326788470000711023246913560,4324566789";
        System.out.format("S1:%50s\n", S1);
        System.out.format("S2:%50s\n", S2);
        String S3 = addFloat(S1, S2);
        System.out.format("S3=S1+S2:%44s\n\n", S3);
        String S4= "12345678000120011,8901065734";
        String S5= "200000000000,23";
        String S6 = multiply(S4, S5);
        System.out.format("s4:%50s\n", S4);
        System.out.format("s5:%50s\n", S5);
        System.out.format("S6=S4xS5:%44s\n", S6);
    }
}
