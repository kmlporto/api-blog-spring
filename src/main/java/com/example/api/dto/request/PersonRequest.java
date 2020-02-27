package com.example.api.dto.request;


import lombok.Data;

@Data
public class PersonRequest extends GenericRequest{

    private String nome;

    private String email;

}
