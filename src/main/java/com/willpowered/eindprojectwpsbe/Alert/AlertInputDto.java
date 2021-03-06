package com.willpowered.eindprojectwpsbe.Alert;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public class AlertInputDto {

    public Long id;
    public String title;
    public String text;
    public Long portalId;
    public LocalDate createdAt;

    public Alert toAlert() {

        var alert = new Alert();

        alert.setId(id);
        alert.setTitle(title);
        alert.setText(text);
        alert.setCreatedAt(createdAt);


        return alert;
    }
}