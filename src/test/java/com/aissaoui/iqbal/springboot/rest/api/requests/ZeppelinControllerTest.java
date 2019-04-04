package com.aissaoui.iqbal.springboot.rest.api.requests;

import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinRequest;
import com.aissaoui.iqbal.springboot.rest.api.model.ZeppelinResult;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.test.utility.Order;
import com.hackerrank.test.utility.OrderedTestRunner;
import com.hackerrank.test.utility.TestWatchman;
import java.util.UUID;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestWatcher;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.rules.SpringClassRule;
import org.springframework.test.context.junit4.rules.SpringMethodRule;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(OrderedTestRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ZeppelinControllerTest {

    @ClassRule
    public static final SpringClassRule springClassRule = new SpringClassRule();

    @Rule
    public final SpringMethodRule springMethodRule = new SpringMethodRule();

    @Rule
    public TestWatcher watchman = TestWatchman.watchman;

    @Autowired
    private MockMvc mockMvc;

    @BeforeClass
    public static void setUpClass() {
        TestWatchman.watchman.registerClass(ZeppelinControllerTest.class);
    }

    @AfterClass
    public static void tearDownClass() {
        TestWatchman.watchman.createReport(ZeppelinControllerTest.class);
    }

    /**
     *
     * @throws Exception
     *
     * It tests response the first task
     */
    @Test
    @Order(1)
    public void testTask() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        ZeppelinRequest zRequest = new ZeppelinRequest();
        zRequest.setCode("%python 1+1");
        zRequest.setSessionid(UUID.randomUUID().toString());
        String strRequest = mapper.writeValueAsString(zRequest);

        ZeppelinResult zResult = new ZeppelinResult();
        zResult.setResult("2");
        String strResult = mapper.writeValueAsString(zResult);

        String response = mockMvc.perform(
                MockMvcRequestBuilders.get("/execute").contentType(MediaType.APPLICATION_JSON).content(strRequest))
                .andExpect(MockMvcResultMatchers.content().json(strResult)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        ;

        Assert.assertEquals(response, strResult);
    }

    @Test
    @Order(2)
    public void testChallenge1Phase1() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        ZeppelinRequest zRequest = new ZeppelinRequest();
        zRequest.setCode("%python a=1");
        zRequest.setSessionid("known_user1");
        String strRequest = mapper.writeValueAsString(zRequest);

        ZeppelinResult zResult = new ZeppelinResult();
        zResult.setResult("");
        String strResult = mapper.writeValueAsString(zResult);

        String response = mockMvc.perform(
                MockMvcRequestBuilders.get("/execute").contentType(MediaType.APPLICATION_JSON).content(strRequest))
                .andExpect(MockMvcResultMatchers.content().json(strResult)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        ;

        Assert.assertEquals(response, strResult);
        
        
 
    }

    @Test
    @Order(3)
    public void testChallenge1Phase2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        
        ZeppelinRequest zRequest = new ZeppelinRequest();
        zRequest.setCode("%python a+7");
        zRequest.setSessionid("known_user1"); 
        String strRequest = mapper.writeValueAsString(zRequest);

        ZeppelinResult zResult = new ZeppelinResult();
        zResult.setResult("8");
        String strResult = mapper.writeValueAsString(zResult);

        String response = mockMvc.perform(
                MockMvcRequestBuilders.get("/execute").contentType(MediaType.APPLICATION_JSON).content(strRequest))
                .andExpect(MockMvcResultMatchers.content().json(strResult)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        ;

        Assert.assertEquals(response, strResult);
    }
    
    
    @Test
    @Order(4)
    public void testChallenge2() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        
        
        ZeppelinRequest zRequest = new ZeppelinRequest();
        zRequest.setCode("%python ax=10");
        zRequest.setSessionid(UUID.randomUUID().toString()); 
        String strRequest = mapper.writeValueAsString(zRequest);

        ZeppelinResult zResult = new ZeppelinResult();
        zResult.setResult("");
        String strResult = mapper.writeValueAsString(zResult);

        String response = mockMvc.perform(
                MockMvcRequestBuilders.get("/execute").contentType(MediaType.APPLICATION_JSON).content(strRequest))
                .andExpect(MockMvcResultMatchers.content().json(strResult)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        ;

        
        zRequest = new ZeppelinRequest();
        zRequest.setCode("%python ax+20");
        zRequest.setSessionid(UUID.randomUUID().toString()); 
         strRequest = mapper.writeValueAsString(zRequest);

         zResult = new ZeppelinResult();
        zResult.setResult("30");
         strResult = mapper.writeValueAsString(zResult);

         response = mockMvc.perform(
                MockMvcRequestBuilders.get("/execute").contentType(MediaType.APPLICATION_JSON).content(strRequest))
                .andExpect(MockMvcResultMatchers.content().json(strResult)).andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        ;
        
        
        Assert.assertNotEquals(response, "30");
        
    }

}
