package com.xkt.siot.domain;
// Generated 2016-4-19 15:14:08 by Hibernate Tools 4.3.1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cdorm generated by hbm2java
 */
@Entity
@Table(name = "cdorm", catalog = "siotdb")
public class Cdorm implements java.io.Serializable {

    private Integer id;
    private int cid;
    private int did;

    public Cdorm() {
    }

    public Cdorm(int cid, int did) {
        this.cid = cid;
        this.did = did;
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

    @Column(name = "cid", nullable = false)
    public int getCid() {
        return this.cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Column(name = "did", nullable = false)
    public int getDid() {
        return this.did;
    }

    public void setDid(int did) {
        this.did = did;
    }
}
