package com.example.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name="event_leave")
public class LeaveEntity implements Serializable {
    @Id
    private Long id;
    private String processInstanceId;
    private String userId;
    private Date StartTime;
    private Date endTime;
    private String reason;
    private String leaderApproved;
}
