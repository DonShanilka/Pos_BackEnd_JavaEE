
package lk.ijse.pos_back_end.BO;

import lk.ijse.pos_back_end.BO.Impl.CustomerBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER_BO, ITEM_BO, PURCHASE_ORDER_BO,Detail_BO
    }

    public <T extends SuperBO> T getBO(BOTypes boTypes) {
        switch (boTypes) {
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();
            case ITEM_BO:
                return null;
            case PURCHASE_ORDER_BO:
                return null;
            case Detail_BO:
                return null;
            default:
                return null;
        }
    }
}
