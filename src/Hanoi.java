import java.util.Arrays;
import java.util.Stack;

public class Hanoi {

    private static int n;
    private static Stack<Integer> x;
    private static Stack<Integer> y;
    private static Stack<Integer> z;
    private static int iterations = 0;
    private static int numberOfMoves = 0;
    private static StringBuilder moves = new StringBuilder();

    public static void main(String[] args) {
        long start;
        long finish;

        n = Integer.parseInt(args[0]);
        x = range(n);
        y = new Stack<>();
        z = new Stack<>();

        display(x, y, z);

        start = System.currentTimeMillis();
        hanoi(n, x, z, y);
        finish = System.currentTimeMillis();

        display(x, y, z);
        //System.out.println(moves.toString());
        System.out.println(String.format("%s iterations and %s moves in %s ms", iterations, numberOfMoves, finish - start));
    }

    private static void hanoi(final int n, Stack<Integer> x, Stack<Integer> z, Stack<Integer> y) {
        iterations++;
        if(n == 1) {
            move(x,z);
        } else if(n == 2) {
            move(x,y);
            move(x,z);
            move(y,z);
        } else {
            hanoi(n - 1, x, y, z);
            move(x, z);
            hanoi(n - 1, y, z, x);
        }
    }

    private static void move(Stack<Integer> a, Stack<Integer> b) {
        if(!a.isEmpty() && (b.isEmpty() || a.peek() < b.peek())) {
            b.add(a.pop());
            numberOfMoves++;
            final String move = String.format("%s -> %s%n", label(a), label(b));
            moves.append(move);
        }
    }

    private static String label(Stack<Integer> a) {
        if(a == x) return "A";
        if(a == y) return "B";
        if(a == z) return "C";
        return "";
    }

    private static Stack<Integer> range(int n) {
        final Stack<Integer> a = new Stack<>();
        for(int i = n; i > 0; i--) {
            a.push(i);
        }
        return a;
    }
    private static String stringify(Stack<Integer> a) {
        StringBuilder sb = new StringBuilder("\n");
        sb.append("[");
        sb.append(label(a));
        sb.append("]");
        a.forEach(i -> sb.append(" " + i));
        return sb.toString();
    }
    private static void display(Stack<Integer> x, Stack<Integer> y, Stack<Integer> z) {
        StringBuilder sb = new StringBuilder();
        sb.append("[Iteration " + iterations + "]");
        final String w[] = new String [] {
                stringify(x),
                stringify(y),
                stringify(z)
        };
        Arrays.sort(w);
        sb.append(w[0]);
        sb.append(w[1]);
        sb.append(w[2]);
        sb.append(hr(n));
        System.out.print(sb.toString());
    }
    private static String hr(final int n) {
        final int MAX_LENGTH = (n < 10) ? n :
                (n < 100) ? (9 + 2 * (n - 9)) : 200;

        StringBuilder sb = new StringBuilder("\n+");
        for(int i = 0; i < MAX_LENGTH + n; i++) {
            sb.append("-");
        }
        sb.append("\n");
        return sb.toString();
    }
}
