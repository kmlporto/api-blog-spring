package com.example.api.util;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.security.InvalidParameterException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class Converter {

    private final ModelMapperFactory modelMapperFactory;

    public <S, D> void map(S source, D destiny){
        map(source, destiny, modelMapperFactory.modelMapperMatchingStrategyStrict());
    }

    public <S, D> void mapWithOutNull(S source, D destiny){
        map(source, destiny, modelMapperFactory.modelMapperMatchingStrategyStrictAndNotNull());
    }

    public <S, D> D map(S source, Class<D> destinationType){
        return map(source, destinationType, modelMapperFactory.modelMapperMatchingStrategyStrict());
    }

    private <S, D> D map(S source, Class<D> destinationType, ModelMapper modelMapper){
        Optional.ofNullable(source).orElseThrow(InvalidParameterException::new);
        Optional.ofNullable(destinationType).orElseThrow(InvalidParameterException::new);
        Optional.ofNullable(modelMapper).orElseThrow(InvalidParameterException::new);
        return modelMapper.map(source, destinationType);
    }

    private <S, D> void map(S source, D destiny, ModelMapper modelMapper){
        Optional.ofNullable(source).orElseThrow(InvalidParameterException::new);
        Optional.ofNullable(destiny).orElseThrow(InvalidParameterException::new);
        Optional.ofNullable(modelMapper).orElseThrow(InvalidParameterException::new);
        modelMapper.map(source, destiny);
    }

}
