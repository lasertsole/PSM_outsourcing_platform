package com.psm.service;
import com.psm.mapper.AccountMapper;
import com.psm.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShowcaseService {
    public Result<?> getShowcaseBoxes(String token) {
        System.out.println("成功接收");
        return Result.success("成功接收");
    }
}
