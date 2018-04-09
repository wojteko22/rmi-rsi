import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Farmer {
    private final String[] addresses;

    Farmer(String[] addresses) {
        this.addresses = addresses;
    }

    void start() {
        boolean sequentiallyMode = sequentiallyMode();
        long start = System.currentTimeMillis();
        List<ResultType> list = getResults(sequentiallyMode);
        long end = System.currentTimeMillis();
        double pi = list.stream().mapToDouble(ResultType::getResult).sum() * 4;
        double average = list.stream()
                .mapToLong(ResultType::getTime).average()
                .orElseThrow(() -> new RuntimeException("Something went wrogn"));
        long time = end - start;
        System.out.println("PI: " + pi);
        System.out.println("Average worker time: " + average);
        System.out.println("Time on farmer: " + time + " ms");
    }

    private List<ResultType> getResults(boolean sequentiallyMode) {
        if (sequentiallyMode) {
            return getResultsSeqentially();
        }
        return getResultsParallely();
    }

    private List<ResultType> getResultsSeqentially() {
        return Stream.of(addresses).map(this::calculate).collect(Collectors.toList());
    }

    private List<ResultType> getResultsParallely() {
        return Stream.of(addresses).parallel().map(this::calculate).collect(Collectors.toList());
    }

    private boolean sequentiallyMode() {
        System.out.println("sequentially (s) or parallel (p)?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.next();
        char firstChar = input.charAt(0);
        return firstChar == 's' || firstChar == 'S';
    }

    private ResultType calculate(String address) {
        try {
            Worker worker = remoteWorker(address);
            int n = worker.getId();
            Task task = new TaskImpl(n);
            ResultType result = calculateWith(worker, task);
            Long time = result.getTime();
            System.out.println(address + ": " + time + " ms");
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Error during using worker", e);
        }
    }

    private static Worker remoteWorker(String address) throws RemoteException, NotBoundException, MalformedURLException {
        try {
            Worker worker = (Worker) Naming.lookup(address);
//            System.out.println("Reference to " + address + " is got.");
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
