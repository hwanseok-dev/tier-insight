package io.lucky.server.user.controller.dto;

import lombok.Getter;

@Getter
public class RestResponse {
    private final int status;
    private final Object data;
    private final String message;

    public static RestResponse ok(){
        return new RestResponse(200, null, "ok");
    }
    public static RestResponse ok(Object data){
        return new RestResponse(200, data, "ok");
    }
    public static RestResponse badRequest(String message){
        return new RestResponse(400, null, message);
    }
    public static RestResponse internalServerError(){
        return new RestResponse(500, null, "error");
    }
    public static RestResponse unknownError(){
        return new RestResponse(500, null, "error");
    }
    private RestResponse(int status, Object data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }
}
