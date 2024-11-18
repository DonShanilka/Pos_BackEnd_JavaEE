
package lk.ijse.pos_back_end.Dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    private String id;
    private String name;
    private String address;
    private int salary;
}
