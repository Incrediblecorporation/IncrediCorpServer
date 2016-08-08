package incredicorpserver.webservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/checkConnection")
public class CheckConnection_WebService {
    @RequestMapping(method= RequestMethod.POST)
    public ResponseEntity<Boolean> isConnected() {
        System.out.println("Connected");
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
