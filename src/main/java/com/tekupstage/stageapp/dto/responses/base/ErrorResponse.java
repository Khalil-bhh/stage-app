package com.tekupstage.stageapp.dto.responses.base;


import java.util.ArrayList;

public class ErrorResponse extends AppResponse {


    public ErrorResponse() {
        super(false);
        if (getFullMessages() == null)
            setFullMessages(new ArrayList<>());
    }

}
