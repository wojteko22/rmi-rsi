import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcObjImpl extends UnicastRemoteObject implements CalcObject {
    CalcObjImpl() throws RemoteException {
        super();
    }

    @Override
    public double calculate(double a, double b) {
        return a + b;
    }
}
