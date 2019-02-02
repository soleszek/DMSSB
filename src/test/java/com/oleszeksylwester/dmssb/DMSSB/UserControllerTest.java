package com.oleszeksylwester.dmssb.DMSSB;

import com.oleszeksylwester.dmssb.DMSSB.controller.UserController;
import com.oleszeksylwester.dmssb.DMSSB.service.UserService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    /*@Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;*/
}
