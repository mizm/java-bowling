package bowling.domain.frame;

import bowling.domain.Pin;
import bowling.domain.Round;
import bowling.domain.state.Ready;
import bowling.domain.state.State;

import java.util.Objects;

public class NormalFrame implements Frame {

    private final Round round;
    private final State state;

    private NormalFrame(Round round, State state) {
        this.round = round;
        this.state = state;
    }

    public static NormalFrame readyFrame(Round round) {
        return of(round, Ready.getInstance());
    }

    public static NormalFrame of(Round round, State state) {
        return new NormalFrame(round, state);
    }

    @Override
    public Frame bowl(Pin pin) {
        State next = state.bowl(pin);
        if (next.isFinished()) {

        }
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        NormalFrame that = (NormalFrame) o;
        return Objects.equals(round, that.round) && Objects.equals(state, that.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(round, state);
    }
}
