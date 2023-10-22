package com.psm.fetcher.Aop;

import com.netflix.graphql.dgs.context.DgsContext;
import com.psm.custom.GraphQLException;
import com.psm.entity.AccountEntity;
import com.psm.mapper.AccountMapper;
import com.psm.utils.IdentiUitil;
import graphql.schema.DataFetchingEnvironment;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

/**
 * @BelongsProject: psm-backend
 * @BelongsPackage: com.psm.fetcher.Aop
 * @Author: ChenLan
 * @Date: 2023/10/22 16:43
 * @Description:检查用户是否有权访问fetcher
 */
@Component
@Aspect
public class TokenIdentAop {

    @Autowired
    private AccountMapper accountMapper;

    public static Object IdentiToken(DataFetchingEnvironment dfe){
        Object object = DgsContext.getCustomContext(dfe);
        if (object instanceof GraphQLException){
            throw (GraphQLException)object;
        }
        return object;
    }

    @Around("@annotation(com.psm.fetcher.Aop.annotation.IdentifyToken)")
    public Object tokenIdent(ProceedingJoinPoint pjp) throws Throwable{
        Object[] args = pjp.getArgs();
        List<AccountEntity> list = accountMapper.findByToken((String) args[args.length-1]);

        if(list.size()==0){
            throw  new GraphQLException("401", "无效token");
        }
        else if (!list.get(0).getStatus().equals("1")) {
            throw new GraphQLException("403", "账号不可用");
        }

        Object result = pjp.proceed();
        return result;
    }
}
