package pojo.response;

import lombok.Data;

/**
 * @author Pratikshit Bansal
 */
@Data
public class CreateUserResponse {
    private String name;
    private String job;
    private String id;
    private String createdAt;
}
