package bowling;

import bowling.domain.FinalFrame;
import bowling.domain.NormalFrame;
import bowling.domain.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FInalFrameTest {
    Player player;
    NormalFrame frame;


    @BeforeEach
    void init() {
        String name = "RSH";
        player = new Player(name);
        frame = (NormalFrame) player.startFrame();

        for (int i = 0; i < 8; i++) {
            frame = (NormalFrame) frame.nextFrame(10);
        }
    }

    @Test
    @DisplayName("마지막 프레임 생성")
    void createFinalFrame() {
        frame = (NormalFrame) frame.nextFrame(10);
        FinalFrame finalFrame = (FinalFrame) frame.nextFrame(10);
        assertThat(finalFrame).isEqualTo(new FinalFrame(10));
    }

    @Test
    @DisplayName("마지막 프레임 9번째 프레임에서 생성 오류")
    void createFinalFrameException() {
        assertThatThrownBy(() -> {
            FinalFrame finalFrame = (FinalFrame) frame.nextFrame(10);
        }).isInstanceOf(ClassCastException.class);
    }

    @Test
    @DisplayName("마지막 프레임 마지막 공")
    void finalBall() {
        frame = (NormalFrame) frame.nextFrame(10);
        FinalFrame finalFrame = (FinalFrame) frame.nextFrame(2);
        finalFrame.secondBall(8);
        finalFrame.finalBall(10);

        assertThat(finalFrame.frameScore()).isEqualTo(20);
    }

    @Test
    @DisplayName("마지막 프레임 마지막 공 에러")
    void finalBallValidate() {
        frame = (NormalFrame) frame.nextFrame(10);
        FinalFrame finalFrame = (FinalFrame) frame.nextFrame(2);
        finalFrame.secondBall(3);

        assertThatThrownBy(() ->
                finalFrame.finalBall(10))
                .isInstanceOf(RuntimeException.class);
    }


}
