package qr_code.models.model;

import javax.persistence.*;

@Entity
@Table(name = "config_table")
public class Config {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 12000)
    private String htmlConfig;

    @Column
    private String subject;

    @Column
    private int target;


    public Config(String htmlConfig, String subject, int target) {
        this.htmlConfig = htmlConfig;
        this.subject = subject;
        this.target = target;
    }

    public int getTarget() {
        return target;
    }

    public void setTarget(int target) {
        this.target = target;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Config() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHtmlConfig() {
        return htmlConfig;
    }

    public void setHtmlConfig(String toAddress) {
        this.htmlConfig = toAddress;
    }
}
