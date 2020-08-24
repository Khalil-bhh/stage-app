package com.tekupstage.stageapp.dto.responses.base;

public class SuccessResponse extends AppResponse {
    public SuccessResponse() {
        super(true);
    }

    public SuccessResponse(String message) {
        this();
        addFullMessage(message);
    }

}
