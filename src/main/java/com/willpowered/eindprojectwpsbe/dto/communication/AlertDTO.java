package com.willpowered.eindprojectwpsbe.dto.communication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlertDTO {

    private Long id;
    private String title;
    private String text;
    private Long profileId;
}