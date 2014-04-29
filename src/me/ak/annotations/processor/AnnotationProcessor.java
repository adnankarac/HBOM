package me.ak.annotations.processor;

import java.lang.annotation.Annotation;

import me.ak.hbase.HBaseObject;

public class AnnotationProcessor {
	public static void processAnnotations(Class<HBaseObject> clazz) {
		Annotation[] annotations = clazz.getAnnotations();
	}
}
