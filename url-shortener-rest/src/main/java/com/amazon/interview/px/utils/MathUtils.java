package com.amazon.interview.px.utils;

import java.util.Stack;

public class MathUtils {
    private static char[] charSet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    /**
     * 将10进制转化为62进制
     *
     * @param number
     * @return
     */
    public static String _10_to_62(long number) {
        Long rest = Math.abs(number);
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest != 0) {
            stack.add(charSet[new Long((rest - (rest / 62) * 62)).intValue()]);
            rest = rest / 62;
        }
        for (; !stack.isEmpty(); ) {
            result.append(stack.pop());
        }

        return result.toString();

    }
}
