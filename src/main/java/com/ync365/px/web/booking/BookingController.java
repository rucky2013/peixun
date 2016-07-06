package com.ync365.px.web.booking;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ync365.px.entity.PxProject;
import com.ync365.px.service.booking.BookingService;

/**
 * 
 *     
 * @Title：BookingController  
 * @Description: TODO   
 * @author: zhangdong      
 * @date: 2016年1月6日 上午10:59:53      
 * @version     
 *
 */
@Controller
@RequestMapping(value = "/booking")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @RequestMapping(value = "/tograbticket")
    public String tograbticket(Map<String, Object> model,Long projectId) {
        if(bookingService.isTaked(projectId)){
            return "booking/success";
        }else{
            PxProject pxProject = bookingService.getProjectFormRedis(projectId);
            model.put("pxProject", pxProject);
            model.put("num", bookingService.getHaveTicketNum(projectId));
            return "booking/grabticket";
        }
    }

    @RequestMapping(value = "/grab",method = RequestMethod.POST)
    public String grab(int projectId, HttpServletResponse response) {
        if (bookingService.booking(projectId)) {
            return "booking/success";
        } else {
            return "booking/fail";
        }
    }
    
}
