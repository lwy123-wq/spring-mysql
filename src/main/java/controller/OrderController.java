package controller;

import entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.OrderServerImpl;

@Controller
public class OrderController {
    @Autowired
    private OrderServerImpl orderServer;
    @RequestMapping(value = "add_order", method = RequestMethod.POST)
    @ResponseBody
    public String deleteOrder(@RequestBody Order order) {
        int result =orderServer.deleteOrder(order);
        if (result != 0) {
            return "sucess delete" + order.getOrderName();
        }
        return "删除失败";
    }
}
