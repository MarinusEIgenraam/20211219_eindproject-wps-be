package com.willpowered.eindprojectwpsbe.dto.communication.Alert;

import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import lombok.var;

public class AlertDTO {

    public Long id;
    public String title;
    public String text;
    public Portal portal;

    public static AlertDTO fromAlert(Alert alert) {

        var dto = new AlertDTO();

        dto.id = alert.getId();
        dto.title = alert.getTitle();
        dto.text = alert.getText();
        dto.portal = alert.getPortal();

        return dto;
    }


}