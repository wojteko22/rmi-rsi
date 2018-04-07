import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

class WorkerStarter {

    private final String[] addresses;

    WorkerStarter(String[] addresses) {
        this.addresses = addresses;
    }

    void startWorkers() throws RemoteException, MalformedURLException {
        LocateRegistry.createRegistry(1099);
        for(int i = 0; i < addresses.length; i++) {
            try {
                Worker worker = new WorkerImpl(i);
                String address = addresses[i];
                Naming.rebind(address, worker);
                System.out.println("Server " + address + " is registered now :-)");
            } catch (Exception e) {
                System.out.println("SERVER CAN'T BE REGISTERED!");
                throw e;
            }
        }
    }
}
