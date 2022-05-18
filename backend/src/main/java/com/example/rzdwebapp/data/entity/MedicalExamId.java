package com.example.rzdwebapp.data.entity;

import lombok.Data;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.sql.Date;

@Data
@Embeddable
class MedicalExamId implements Serializable {
    private Integer staffId;
    private Date date;
}
