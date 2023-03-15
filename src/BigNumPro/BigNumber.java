/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BigNumPro;

/**
 *
 * @author HAU NUONG MO HANH
 */
public class BigNumber extends GenericStack<Byte>
        implements Comparable<BigNumber> {

    private boolean negative = false;

    @Override
    public String toString() {
        String result = "";
        if (negative == true) {
            result += "-";
        }
        GenericStack<Byte> tmp = this.toGenericStack();
        for (GenericNode<Byte> i = tmp.top; i != null; i = i.pre) {
            result += i.info.toString();
        }

        return result;
    }

    public static BigNumber parse(String input) {
        input = input.trim();
        BigNumber result = new BigNumber();
        int length = input.length();
        for (int i = 0; i < length; i++) {
            Character ch = input.charAt(i);
            if (ch == '-' && i == 0) {
                result.negative = true;
            } else {
                Byte el = Byte.parseByte(ch.toString());
                result.push(el);
            }
        }
        return result;
    }

    public BigNumber Add(String numB) {
        BigNumber stB = BigNumber.parse(numB);
        return BigNumber.Add(this, stB);
    }

    public BigNumber Add(BigNumber numB) {
        return BigNumber.Add(this, numB);
    }

    public static BigNumber Add(String numA, String numB) {
        BigNumber stA, stB;
        stA = BigNumber.parse(numA);
        stB = BigNumber.parse(numB);
        return Add(stA, stB);

    }

    public BigNumber negativeValue() {
        GenericStack<Byte> tmp = this.toGenericStack();
        BigNumber cp = new BigNumber();
        tmp.moveAllElsTo(cp);
        cp.negative = !this.negative;
        return cp;
    }

    public static BigNumber Add(BigNumber stA, BigNumber stB) {
        if (stA.negative == true && stB.negative == false) {
            BigNumber cpStA = stA.negativeValue();
            return Sub(stB, cpStA);
        }
        if (stA.negative == false && stB.negative == true) {
            BigNumber cpStB = stB.negativeValue();
            return Sub(stA, cpStB);
        }

        stA = stA.copy();
        stB = stB.copy();

        byte re = 0;
        GenericStack<Byte> stResult = new GenericStack<>();
        BigNumber check = stA.length() > stB.length() ? stA : stB;

        while (!check.isEmpty()) {
            Byte popA = 0, popB = 0;
            if (!stA.isEmpty()) {
                popA = stA.pop();
            }
            if (!stB.isEmpty()) {
                popB = stB.pop();
            }

            re += (byte) (popA + popB);
            if (re >= 10) {
                re = (byte) (re - 10);
                stResult.push(re);
                re = 1;
            } else {
                stResult.push(re);
                re = 0;
            }
        }

        if (re != 0) {
            stResult.push(re);
        }

        BigNumber result = new BigNumber();
        stResult.moveAllElsTo(result);
        if (stA.negative == true && stB.negative == true) {
            result.negative = true;
        }

        return result;

    }

    public BigNumber Sub(String numB) {
        BigNumber stB = BigNumber.parse(numB);
        return BigNumber.Sub(this, stB);
    }

    public BigNumber Sub(BigNumber numB) {
        return BigNumber.Sub(this, numB);
    }

    public static BigNumber Sub(String numA, String numB) {
        BigNumber stA, stB;
        stA = BigNumber.parse(numA);
        stB = BigNumber.parse(numB);
        return Sub(stA, stB);

    }

    public static BigNumber Sub(BigNumber stA, BigNumber stB) {
        if (stA.negative == false && stB.negative == true) {
            BigNumber cpStB = stB.negativeValue();
            return Add(stA, cpStB);
        }
        if (stA.negative == true && stB.negative == false) {
            BigNumber cpStB = stB.negativeValue();
            return Add(stA, cpStB);
        }

        stA = stA.copy();
        stB = stB.copy();

        byte re = 0;
        GenericStack<Byte> stResult = new GenericStack<>();
        boolean neg = false;
        if (stA.compareTo(stB) < 0) {
            BigNumber tmp = stA;
            stA = stB;
            stB = tmp;
            neg = true;
        }

        while (!stA.isEmpty()) {
            Byte popA = 0, popB = 0;
            if (!stA.isEmpty()) {
                popA = stA.pop();
            }
            if (!stB.isEmpty()) {
                popB = stB.pop();
            }
            Byte Larg = popA < (popB + re) ? (byte) (popA + 10) : popA;
            re = (byte) (Larg - popB - re);
            if (re >= 10) {
                re = (byte) (re - 10);
                stResult.push(re);
                re = 1;
            } else {
                stResult.push(re);
                re = 0;
            }
            if (Larg >= 10) {
                re += 1;
            }
        }

        if (re != 0) {
            stResult.push(re);
        }

        BigNumber result = new BigNumber();
        stResult.moveAllElsTo(result);
        result.negative = neg;
        return result;

    }

    public BigNumber Mul(BigNumber numB) {
        return BigNumber.Mul(this, numB);
    }

    public static BigNumber Mul(String numA, String numB) {
        BigNumber stA, stB;
        stA = BigNumber.parse(numA);
        stB = BigNumber.parse(numB);
        return Mul(stA, stB);

    }

    public static BigNumber Mul(BigNumber stA, BigNumber stB) {
        stA = stA.copy();
        stB = stB.copy();

        if (stA.equals(BigNumber.parse("0")) || stB.equals(BigNumber.parse("0"))) {
            return BigNumber.parse("0");
        }

        BigNumber stResult = new BigNumber();
        if (stA.length() < stB.length()) {
            BigNumber tmp = stA;
            stA = stB;
            stB = tmp;
        }

        int timesToDo = stB.length();
        BigNumber[] results = new BigNumber[timesToDo];
        for (int i = 0; i < timesToDo; i++) {
            BigNumber backup1 = stA.copy();
            Byte digit = stB.pop();
            results[i] = BigNumber.doMul(i, digit, stA);
            stA = backup1;
            if (i == 0) {
                stResult = results[i];
            } else {
                stResult = stResult.Add(results[i]);
            }
        }

        stResult.negative = stA.negative ^ stB.negative;

        return stResult;

    }

    protected static BigNumber doMul(int pos, Byte digit, BigNumber stA) {
        int stALen = stA.length();

        GenericStack<Byte> tmp = new GenericStack<>();
        for (int j = 0; j < pos; j++) {
            tmp.push((byte) 0);
        }
        byte re = 0;
        for (int j = 0; j < stALen; j++) {
            Byte popA = stA.pop();
            re += digit * popA;
            if (re < 10) {
                tmp.push(re);
                re = 0;
            } else {
                byte plus = (byte) (re / 10);
                re = (byte) (re - plus * 10);
                tmp.push(re);
                re = plus;
            }
        }
        if (re != 0) {
            tmp.push(re);
        }

        BigNumber result = new BigNumber();
        tmp.moveAllElsTo(result);
        return result;
    }

    public BigNumber Div(String numB) {
        BigNumber stB = BigNumber.parse(numB);
        return BigNumber.Div(this, stB);
    }

    public BigNumber Div(BigNumber numB) {
        return BigNumber.Div(this, numB);
    }

    public static BigNumber Div(String numA, String numB) {
        BigNumber stA, stB;
        stA = BigNumber.parse(numA);
        stB = BigNumber.parse(numB);
        return Div(stA, stB);

    }

    public BigNumber copy() {
        BigNumber result = this.negativeValue();
        result.negative = this.negative;
        return result;
    }

    public static BigNumber Div(BigNumber stA, BigNumber stB) {
        if (stB.equals(BigNumber.parse("0"))) {
            throw new java.lang.ArithmeticException("DIVIDE BY 0.");
        }
        boolean neg = (stA.negative == true ^ stB.negative == true);
        stA = stA.copy();
        stA.negative = false;
        stB = stB.copy();
        stB.negative = false;
        int compare = stA.compareTo(stB);

        if (compare < 0) {
            return BigNumber.parse("0");
        }
        if (compare == 0) {
            return BigNumber.parse("1");
        }

        BigNumber result = new BigNumber();
        BigNumber pop = null;
        GenericStack<Byte> numA = stA.toGenericStack();
        int len = numA.length();
        for (int i = 0; i < len; i++) {
            Byte get = numA.pop();
            if (pop == null) {
                pop = BigNumber.parse(get.toString());

            } else {
                pop.push(get);
            }
            if (pop.compareTo(stB) < 0) {
                result.push((byte) 0);
                continue;
            }
            BigNumber fCheck = BigNumber.parse(pop.toString());
            BigNumber check = pop.Sub(stB);

            BigNumber Z = BigNumber.parse("0");
            Integer find = 0;
            while (check.compareTo(Z) >= 0) {
                find++;
                check = check.Sub(stB);
            }
            BigNumber tmp = BigNumber.parse(find.toString());
            pop = fCheck.Sub(tmp.Mul(stB));
            BigNumber tmp2 = new BigNumber();
            tmp.moveAllElsTo(tmp2);
            tmp2.moveAllElsTo(result);

        }
        result.negative = neg;
        return result;

    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BigNumber)) {
            return false;
        }
        return this.compareTo((BigNumber) o) == 0;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public void push(Byte el) {
        if (!this.isEmpty()) {
            if (this.length() == 1 & this.topEl() == 0) {
                this.pop();
                super.push(el);
            } else {
                super.push(el);
            }
        } else {
            super.push(el);
        }
    }

    @Override
    public int compareTo(BigNumber o) {
        if (this.negative == true && o.negative == false) {
            return -1;
        }
        if (this.negative == false && o.negative == true) {
            return 1;
        }
        if (this.length() < o.length()) {
            return -1;
        }
        if (this.length() > o.length()) {
            return 1;
        }
        int getLen = this.length();
        GenericStack<Byte> tmp1 = this.toGenericStack();
        GenericStack<Byte> tmp2 = o.toGenericStack();
        boolean flag = true;
        for (int i = 0; i < getLen; i++) {

            byte popThis = tmp1.pop();
            byte popO = tmp2.pop();
            if (popThis > popO) {
                return 1;
            }
            if (popThis != popO) {
                flag = false;
            }
            if (popThis < popO) {
                return -1;
            }
        }

        if (flag == false) {
            return 1;
        }

        return 0;
    }
}
