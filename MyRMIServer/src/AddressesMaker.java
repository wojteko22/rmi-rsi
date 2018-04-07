public class AddressesMaker {
    private final int count;

    AddressesMaker(int count) {
        this.count = count;
    }

    String[] prepareAddresses() {
        String[] array = new String[count];
        for (int i = 0; i < count; i++) {
            array[i] = "//localhost/" + i;
        }
        return array;
    }
}
