package rentCar.controller;

/**
 * Created by xpb on 2017/6/28.
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.server.MockMvc;
import org.springframework.test.web.server.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-mybatis.xml","classpath:spring-mvc.xml"})

@WebAppConfiguration
public class CustomerControllerTest {
    @Autowired
    protected WebApplicationContext wac;

    protected MockMvc mockMvc ;
    private String customerAddUrl="/Car/Add";
    private String customerListUrl="/Car/List";
    private String customerQueryUrl="/Car/Query";
    private String registerUrl="/Customer/Register";
    private String jsonStringCustomerRegister="{'customerId':'222222110','customerTel':'05511111111'," +
            "'customerPassword':'111','recordCreator':'0',,'stuffEmail':'9698@qq.com'}";
    private String jsonStringCustomerRegister1="{'customerId':'111100000','customerTel':'05510000000'," +
            "'customerPassword':'111','recordCreator':'0',,'stuffEmail':'9999@qq.com'}";

    //private String modelInfoId;  name1
    //carNo
    @Before()  //这个方法在每个方法执行之前都会执行一遍
    public void setup()
    {
        mockMvc = MockMvcBuilders.webApplicationContextSetup(wac).build();
    }
    @Test
    public void customerRegisterTest() throws Exception
    {
        String responseString = mockMvc.perform
                (
                        post(registerUrl, "json").characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(jsonStringCustomerRegister1.getBytes())

                )
                //  .andExpect(status().isOk())    //返回的状态是200
                .andDo(print())       //打印出请求和相应的内容
                //.andReturn();
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        //System.out.println("-----返回的json = " + responseString);
    }
    @Test
    public void customerQueryTest() throws Exception
    {
        String responseString = mockMvc.perform
                (
                        get(customerQueryUrl, "json").characterEncoding("UTF-8")
                                .contentType(MediaType.APPLICATION_JSON)
                               // .body(null)

                )
                //  .andExpect(status().isOk())    //返回的状态是200
                .andDo(print())       //打印出请求和相应的内容
                //.andReturn();
                .andReturn().getResponse().getContentAsString();   //将相应的数据转换为字符串
        //System.out.println("-----返回的json = " + responseString);
    }
}
