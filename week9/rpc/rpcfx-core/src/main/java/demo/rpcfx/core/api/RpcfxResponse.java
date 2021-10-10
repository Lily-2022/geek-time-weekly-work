package demo.rpcfx.core.api;

import lombok.Data;

@Data
public class RpcfxResponse {
    private Object result;
    private boolean status;
    private Exception exception;
}
