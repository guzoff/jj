package com.guzoff.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "CONVICT")
public class Convict implements Serializable {

    private static final Map<String, String> CRIME_MAP = new HashMap<>();
    
    // 8 crimes: 1 crime for every bit in a byte
    static {
        CRIME_MAP.put("1", "Encapsulated inheritance inside polymorphism and called it abstraction");
        CRIME_MAP.put("2", "Was too public in his/her methods");
        CRIME_MAP.put("3", "Turned SOLID into SID");
        CRIME_MAP.put("4", "Never synchronized");
        CRIME_MAP.put("5", "Always synchronized");
        CRIME_MAP.put("6", "Was too wet - never DRY");
        CRIME_MAP.put("7", "Washed the REST with the SOAP");
        CRIME_MAP.put("8", "Thought that he/she was unsigned");
    }
    
    public Map<String, String> getCRIME_MAP() {
        return CRIME_MAP;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 3, max = 50)
    @Column(name = "NICKNAME", nullable = false)
    private String nickname;

    @NotNull
    @Pattern(regexp = "[FM]")
    @Column(name = "SEX", nullable = false)
    private String sex;

    @NotNull
    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "JAILING_DATE", nullable = false)
    private LocalDate jailingDate;

    @NotNull
    @Pattern(regexp = "[1-8]")
    @Column(name = "CRIME_CODE", nullable = false)
    private String crimeCode;

    @NotNull
    @Digits(integer = 3, fraction = 0)
    @Column(name = "SENTENCE_PERIOD", nullable = false)
    private Short sentencePeriod;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDate getJailingDate() {
        return jailingDate;
    }

    public void setJailingDate(LocalDate jailingDate) {
        this.jailingDate = jailingDate;
    }

    public String getCrimeCode() {
        return crimeCode;
    }

    public void setCrimeCode(String crimeCode) {
        this.crimeCode = crimeCode;
    }
    
    /*
     *  Method for obtaining "binary" version of crimeCode
     */
    public int getIntCode() {
        int code = 1;
        for (int i = 1; i < Integer.parseInt(crimeCode); i++) {
            code *= 10;
        }
        return code;
    }

    public Short getSentencePeriod() {
        return sentencePeriod;
    }

    public void setSentencePeriod(Short sentencePeriod) {
        this.sentencePeriod = sentencePeriod;
    }

    @Override
    public int hashCode() {
        return (nickname == null) ? 0 : nickname.hashCode();        
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj instanceof Convict) {
            Convict other = (Convict) obj;
            if (id.equals(other.id)) {
                return (nickname == null)
                        ? other.nickname == null
                        : nickname.equals(other.nickname);
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Convict [id=" + id + ", nickname=" + nickname + ", jailingDate="
                + jailingDate + ", crimeCode=" + crimeCode
                + ", sentencePeriod=" + sentencePeriod + "]";
    }

}
