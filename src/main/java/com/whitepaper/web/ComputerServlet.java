package com.whitepaper.web;

import com.google.gson.Gson;
import com.whitepaper.pojo.ListOfComputer;
import com.whitepaper.service.ComputerService;
import com.whitepaper.service.ComputerServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ComputerServlet extends BaseServlet {

    ComputerService computerService = new ComputerServiceImpl();

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        if (action != null && action.equals("list"))
//            list(req, resp);
//    }

    public void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cpu = req.getParameter("cpu");
        String gpu = req.getParameter("gpu");
        String memory = req.getParameter("memory");
        String power = req.getParameter("power");
        String pccAndMotherboard = req.getParameter("pccAndMotherboard");
        String ssd = req.getParameter("ssd");
        String hdd = req.getParameter("hdd");
        Double maximum = Double.parseDouble(req.getParameter("maximum"));
        List<ListOfComputer> listOfComputers = computerService.queryForListOfComputer(cpu, gpu, memory, power, pccAndMotherboard, ssd, hdd);
//        for (ListOfComputer each : listOfComputers){
//            if (each.getHdd() != null)
//                System.out.println("有的");
//        }
        int number = 20;
        List<ListOfComputer> list = computerService.recommandList(listOfComputers, maximum, number);
        //存到session域中
//        req.getSession().setAttribute("list", list);
        //存到ServletContext域中
        this.getServletConfig().getServletContext().setAttribute("list", list);
        //转成json文件
        Gson gson = new Gson();
        String json = gson.toJson(list);
        System.out.println(json);
        resp.getWriter().write(json);
    }

    public void save(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("index");
//        List<ListOfComputer> list = (List<ListOfComputer>) req.getSession().getAttribute("list");
        List<ListOfComputer> list = (List<ListOfComputer>) this.getServletConfig().getServletContext().getAttribute("list");
        ListOfComputer listOfComputer = list.get(Integer.parseInt(index));
        computerService.addComputer(listOfComputer);
    }


    public void showCollection(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ListOfComputer> saveList = computerService.showData();
        //存到session域中
//        req.getSession().setAttribute("saveList", saveList);
        //存到ServletContext域中
//        this.getServletConfig().getServletContext().setAttribute("saveList", saveList);
        //转成json文件
        Gson gson = new Gson();
        String json = gson.toJson(saveList);
        System.out.println(json);
        resp.getWriter().write(json);
    }

    public void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String index = req.getParameter("index");
        List<ListOfComputer> list = computerService.showData();
        ListOfComputer listOfComputer = list.get(Integer.parseInt(index));
        long count = computerService.deleteComputer(listOfComputer);
        if (count == 1){
            resp.getWriter().write(1);
        }else {
            resp.getWriter().write(0);
        }
    }
}
