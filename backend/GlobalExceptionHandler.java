import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.system.billing.backend.client.ClientNotFoundException;

@RestControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<Map<String, String>> notFound(ClientNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(Map.of("error", "CLIENT_NOT_FOUND", "message", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> invalid(MethodArgumentNotValidException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
        .body(Map.of("error", "INVALID_DATA", "message", ex.getBindingResult().getFieldErrors().get(0).getDefaultMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> duplicate(DataIntegrityViolationException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT)
        .body(Map.of("error", "DUPLICATE_DATA", "message", "A record with that NIT already exists"));
    }

}
