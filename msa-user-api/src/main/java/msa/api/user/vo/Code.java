package msa.api.user.vo;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Code implements Serializable {

    @Id
    @GeneratedValue
    Long id;
    String code;
    String name;
    public Code() {}
    public Code(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public static Code join(@NonNull String code, @NonNull String name) {
        return new Code(code, name);
    }
}