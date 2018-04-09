import java.io.Serializable;

public class ResultType implements Serializable {
    private static final long serialVersionUID = 102L;
    private double result;
    private Long time;

    ResultType(double result) {
        this.result = result;
    }

    ResultType(double result, Long time) {
        this.result = result;
        this.time = time;
    }

    double getResult() {
        return result;
    }

    Long getTime() {
        return time;
    }
}
