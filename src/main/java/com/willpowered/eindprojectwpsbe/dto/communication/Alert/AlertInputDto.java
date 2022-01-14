package com.willpowered.eindprojectwpsbe.dto.communication.Alert;

import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import lombok.var;

public class AlertInputDto {

    public Long id;
    public String title;
    public String text;
    public Long portalId;

    public Alert toAlert() {

        var alert = new Alert();

        alert.setId(id);
        alert.setTitle(title);
        alert.setText(text);

        return alert;
    }
}