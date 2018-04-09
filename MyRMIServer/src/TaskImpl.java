public class TaskImpl implements Task {
    private int n;

    TaskImpl(int n) {
        this.n = n;
    }

    @Override
    public ResultType calculate() {
        int multiplier = 1000000;
        int start = multiplier * n;
        double sum = 0.0;
        int i = start;
        while(i < start + multiplier) {
            sum += 1.0 / (4 * i + 1) - 1.0 / (4 * i + 3);
            i++;
        }
        return new ResultType(sum);
    }
}
