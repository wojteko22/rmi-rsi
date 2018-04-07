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
        return task.calculate();
    }

    @Override
    public int getId() {
        return id;
    }
}
