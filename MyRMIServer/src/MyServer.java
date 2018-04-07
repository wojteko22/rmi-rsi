import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class MyServer {
    public static void main(String[] args) throws RemoteException, MalformedURLException {
        checkArgs(args);
        LocateRegistry.createRegistry(1099);
        try {
            CalcObject object1 = new CalcObjImpl();
            Naming.rebind(args[0], object1);
            CalcObject2 object2 = new CalcObjImpl2();
            Naming.rebind(args[1], object2);
            System.out.println("Server is registered now :-)");
        } catch (Exception e) {
            System.out.println("SERVER CAN'T BE REGISTERED!");
            throw e;
        }
    }

    private static void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "You have to enter two RMI object addresses in the form: //host_address/service_name ");
        }
    }
}
