package com.example.api.util;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperFactory {


    private ModelMapper modelMapper(){
        return new ModelMapper();
    }

    private ModelMapper modelMapper(MatchingStrategy matchingStrategy){
        ModelMapper modelMapper = modelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(matchingStrategy);
        return modelMapper;
    }

    public ModelMapper modelMapperMatchingStrategyStrict(){
        return modelMapper(MatchingStrategies.STRICT);
    }

    public ModelMapper modelMapperMatchingStrategyStrictAndNotNull(){
        ModelMapper modelMapper = modelMapperMatchingStrategyStrict();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}
