import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

class Farmer {
    private final String[] addresses;

    Farmer(String[] addresses) {
        this.addresses = addresses;
    }

    void start() throws RemoteException, MalformedURLException, NotBoundException {
        int n = 0;
        double sum = 0;
        for (String address : addresses) {
            Worker worker = remoteWorker(address);
            Task task = new TaskImpl(n);
            ResultType result = calculateWith(worker, task);
            sum += result.getResult();
            ++n;
        }
        System.out.println("Result = " + sum * 4);
    }

    private static Worker remoteWorker(String address) throws RemoteException, NotBoundException, MalformedURLException {
        try {
            Worker worker = (Worker) Naming.lookup(address);
            System.out.println("Reference to " + address + " is got.");
            return worker;
        } catch (Exception e) {
            System.out.println("Cannot get reference to " + address);
            throw e;
        }
    }

    private ResultType calculateWith(Worker worker, Task task) throws RemoteException {
        try {
            return worker.calculate(task);
        } catch (Exception e) {
            System.out.println("Error during remote execution.");
            throw e;
        }
    }
}
