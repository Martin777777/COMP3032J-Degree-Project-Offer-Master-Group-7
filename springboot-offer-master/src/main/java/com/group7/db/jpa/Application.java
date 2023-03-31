package com.group7.db.jpa;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.group7.db.jpa.utils.ERound;
import com.group7.db.jpa.utils.EStatus;
import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: WangYuyang
 * @Date: 2023/3/6-20:27
 * @Project: COMP3032J_FYP_Thesis_Group_7
 * @Package: com.group7.db.jpa
 * @Description:
 **/
@Entity
@Table(name = "application",
        uniqueConstraints = {
        })
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    @JsonBackReference
    private User user;

    @ManyToOne
    @JsonBackReference
    private Program program;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EStatus eStatus = EStatus.NULL;

    @Temporal(TemporalType.DATE)
    private Date deadline;

    @Temporal(TemporalType.TIMESTAMP)
    private Date reportedTime;

    @Column
    @Enumerated(EnumType.STRING)
    private ERound eRound;


    public Application(User user, Program program) {
        this.user = user;
        this.program = program;
        // default status
        this.eStatus = EStatus.AWAITING_REVIEW;
        // default application round
        this.eRound = ERound.FALL_2023;
    }

    public Application(User user, Program program, EStatus eStatus, Date deadline, ERound eRound, Date reportedTime) {
        this.user = user;
        this.program = program;
        this.eStatus = eStatus;
        this.deadline = deadline;
        this.eRound = eRound;
        this.reportedTime = reportedTime;
    }


    public Application() {
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EStatus geteStatus() {
        return eStatus;
    }

    public void seteStatus(EStatus eStatus) {
        this.eStatus = eStatus;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getReportedTime() {
        return reportedTime;
    }

    public void setReportedTime(Date reportedTime) {
        this.reportedTime = reportedTime;
    }

    public ERound geteRound() {
        return eRound;
    }

    public void seteRound(ERound eRound) {
        this.eRound = eRound;
    }
}
