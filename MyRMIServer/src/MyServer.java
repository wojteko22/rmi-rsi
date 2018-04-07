import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MyServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        checkArgs(args);
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

    private static void checkArgs(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException(
                    "You have to enter one RMI object address in the form: //host_address/service_name ");
        }
    }
}
