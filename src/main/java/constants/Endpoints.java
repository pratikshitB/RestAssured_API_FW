package constants;

import lombok.Builder;
import lombok.Data;

/**
 * @author Pratikshit Bansal
 */
@Data
@Builder
public class Endpoints {
    public static String GET_USER="/api/users?page=2";
    public static String CREATE_USER="/api/users";
}
