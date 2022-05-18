package com.example.rzdwebapp.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Arrays;


@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class DelayId implements Serializable {
    public DelayId(String strId) {
        int[] idArr = Arrays.stream(strId.split("_")).mapToInt(Integer::parseInt).toArray();
        this.trainScheduleId = idArr[0];
        this.routeStationId = idArr[1];
    }

    private Integer trainScheduleId;
    private Integer routeStationId;
}
