package bowling.domain.result;

import java.util.List;
import java.util.Objects;

public class FrameResults {
    private final List<FrameResult> values;

    public FrameResults(List<FrameResult> values) {
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FrameResults that = (FrameResults) o;
        return Objects.equals(values, that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(values);
    }

    @Override
    public String toString() {
        return "FrameResults{" +
                "values=" + values +
                '}';
    }
}
