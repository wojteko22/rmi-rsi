import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WorkerImpl extends UnicastRemoteObject implements Worker {
    private final int id;

    WorkerImpl(int id) throws RemoteException {
        super();
        this.id = id;
    }

    @Override
    public ResultType calculate(Task task) {
        long start = System.currentTimeMillis();
        ResultType resultType = task.calculate();
        long end = System.currentTimeMillis();
        long time = end - start;
        double result = resultType.getResult();
        return new ResultType(result, time);
    }

    @Override
    public int getId() {
        return id;
    }
}
