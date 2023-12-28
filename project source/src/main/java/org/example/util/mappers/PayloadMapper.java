package org.example.util.mappers;

import org.example.dto.CreatePayloadDto;
import org.example.dto.CreatePayloadQualificationDto;
import org.example.entity.Payload;
import org.example.entity.PayloadQualification;

public class PayloadMapper {

    public static PayloadQualification dtoToObject(CreatePayloadQualificationDto payloadQualificationDto){
        PayloadQualification qualification = new PayloadQualification();
        qualification.setQualification(payloadQualificationDto.getQualification());
        return qualification;
    }

    public static Payload dtoToObject(CreatePayloadDto payloadDto){
        Payload payload = new Payload();
        payload.setUnit(payloadDto.getUnit());
        payload.setUnitValue(payloadDto.getUnitValue());
        payload.setPayloadQualification(payloadDto.getPayloadQualification());
        return payload;
    }

}
