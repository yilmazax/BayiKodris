package com.kodris.bayi;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {

        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.kodris.bayi");

        noClasses()
            .that()
                .resideInAnyPackage("com.kodris.bayi.service..")
            .or()
                .resideInAnyPackage("com.kodris.bayi.repository..")
            .should().dependOnClassesThat()
                .resideInAnyPackage("..com.kodris.bayi.web..")
        .because("Services and repositories should not depend on web layer")
        .check(importedClasses);
    }
}
