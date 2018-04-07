import java.io.Serializable;

public class ResultType implements Serializable {
    private static final long serialVersionUID = 102L;
    private double result;

    ResultType(double result) {
        this.result = result;
    }

    double getResult() {
        return result;
    }
}
