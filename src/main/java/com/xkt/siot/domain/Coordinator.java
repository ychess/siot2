package com.xkt.siot.domain;
// Generated 2016-4-19 15:14:08 by Hibernate Tools 4.3.1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

/**
 * Coordinator generated by hbm2java
 */
@Entity
@Table(name = "coordinator", catalog = "siotdb", uniqueConstraints = @UniqueConstraint(columnNames = "eui"))
@DynamicInsert(true)
@DynamicUpdate(true)
public class Coordinator implements java.io.Serializable {

    private Integer id;
    private String eui;
    private String ver;
    private byte status;
    private String description;
    private Date updatetime;
    private Date rectime;

    public Coordinator() {
    }

    public Coordinator(String eui, String ver, byte status) {
        this.eui = eui;
        this.ver = ver;
        this.status = status;
    }

    public Coordinator(String eui, String ver, byte status, String description, Date updatetime, Date rectime) {
        this.eui = eui;
        this.ver = ver;
        this.status = status;
        this.description = description;
        this.updatetime = updatetime;
        this.rectime = rectime;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)

    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "eui", unique = true, nullable = false, length = 45)
    public String getEui() {
        return this.eui;
    }

    public void setEui(String eui) {
        this.eui = eui;
    }

    @Column(name = "ver", nullable = false, length = 45)
    public String getVer() {
        return this.ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    @Column(name = "status", nullable = false)
    public byte getStatus() {
        return this.status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    @Column(name = "description", length = 45)
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updatetime", length = 19)
    public Date getUpdatetime() {
        return this.updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "rectime", length = 19)
    public Date getRectime() {
        return this.rectime;
    }

    public void setRectime(Date rectime) {
        this.rectime = rectime;
    }
}
