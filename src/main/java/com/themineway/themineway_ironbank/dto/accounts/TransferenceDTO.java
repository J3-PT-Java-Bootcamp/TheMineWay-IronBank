package com.themineway.themineway_ironbank.dto.accounts;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransferenceDTO {
    @NotNull
    public int from;

    @NotNull
    public int to;

    @NotNull
    @DecimalMin("0")
    public BigDecimal amount;
}
