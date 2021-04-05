package com.willlake.ringingapi.endpoints;

import com.willlake.ringingapi.methods.data.MethodRepoControl;
import com.willlake.ringingapi.methods.data.MethodRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DatabaseHandler {
    private static final Logger log = LoggerFactory.getLogger(DatabaseHandler.class);

    private MethodRepoControl methodRepoControl;
    private MethodRepository methodRepository;

    public DatabaseHandler(MethodRepoControl methodRepoControl, MethodRepository methodRepository) {
        this.methodRepoControl = methodRepoControl;
        this.methodRepository = methodRepository;
    }

    public void clear() {
        methodRepoControl.clearAllMethodsFromDB();
        log.info("Method table cleared");
        log.info(methodRepository.countAll() + " records in the Methods Table");
    }
}
