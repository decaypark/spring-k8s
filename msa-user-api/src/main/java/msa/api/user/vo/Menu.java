package msa.api.user.vo;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Menu implements Serializable {

    @Id
    @GeneratedValue
    Long id;
    String name;
    public Menu() {}
    public Menu(String name) {
        this.name = name;
    }

    public static Menu join(@NonNull String code, @NonNull String name) {
        return new Menu(name);
    }
}
