import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class MyClient {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        checkArgs(args);
        String address1 = args[0];
        String address2 = args[1];
        InputType input = new InputType("add", 6.6, 3.3);
        CalcObject remoteObject1 = remoteObject(address1);
        CalcObject2 remoteObject2 = remoteObject(address2);
        double result1;
        ResultType result2;
        try {
            result1 = remoteObject1.calculate(1.1, 2.2);
            result2 = remoteObject2.calculate(input);
        } catch (Exception e) {
            System.out.println("Error during remote execution.");
            throw e;
        }
        System.out.println("Result1 = " + result1);
        System.out.println("Result2 = " + result2.getResult() + " " + result2.getResult_description());
    }

    private static void checkArgs(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException(
                    "You have to enter two RMI object addresses in the form: //host_address/service_name ");
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
