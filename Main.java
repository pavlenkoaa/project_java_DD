package com.company;

import java.io.IOException;
import java.util.Stack;

public class Main {
    private static class Itemi {
        public final int factory;
        public final StringBuilder sb;
        public Itemi(int factor) {
            this.factory = factor;
            sb = new StringBuilder();
        }
    };
    private static Stack<Itemi> stack = new Stack<Itemi>();

    public static void main(String... args) {
        int c = -1;
        int factor = 0;
        for (; ; ) {
            try {
                c = System.in.read();
            } catch (IOException e) {
                System.exit(1);
            }
            if (c == -1) {
                break;
            }
            if (Character.isDigit(c)) {
                factor = 10 * factor + Character.getNumericValue(c);
            } else if (c == '[') {
                stack.push(new Itemi(factor));
                factor = 0;
            } else if (c == ']') {
                Itemi item = stack.pop();
                String s = item.sb.toString();
                append(item.factory, s);
                factor = 0;
            } else {
                append(factor, Character.toString((char) c));
                factor = 0;
            }
        }
    }

    private static void append(int factor, String s) {
        int n = (factor == 0) ? 1 : factor;
        for (int i = 0; i < n; ++i) {
            if (stack.empty()) {
                System.out.print(s);
            } else {
                stack.peek().sb.append(s);
            }
        }
    }
}
