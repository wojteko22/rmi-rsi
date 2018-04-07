import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalcObjImpl2 extends UnicastRemoteObject implements CalcObject2 {
    CalcObjImpl2() throws RemoteException {
        super();
    }

    @Override
    public ResultType calculate(InputType inParam) {
        double x1 = inParam.getX1();
        double x2 = inParam.getX2();
        String operation = inParam.getOperation();
        double result = 0;
        String description = "Operation " + operation;
        switch (operation) {
            case "add":
                result = x1 + x2;
                break;
            case "sub":
                result = x1 - x2;
                break;
            default:
                description = "Wrong operation";
        }
        return new ResultType(result, description);
    }
}
