package com.willpowered.eindprojectwpsbe.Alert;

import com.willpowered.eindprojectwpsbe.Portal.PortalDto;
import lombok.var;

public class AlertDto {

    public Long id;
    public String title;
    public String text;
    public PortalDto portal;

    public static AlertDto fromAlert(Alert alert) {

        var Dto = new AlertDto();

        Dto.id = alert.getId();
        Dto.title = alert.getTitle();
        Dto.text = alert.getText();
        Dto.portal = PortalDto.fromPortal(alert.getPortal());

        return Dto;
    }


}