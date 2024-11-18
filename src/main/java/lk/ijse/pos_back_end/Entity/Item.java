
package lk.ijse.pos_back_end.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String code;
    private String itemName;
    private int unitPrice;
    private int qtyOnHand;
}
