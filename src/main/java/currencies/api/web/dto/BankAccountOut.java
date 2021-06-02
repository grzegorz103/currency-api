package currencies.api.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountOut {

    private Long id;

    private String number;

    private List<SaldoOut> saldos;

}
