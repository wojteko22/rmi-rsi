class ArgsParser {
    private final String[] args;

    ArgsParser(String[] args) {
        this.args = args;
    }

    int numberOfWorkers() {
        if (args.length == 0) {
            return 4;
        }
        return parseArgs();
    }

    private int parseArgs() {
        if (args.length != 1) {
            throw new IllegalArgumentException("You can specify only one number");
        }
        return parseFirstArg();
    }

    private int parseFirstArg() {
        int value = Integer.parseInt(args[0]);
        if (value < 4) {
            throw new IllegalArgumentException("Number of workers cannot be less than 4");
        }
        return value;
    }
}
