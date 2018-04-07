import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        startWorkers();
        startFarmer();
    }

    private static void startWorkers() throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        String[] addresses = {"//localhost/a", "//localhost/b"};
        for (String address : addresses) {
            try {
                Worker worker = new WorkerImpl();
                Naming.rebind(address, worker);
                System.out.println("Server " + address + " is registered now :-)");
            } catch (Exception e) {
                System.out.println("SERVER CAN'T BE REGISTERED!");
                throw e;
            }
        }
    }

    private static void startFarmer() throws RemoteException, MalformedURLException, NotBoundException {
        String[] addresses = {"//localhost/a", "//localhost/b"};
        for (String address : addresses) {
            Task task = new TaskImpl(new Random().nextInt());
            Worker worker = remoteObject(address);
            ResultType result;
            try {
                result = worker.calculate(task);
            } catch (Exception e) {
                System.out.println("Error during remote execution.");
                throw e;
            }
            System.out.println("Result = " + result.getResult() + " " + result.getResult_description());
        }
    }

    private static <T> T remoteObject(String address) throws RemoteException, NotBoundException, MalformedURLException {
        try {
            T object = (T) Naming.lookup(address);
            System.out.println("Reference to " + address + " is got.");
            return object;
        } catch (Exception e) {
            System.out.println("Cannot get reference to " + address);
            throw e;
        }
    }
}
