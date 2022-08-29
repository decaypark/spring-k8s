package msa.api.user.vo;

import lombok.Data;
import org.codehaus.jackson.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.List;

@Data
public class CodeUser implements Serializable  {

    @JsonIgnore
    Code code;
    @JsonIgnore
    Member member;
}
