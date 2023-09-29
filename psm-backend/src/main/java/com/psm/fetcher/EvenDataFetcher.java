package com.psm.fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.Arrays;
import java.util.List;

@DgsComponent
public class EvenDataFetcher {
    @DgsQuery
    public List<String> events(){
        return Arrays.asList("Reading book", "Watch Movie", "Cooking");
    }

    @DgsMutation
    public String createEvent(@InputArgument String name){
        return name + "Created";
    }
}
