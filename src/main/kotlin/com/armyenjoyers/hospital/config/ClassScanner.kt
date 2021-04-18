package com.armyenjoyers.hospital.config

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.BeanDefinition

import org.springframework.core.type.filter.AnnotationTypeFilter

import org.springframework.web.context.support.StandardServletEnvironment

import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider
import java.util.*

import java.util.stream.Collectors





object ClassScanner {
    private val logger: Logger = LoggerFactory.getLogger(ClassScanner::class.java)
    fun findAllAnnotatedClassesInPackage(
        packageName: String,
        clazz: Class<out Annotation?>
    ): List<Class<*>> {
        val scanner = ClassPathScanningCandidateComponentProvider(false)
        scanner.addIncludeFilter(AnnotationTypeFilter(clazz))

        return scanner
            .findCandidateComponents(packageName)
            .stream()
            .map { obj: BeanDefinition -> obj.beanClassName }
            .filter(Objects::nonNull)
            .map{Class.forName(it)}
            .collect(Collectors.toList())
    }
}