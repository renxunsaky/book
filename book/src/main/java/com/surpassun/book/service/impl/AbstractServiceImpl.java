package com.surpassun.book.service.impl;

import org.springframework.stereotype.Service;

import com.vercer.engine.persist.ObjectDatastore;
import com.vercer.engine.persist.annotation.AnnotationObjectDatastore;

@Service
public class AbstractServiceImpl {

	ObjectDatastore datastore = new AnnotationObjectDatastore();
}
