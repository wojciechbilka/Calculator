package calculator.logic;

import java.math.BigDecimal;
import java.math.RoundingMode;

public enum MathematicalOperation {
    ADD {
        @Override
        public BigDecimal calculate(BigDecimal x, BigDecimal y) {
            return x.add(y);
        }
    },
    SUBTRACT {
        @Override
        public BigDecimal calculate(BigDecimal x, BigDecimal y) {
            return x.subtract(y);
        }
    },
    DIVIDE {
        @Override
        public BigDecimal calculate(BigDecimal x, BigDecimal y) {
            return x.divide(y, 10, RoundingMode.HALF_UP);
        }
    },
    MULTIPLY {
        @Override
        public BigDecimal calculate(BigDecimal x, BigDecimal y) {
            return x.multiply(y);
        }
    };

    public abstract BigDecimal calculate(BigDecimal x, BigDecimal y);
}
