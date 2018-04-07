public class TaskImpl implements Task {
    private int number;

    TaskImpl(int number) {
        this.number = number;
    }

    @Override
    public ResultType calculate() {
        return new ResultType(number, "some result");
    }
}
