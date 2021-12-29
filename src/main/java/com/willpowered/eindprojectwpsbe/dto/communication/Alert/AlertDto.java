package com.willpowered.eindprojectwpsbe.dto.communication.Alert;

import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import lombok.var;

public class AlertDto {

    public Long id;
    public String title;
    public String text;
    public Portal portal;

    public static AlertDto fromAlert(Alert alert) {

        var Dto = new AlertDto();

        Dto.id = alert.getId();
        Dto.title = alert.getTitle();
        Dto.text = alert.getText();
        Dto.portal = alert.getPortal();

        return Dto;
    }


}