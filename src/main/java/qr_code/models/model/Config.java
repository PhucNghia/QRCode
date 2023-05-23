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

    public Config(String htmlConfig) {
        this.htmlConfig = htmlConfig;
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
