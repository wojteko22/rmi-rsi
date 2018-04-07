public class MyClient {
    public static void main(String[] args) {
        double wynik;
        CalcObject zObiekt;
        CalcObject2 zObiekt2;
        ResultType wynik2;
        InputType inObj;
        if (args.length != 2) {
            System.out.println("You have to enter two RMI object addresses in the form: //host_address/service_name ");
            return;
        }
        String adres = args[0];
        String adres2 = args[1];

        inObj = new InputType();
        inObj.x1 = 6.6;
        inObj.x2 = 3.3;
        inObj.operation = "add"; //lub "sub"
        // //use this if needed
        // if (System.getSecurityManager() == null)
        // System.setSecurityManager(new SecurityManager());
        try {
            zObiekt = (CalcObject) java.rmi.Naming.lookup(adres);
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac referencji do " + adres);
            e.printStackTrace();
            return;
        }
        try {
            zObiekt2 = (CalcObject2) java.rmi.Naming.lookup(adres2);
        } catch (Exception e) {
            System.out.println("Nie mozna pobrac referencji do " + adres2);
            e.printStackTrace();
            return;
        }
        System.out.println("Referencja do " + adres + " jest pobrana.");
        try {
            wynik = zObiekt.calculate(1.1, 2.2);
            wynik2 = zObiekt2.calculate(inObj);
        } catch (Exception e) {
            System.out.println("Blad zdalnego wywolania.");
            e.printStackTrace();
            return;
        }
        System.out.println("Referencja do " + adres2 + " jest pobrana.");
        System.out.println("Wynik = " + wynik);
        System.out.println("Wynik2 = " + wynik2.result + " " + wynik2.result_description);
    }
}
