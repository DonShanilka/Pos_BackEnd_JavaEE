
package lk.ijse.pos_back_end.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {
    private String code;
    private String itemName;
    private int unitPrice;
    private int qtyOnHand;
}
