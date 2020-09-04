package com.helloxin;

import com.helloxin.param.RecordParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * Created by yexin on 2019/11/22.
 */
@RestController
@Slf4j
public class HelloController {

    @GetMapping("hello")
    @RecordParam(channel= "test",requestData = "0")
    public String sayhello(@RequestParam("name") String name) throws IOException {
        return "hello:"+name;
    }
}
