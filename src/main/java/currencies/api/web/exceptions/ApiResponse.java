package currencies.api.web.exceptions;

import lombok.Getter;

import java.util.List;

@Getter
public class ApiResponse {
    private List<String> messages;

    public ApiResponse(List<String> messages) {
        this.messages = messages;
    }
}
