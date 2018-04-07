import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {
    public static void main(String[] args) throws RemoteException, MalformedURLException, NotBoundException {
        int numberOfWorkers = new ArgsParser(args).numberOfWorkers();
        String[] addresses = new AddressesMaker(numberOfWorkers).prepareAddresses();
        new WorkerStarter(addresses).startWorkers();
        new Farmer(addresses).start();
    }
}
