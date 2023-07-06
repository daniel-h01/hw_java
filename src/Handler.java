package example;
import java.util.concurrent.ThreadLocalRandom;

public class Handler {
    public Double rnd() {
        return ThreadLocalRandom.current().nextDouble();
    }

    public Double mul(Double a, Double b) {
        return a * b;
    }

    public Double sum(Double a, Double b) {
        return a + b;
    }

    public Double div(Double a, Double b) {
        return a / b;
    }

    public Double mod(Double a, Double b) {
        return a % b;
    }
}