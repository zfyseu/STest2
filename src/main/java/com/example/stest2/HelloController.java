package com.example.stest2;

import com.example.client.VersionServiceClient;
import com.example.dao.GoodsDao;
import com.example.entity.Goods;
import com.example.service.DubboTest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class HelloController {
    @Resource
    private GoodsDao goodsDao;
    @Autowired
    private DubboTest dubboTest;
    @Autowired
    private VersionServiceClient versionServiceClient;

    @RequestMapping("hello")
    @ResponseBody
    public String hello() {
       // return "hello springboot";
        dubboTest.test();
        return goodsDao.getGoods();
    }

    @GetMapping("testSpringCloud")
    public void testSpringCloud() {
        System.out.println(versionServiceClient.getVersion());
    }

    @RequestMapping("/test")
    public String welcomPage() {
        System.out.println("welcomPage");
        return "index";
    }

    @RequestMapping(value = "/insertGoods", method = RequestMethod.GET)
    @ResponseBody
    public String insertGoods(@RequestParam(value = "name") String name,
                              @RequestParam(value = "brand") String brand) {
        return String.valueOf(goodsDao.insertGoodsJPA(name, brand));
        //return String.valueOf(goodsDao.insertGoods(name, brand));
    }

    public static void main(String[] args) {
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        JsonObject objRet = new JsonObject();
        objRet.addProperty("name", "skii");
        objRet.addProperty("hh", 100);
        Goods goods = gson.fromJson(objRet, Goods.class);
        BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(goods);
        Object obj = parameterSource.getValue("inventory");
        System.out.println(66);
    }
}
