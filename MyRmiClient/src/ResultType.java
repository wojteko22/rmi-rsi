import java.io.Serializable;

public class ResultType implements Serializable {
    private static final long serialVersionUID = 102L;
    private double result;
    private String result_description;

    double getResult() {
        return result;
    }

    String getResult_description() {
        return result_description;
    }
}
