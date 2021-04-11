package com.willlake.ringingapi.endpoints;

import com.willlake.ringingapi.endpoints.handlers.MethodHandler;
import com.willlake.ringingapi.methods.data.dto.Lead;
import com.willlake.ringingapi.methods.data.dto.Method;
import com.willlake.ringingapi.methods.data.dto.Row;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/methods")
public class MethodController {
    private final MethodHandler methodHandler;

    public MethodController(MethodHandler methodHandler) {
        this.methodHandler = methodHandler;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<Iterable<Method>> allMethods() {
        return new ResponseEntity<>(methodHandler.allMethods(), HttpStatus.OK);
    }

    @RequestMapping(value = "/{methodId}", method = RequestMethod.GET)
    public ResponseEntity<Method> getMethod(@PathVariable String methodId) {
        return new ResponseEntity<>(methodHandler.getMethod(methodId), HttpStatus.OK);
    }

    @RequestMapping(value = "/plain/{methodId}", method = RequestMethod.GET)
    public ResponseEntity<Lead> plainCourse(@PathVariable String methodId) {
        return new ResponseEntity<>(methodHandler.plainCourse(methodId), HttpStatus.OK);
    }

    @RequestMapping(value = "/plainShort/{methodId}", method = RequestMethod.GET)
    public ResponseEntity<List<String>> plainShort(@PathVariable String methodId) {
        return new ResponseEntity<>(methodHandler.plainShort(methodId), HttpStatus.OK);
    }
}
