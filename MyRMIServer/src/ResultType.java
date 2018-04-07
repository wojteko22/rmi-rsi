import java.io.Serializable;

public class ResultType implements Serializable {
    private static final long serialVersionUID = 102L;
    private double result;
    private String result_description;

    ResultType(double result, String result_description) {
        this.result = result;
        this.result_description = result_description;
    }

    double getResult() {
        return result;
    }

    String getResult_description() {
        return result_description;
    }
}
