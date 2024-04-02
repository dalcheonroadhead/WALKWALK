package org.ssafy.d210.walk.dto.response;

import lombok.Getter;
import org.springframework.data.domain.Slice;

import java.util.List;

@Getter
public class SliceResponseDto<T> {
    private final List<T> content;
    private final int currentPage;
    private final int size;
    private final boolean first;
    private final boolean last;
    private Long userRank;
    private Integer userRankPage;

    public SliceResponseDto(Slice<T> sliceContent, Long userRank, Integer userRankPage) {
        this.content = sliceContent.getContent();
        this.currentPage = sliceContent.getNumber();
        this.size = sliceContent.getSize();
        this.first = sliceContent.isFirst();
        this.last = sliceContent.isLast();
        this.userRank = userRank;
        this.userRankPage = userRankPage;
    }

}
