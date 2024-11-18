
package lk.ijse.pos_back_end.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetail {
    private String orderId;
    private String id;
    private String proId;
    private int qtyOnHand;
    private int unitPrice;
}
