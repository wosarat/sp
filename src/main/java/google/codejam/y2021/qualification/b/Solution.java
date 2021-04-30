package google.codejam.y2021.qualification.b;

import java.io.*;
import java.text.DecimalFormat;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Solution implements Runnable {
    private final static long START_TIME = System.currentTimeMillis();

    private final static boolean USE_FILES = false;
    private final static File FILE_DIR = new File("D:/");
    private final static File IN_FILE = new File(FILE_DIR, "in.txt");
    private final static File OUT_FILE = new File(FILE_DIR, "out.txt");


    private final static boolean LOG_ENABLED = true;
    private final static boolean ONLINE_JUDGE = (System.getenv("LOCAL_JUDGE") == null);
    private final static String SYSTEM_ENCODING = "utf-8";
    private final static int DEFAULT_BUFFER_SIZE = 256 * 256;


    private static class Logger {
        private final PrintWriter logWriter = Util.newPrintWriter(System.err, true);
        private final DecimalFormat df = new DecimalFormat("0.000");

        private void message(String type, String message, Object... params) {
            if (ONLINE_JUDGE || !LOG_ENABLED) {
                return;
            }
            logWriter.printf("[" + type + "] " + df.format((System.currentTimeMillis() - START_TIME) / 1000.0) + ": " + message + "\r\n", params);
        }

        public void debug(String message, Object... params) {
            message("DEBUG", message, params);
        }
    }

    interface PermutationVisitor<T> {
        void visit(T[] permutation);
    }

    public final static class Util {
        public final static String LINE_SEPARATOR = System.getProperty("line.separator");

        public static PrintWriter newPrintWriter(OutputStream out, boolean autoFlush) {
            try {
                return new PrintWriter(new OutputStreamWriter(new BufferedOutputStream(out), SYSTEM_ENCODING), autoFlush);
            } catch (UnsupportedEncodingException e) {
                throw new IllegalStateException(e);
            }
        }

        public static PrintWriter newPrintWriter(Writer writer, boolean autoFlush) {
            return new PrintWriter(writer, autoFlush);
        }


        private static void flushStringBuilder(Writer writer, StringBuilder sb) throws IOException {
            if (sb.length() > DEFAULT_BUFFER_SIZE) {
                writer.append(sb);
                sb.setLength(0);
            }
        }

        public static void printArray(Writer writer, String separator, Object... elements) throws IOException {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (Object o : elements) {
                if (first) {
                    first = false;
                } else {
                    sb.append(separator);
                }
                sb.append(o);
                flushStringBuilder(writer, sb);
            }
            sb.append(LINE_SEPARATOR);
            writer.append(sb);
        }

        public static void printArray(Writer writer, String separator, int... elements) throws IOException {
            StringBuilder sb = new StringBuilder();
            boolean first = true;
            for (Object o : elements) {
                if (first) {
                    first = false;
                } else {
                    sb.append(separator);
                }
                sb.append(o);
                flushStringBuilder(writer, sb);
            }
            sb.append(LINE_SEPARATOR);
            writer.append(sb);
        }

        public static long getElement(long[] array, int index, long outOfBoundsValue) {
            if (index < 0 || index >= array.length) {
                return outOfBoundsValue;
            }
            return array[index];
        }

        public static char getElement(char[] array, int index, char outOfBoundsValue) {
            if (index < 0 || index >= array.length) {
                return outOfBoundsValue;
            }
            return array[index];
        }

        public static int getElement(int[] array, int index, int outOfBoundsValue) {
            if (index < 0 || index >= array.length) {
                return outOfBoundsValue;
            }
            return array[index];
        }

        public static double getElement(double[] array, int index, double outOfBoundsValue) {
            if (index < 0 || index >= array.length) {
                return outOfBoundsValue;
            }
            return array[index];
        }

        public static <T> T firstNotNull(T value1, T value2) {
            if (value1 != null) {
                return value1;
            }
            if (value2 != null) {
                return value2;
            }
            throw new NullPointerException("All values are nulls");
        }

        public static long max(long... values) {
            long result = values[0];
            for (int i = 1, n = values.length; i < n; i++) {
                long currentValue = values[i];
                if (result < currentValue) {
                    result = currentValue;
                }
            }
            return result;
        }

        public static double max(double... values) {
            double result = values[0];
            for (int i = 1, n = values.length; i < n; i++) {
                double currentValue = values[i];
                if (result < currentValue) {
                    result = currentValue;
                }
            }
            return result;
        }

        public static long min(long... values) {
            long result = values[0];
            for (int i = 1, n = values.length; i < n; i++) {
                long currentValue = values[i];
                if (result > currentValue) {
                    result = currentValue;
                }
            }
            return result;
        }

        public static double min(double... values) {
            double result = values[0];
            for (int i = 1, n = values.length; i < n; i++) {
                double currentValue = values[i];
                if (result > currentValue) {
                    result = currentValue;
                }
            }
            return result;
        }

        public static <T> void visitAllPermutations(T[] array, PermutationVisitor<T> permutationVisitor) {
            visitAllPermutations(array, 0, permutationVisitor);
        }

        private static <T> void visitAllPermutations(T[] arr, int index, PermutationVisitor<T> permutationVisitor) {
            if (index == arr.length - 1) {
                permutationVisitor.visit(arr);
            } else {
                for (int i = index; i < arr.length; i++) {
                    T tmp = arr[index];
                    arr[index] = arr[i];
                    arr[i] = tmp;
                    visitAllPermutations(arr, index + 1, permutationVisitor);
                }
                T last = arr[index];
                for (int i = index; i < arr.length - 1; i++) {
                    arr[i] = arr[i + 1];
                }
                arr[arr.length - 1] = last;
            }
        }

        public static long euclideanAlgorithm(long n1, long n2) {
            long max = Math.max(n1, n2);
            long min = Math.min(n1, n2);
            while (min > 0) {
                long r = max % min;
                max = min;
                min = r;
            }
            return max;
        }

        public static BitSet getNonPrimes(int max) {
            BitSet nonPrime = new BitSet(max + 1);
            nonPrime.set(0);
            nonPrime.set(1);
            for (int i = 2; ; i = nonPrime.nextClearBit(i + 1)) {
                long from = ((long) i) * i;
                if (from > max) {
                    break;
                }
                for (long j = from; j <= max; j += i) {
                    nonPrime.set((int) j);
                }
            }
            return nonPrime;
        }
    }

    public static final class SmartPrintWriter extends PrintWriter {
        private final StringBuilder sb = new StringBuilder(50);
        private char[] chars = new char[50];

        public SmartPrintWriter(Writer out, boolean autoFlush) {
            super(out, autoFlush);
        }

        @Override
        public void print(long value) {
            sb.setLength(0);
            sb.append(value);
            flashStringBuilder();
        }

        @Override
        public void println(long value) {
            print(value);
            println();
        }

        @Override
        public void print(int value) {
            sb.setLength(0);
            sb.append(value);
            flashStringBuilder();
        }

        @Override
        public void println(int value) {
            print(value);
            println();
        }

        @Override
        public void print(double value) {
            sb.setLength(0);
            sb.append(value);
            flashStringBuilder();
        }

        @Override
        public void println(double value) {
            print(value);
            println();
        }


        @Override
        public void print(float value) {
            sb.setLength(0);
            sb.append(value);
            flashStringBuilder();
        }

        @Override
        public void println(float value) {
            print(value);
            println();
        }


        private final void flashStringBuilder() {
            int length = sb.length();
            if (chars.length < length) {
                chars = new char[length];
            }
            sb.getChars(0, length, chars, 0);
            super.write(chars, 0, length);
        }
    }

    public static final class FastScanner {
        private BufferedReader reader;
        private StringTokenizer currentTokenizer;

        public FastScanner(Reader reader) {
            if (reader instanceof BufferedReader) {
                this.reader = (BufferedReader) reader;
            } else {
                this.reader = new BufferedReader(reader);
            }
        }

        public String nextString() {
            if (currentTokenizer == null || !currentTokenizer.hasMoreTokens()) {
                try {
                    String line;
                    while ((line = reader.readLine()).isEmpty()) {
                        log.debug("Empty line has been skipped");
                    }
                    log.debug("Input line has been read:'%s'", line);
                    currentTokenizer = new StringTokenizer(line);
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
            return currentTokenizer.nextToken();
        }

        public String[] nextStrings(int n) {
            String[] result = new String[n];
            for (int i = 0; i < n; i++) {
                result[i] = nextString();
            }
            return result;
        }


        public int nextInt() {
            return Integer.parseInt(nextString());
        }

        public int[] nextInts(int n) {
            int[] result = new int[n];
            for (int i = 0; i < n; i++) {
                result[i] = nextInt();
            }
            return result;
        }

        public long nextLong() {
            return Long.parseLong(nextString());
        }

        public long[] nextLongs(int n) {
            long[] result = new long[n];
            for (int i = 0; i < n; i++) {
                result[i] = nextLong();
            }
            return result;
        }


        public double nextDouble() {
            return Double.parseDouble(nextString());
        }

        public double[] nextDoubles(int n) {
            double[] result = new double[n];
            for (int i = 0; i < n; i++) {
                result[i] = nextDouble();
            }
            return result;
        }


    }

    public static final class MultidimensionalArrayHelper {
        private final int[] dimensions;
        private final int size;

        public MultidimensionalArrayHelper(int... dimensions) {
            this.dimensions = dimensions;
            int s = 1;
            for (int dimension : dimensions) {
                s *= dimension;
            }
            this.size = s;
        }

        public int index(int... indexes) {
            int n = indexes.length;
            int index = 0;
            for (int i = n - 1; i > 0; i--) {
                index = (index + indexes[i]) * dimensions[i - 1];
            }
            index += indexes[0];
            return index;
        }

        public int size() {
            return size;
        }
    }


    private static final Logger log = new Logger();
    private final BufferedReader reader;
    private final FastScanner in;
    private final SmartPrintWriter out;


    public Solution(Reader reader, Writer writer) {
        this.reader = new BufferedReader(reader, DEFAULT_BUFFER_SIZE);
        this.out = new SmartPrintWriter(new BufferedWriter(writer, DEFAULT_BUFFER_SIZE), false);
        in = new FastScanner(this.reader);
    }

    public static void main(String[] args) throws IOException {
        if (USE_FILES) {
            OUT_FILE.delete();
        }

        try (InputStream input = USE_FILES ? new FileInputStream(IN_FILE) : System.in;
             OutputStream outputStream = USE_FILES ? new FileOutputStream(OUT_FILE) : System.out;) {
            new Solution(
                    new InputStreamReader(input, SYSTEM_ENCODING),
                    new OutputStreamWriter(outputStream, SYSTEM_ENCODING)).run();
        }
    }

    @Override
    public void run() {
        log.debug("Started");
        try {
            try {
                solve();
            } catch (RuntimeException e) {
                throw e;
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
            out.flush();
        } finally {
            log.debug("Stopped");
        }
    }

    private void solve() throws Exception {
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int y = in.nextInt();
            int x = in.nextInt();
            char[] chars = in.nextString().toCharArray();
            out.printf("Case #%s: %s\n", i + 1, solve(x, y, chars));
        }

    }

    private int solve(int x, int y, char[] chars) {
        int n=chars.length;
        Integer[][] dp = new Integer[chars.length][2];
        for (int i = 0; i < chars.length; i++) {
            if (i == 0) {
                switch (chars[i]) {
                    case 'C':
                        dp[0][0] = 0;
                        break;
                    case 'J':
                        dp[0][1] = 0;
                        break;
                    case '?':
                        dp[0][0] = 0;
                        dp[0][1] = 0;
                        break;
                    default:
                        throw new IllegalStateException();
                }
            } else {
                switch (chars[i]) {
                    case 'C':
                        if (dp[i - 1][0] == null) {
                            dp[i][0] = dp[i - 1][1]+x;
                        }else if(dp[i - 1][1] == null){
                            dp[i][0] = dp[i - 1][0];
                        }else{
                            dp[i][0] = Math.min(dp[i - 1][1]+x, dp[i - 1][0]);
                        }
                        break;
                    case 'J':
                        if (dp[i - 1][0] == null) {
                            dp[i][1] = dp[i - 1][1];
                        }else if(dp[i - 1][1] == null){
                            dp[i][1] = dp[i - 1][0]+y;
                        }else{
                            dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]+y);
                        }
                        break;
                    case '?':
                        if (dp[i - 1][0] == null) {
                            dp[i][0] = dp[i - 1][1]+x;
                        }else if(dp[i - 1][1] == null){
                            dp[i][0] = dp[i - 1][0];
                        }else{
                            dp[i][0] = Math.min(dp[i - 1][1]+x, dp[i - 1][0]);
                        }
                        if (dp[i - 1][0] == null) {
                            dp[i][1] = dp[i - 1][1];
                        }else if(dp[i - 1][1] == null){
                            dp[i][1] = dp[i - 1][0]+y;
                        }else{
                            dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0]+y);
                        }

                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
        }
        if(dp[n-1][0]==null){
            return dp[n-1][1];
        } if(dp[n-1][1]==null){
            return dp[n-1][0];
        }else{
            return Math.min(dp[n-1][0],dp[n-1][1]);
        }
    }
}
//-------------------------------//-------------------------------
