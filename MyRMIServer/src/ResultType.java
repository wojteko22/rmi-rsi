import java.io.Serializable;

class ResultType implements Serializable {
    private static final long serialVersionUID = 102L;
    private double result;
    private String result_description;

    ResultType(double result, String result_description) {
        this.result = result;
        this.result_description = result_description;
    }
}
