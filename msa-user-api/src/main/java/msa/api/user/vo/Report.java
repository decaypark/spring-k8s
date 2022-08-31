package msa.api.user.vo;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
public class Report implements Serializable {

    @Id
    @GeneratedValue
    Long id;

    String tstcd;
    Long count;
    Date date;
    public Report() {}
    public Report(String tstcd, Long count, Date date) {
        this.tstcd = tstcd;
        this.count = count;
        this.date = date;
    }

    public static Report join(@NonNull String tstcd, Long count, Date date) {
        return new Report(tstcd, count, date);
    }
}
