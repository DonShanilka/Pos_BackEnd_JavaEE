
package lk.ijse.pos_back_end.Controller;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.ijse.pos_back_end.BO.Bo.ItemBO;
import lk.ijse.pos_back_end.BO.Impl.ItemBOImpl;
import lk.ijse.pos_back_end.DAO.Impl.ItemDAOImpl;
import lk.ijse.pos_back_end.Dto.ItemDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/item",loadOnStartup = 2)
public class ItemController extends HttpServlet {
    static Logger logger = LoggerFactory.getLogger(ItemController.class);
    Connection connection;
    ItemBO itemBO = new ItemBOImpl();

    @Override
    public void init() throws ServletException {
        logger.info("Initialize ItemController");
        try{
            var ctx = new InitialContext();
            DataSource pool = (DataSource) ctx.lookup("java:comp/env/jdbc/PosBackEnd");
            this.connection = pool.getConnection();
        }catch (NamingException | SQLException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null) {
            //send error
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }

        try(var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto = jsonb.fromJson(req.getReader(), ItemDto.class);

            var saveItem = itemBO.saveItem(itemDto, connection);
            writer.write(saveItem);

            logger.info("item saved");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (!req.getContentType().toLowerCase().startsWith("application/json") || req.getContentType() == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
        try (var writer = resp.getWriter()){
            Jsonb jsonb = JsonbBuilder.create();
            ItemDto itemDto = jsonb.fromJson(req.getReader(), ItemDto.class);
            var updateItem = itemBO.updateItem(itemDto, connection);
            writer.write(updateItem);

            logger.info("Item Updated");

        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try (var writer = resp.getWriter()) {
            List<ItemDto> items = itemBO.getAllItems(connection);

            Jsonb jsonb = JsonbBuilder.create();
            String json = jsonb.toJson(items);

            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            writer.write(json);

            logger.info("All item Details are retrieved");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        try (var writer = resp.getWriter()) {
            var deleteItem = itemBO.deleteItem(code, connection);

            writer.write(deleteItem);
            logger.info("Delete Item");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
