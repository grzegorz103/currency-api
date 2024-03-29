package currencies.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserOut {

    private String name;

    private String surname;

    private String pesel;

    private Long bankAccountId;

}
