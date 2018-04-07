import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Worker extends Remote {
    int calculate(Task task) throws RemoteException;
}
