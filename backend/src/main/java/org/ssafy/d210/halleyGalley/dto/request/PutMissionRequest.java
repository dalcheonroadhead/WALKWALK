package org.ssafy.d210.halleyGalley.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PutMissionRequest {
    private List<Long> memberIdList;
}
