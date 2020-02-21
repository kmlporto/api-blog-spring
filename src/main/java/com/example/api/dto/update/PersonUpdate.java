package com.example.api.dto.update;

import com.sun.istack.NotNull;
import lombok.Data;

@Data
public class PersonUpdate {

    @NotNull
    private String nome;

    @NotNull
    private String email;

}
