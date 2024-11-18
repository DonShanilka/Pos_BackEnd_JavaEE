
package lk.ijse.pos_back_end.Controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos_back_end.BO.Bo.ItemBO;
import lk.ijse.pos_back_end.BO.Bo.OrderBO;
import lk.ijse.pos_back_end.BO.Bo.OrderDetailsBO;
import lk.ijse.pos_back_end.BO.Impl.ItemBOImpl;
import lk.ijse.pos_back_end.BO.Impl.OrderBOImpl;
import lk.ijse.pos_back_end.BO.Impl.OrderDetailsBOImpl;
import lk.ijse.pos_back_end.Dto.ItemDto;
import lk.ijse.pos_back_end.Dto.OrderDetailDto;
import lk.ijse.pos_back_end.Dto.OrderDto;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class OrderController extends HttpServlet {
    static Logger logger = LoggerFactory.getLogger(OrderController.class);
    Connection connection;
    OrderBO orderBO = new OrderBOImpl();
    OrderDetailsBO orderDetailsBO = new OrderDetailsBOImpl();
    ItemBO itemBO = new ItemBOImpl();
    boolean isPlaced = false;

    @Override
    public void init() throws ServletException {
        logger.info("initialize OrderController");
        try {
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/PosBackEnd");
            this.connection = pool.getConnection();
        } catch (NamingException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            //send error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try{
            Jsonb jsonb = JsonbBuilder.create();
            List<OrderDetailDto> detailsDto = jsonb.fromJson(req.getReader(), new ArrayList<OrderDetailDto>(){}.getClass().getGenericSuperclass());

            OrderDto orderDto = new OrderDto(
                    detailsDto.get(0).getOrderId(),
                    detailsDto.get(0).getCusId(),
                    LocalDate.now()
            );

            List<ItemDto> productList = new ArrayList<>();

            for (OrderDetailDto dto : detailsDto) {

                productList.add(new ItemDto(
                        dto.getProId(),
                        null,
                        0,
                        dto.getQtyOnHand()
                ));
            }

            placeOrder(detailsDto,orderDto,productList);

            if (isPlaced) {
                resp.getWriter().println("Placed");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }

    public void placeOrder( List<OrderDetailDto> detailsDto, OrderDto orderDto,List<ItemDto> productList){
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                try{
                    connection.setAutoCommit(false);

                    boolean isSaved = orderBO.saveOrderBO(orderDto, connection);

                    if (isSaved) {
                        System.out.println("Saved");

                        boolean isUpdated = false;

                        for (ItemDto productDto : productList) {
                            isUpdated = Boolean.parseBoolean(itemBO.updateItem(productDto, connection));
                        }

                        if (isUpdated) {
                            logger.info("Order Placed");
                            boolean isDetailSaved = false;

                            for (OrderDetailDto dto : detailsDto) {
                                isDetailSaved = orderDetailsBO.saveOrderDetailsBO(dto, connection);
                            }

                            System.out.println(isDetailSaved);

                            if (isDetailSaved) {
                                isPlaced =  true;
                                connection.commit();
                            }else{
                                connection.rollback();
                            }


                        }else{
                            connection.rollback();
                        }

                    }else{
                        connection.rollback();
                    }


                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    connection.setAutoCommit(true);
                }
            }
        }).start();

    }
}
