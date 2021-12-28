package com.willpowered.eindprojectwpsbe.dto.communication.Alert;

import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import lombok.var;

public class AlertInputDTO {

    public Long id;
    public String title;
    public String text;
    public Portal portal;

    public Alert toAlert() {

        var alert = new Alert();

        alert.setId(id);
        alert.setTitle(title);
        alert.setText(text);
        alert.setPortal(portal);

        return alert;
    }
}