package com.tekupstage.stageapp.dto.base;

public class SuccessResponse extends AppResponse {
    public SuccessResponse() {
        super(true);
    }

    public SuccessResponse(String message) {
        this();
        addFullMessage(message);
    }

    public static SuccessResponse build(String message) {
        return new SuccessResponse(message);
    }

}
