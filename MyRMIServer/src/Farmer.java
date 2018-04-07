import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Random;

public class Farmer {
    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        checkArgs(args);
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

    private static void checkArgs(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "You have to enter one RMI object address in the form: //host_address/service_name ");
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
