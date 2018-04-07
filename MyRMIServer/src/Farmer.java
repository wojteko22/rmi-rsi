import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

class Farmer {
    private final String[] addresses;

    Farmer(String[] addresses) {
        this.addresses = addresses;
    }

    void startFarmer() throws RemoteException, MalformedURLException, NotBoundException {
        for (String address : addresses) {
            Task task = new TaskImpl(new Random().nextInt());
            Worker worker = remoteWorker(address);
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
}
