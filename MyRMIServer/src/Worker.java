import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Worker extends Remote {
    ResultType calculate(Task task) throws RemoteException;
}
