public class TaskImpl implements Task {
    private int n;

    TaskImpl(int n) {
        this.n = n;
    }

    @Override
    public ResultType calculate() {
        double result = 1.0 / (4 * n + 1) - 1.0 / (4 * n + 3);
        return new ResultType(result);
    }
}
