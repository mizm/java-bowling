package bowling.domain.frames;

import bowling.domain.KnockDownPins;
import bowling.dto.FrameDto;
import bowling.dto.FramesDto;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Frames {
    public static final int MAX_FRAME_SIZE = 10;
    private final LinkedList<Frame> value;

    private Frames() {
        value = new LinkedList<>();
        value.add(Frame.getFirstFrame());
    }

    public static Frames init() {
        return new Frames();
    }

    public void setKnockDownPins(KnockDownPins knockDownPins) {
        Frame currentFrame = value.getLast();
        currentFrame.setKnockDownPins(knockDownPins);

        if (currentFrame.isEnd() && value.size() < MAX_FRAME_SIZE) {
            int currentFrameIndex = getCurrentFrameIndex();
            Frame nextFrame = currentFrame.getNextFrame(currentFrameIndex + 1);
            value.add(nextFrame);
        }
    }

    public boolean isEnd() {
        return value.size() == MAX_FRAME_SIZE && value.getLast().isEnd();
    }

    public int getCurrentFrameIndex() {
        return value.size();
    }

    public FramesDto convertToDto() {
        List<FrameDto> frameDtos = new ArrayList<>();
        Integer previousTotalScore = 0;
        for (Frame frame : value) {
            FrameDto frameDto = frame.convertToFrameDto(previousTotalScore);
            frameDtos.add(frameDto);
            previousTotalScore = frameDto.getTotalScore();
        }
        return FramesDto.of(frameDtos);
    }

    @Override
    public String toString() {
        return "Frames2{" +
                "value=" + value +
                '}';
    }
}
