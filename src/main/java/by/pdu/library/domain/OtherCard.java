package by.pdu.library.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class OtherCard extends Card {
    private String registration;
    private String passport;
    private String work;
}
