public class TaskImpl implements Task {
    @Override
    public ResultType calculate() {
        return new ResultType(1, "some result");
    }
}
