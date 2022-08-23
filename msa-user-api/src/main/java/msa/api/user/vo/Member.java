package msa.api.user.vo;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Member implements Serializable {

    @Id
    @GeneratedValue
    Long id;
    String name;
    public Member() {}
    public Member(String name) {
        this.name = name;
    }

    public static Member join(@NonNull String name) {
        return new Member(name);
    }
}
