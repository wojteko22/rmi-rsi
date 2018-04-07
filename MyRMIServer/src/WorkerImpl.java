import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class WorkerImpl extends UnicastRemoteObject implements Worker {
    WorkerImpl() throws RemoteException {
        super();
    }

    @Override
    public ResultType calculate(Task task) {
        return task.calculate();
    }
}
