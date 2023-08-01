package pojo.request;

import lombok.Builder;
import lombok.Getter;

/**
 * @author Pratikshit Bansal
 */
@Getter
@Builder
public class CreateUser {
    private String name;
    private String job;
}
