import gratatouille.tasks.FileWithPath
import java.io.File
import kotlin.test.assertEquals
import nmcp.internal.task.nmcpFindDeploymentName
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class FindDeploymentNameTest {

    @get:Rule
    internal val tmp = TemporaryFolder()

    private fun assertDeploymentName(inputFiles: List<String>, expected: String) {
        val output = tmp.newFile("output.txt")
        nmcpFindDeploymentName(
            inputFiles = inputFiles.map { FileWithPath(File(it), normalizedPath = it) },
            outputFile = output,
        )
        assertEquals(expected, output.readText())
    }

    @Test
    fun `single artifact`() = assertDeploymentName(
        inputFiles = listOf(
            "com/example/foo/1.0.0/foo-1.0.0.pom",
        ),
        expected = "com.example:foo:1.0.0",
    )

    @Test
    fun `multiple artifacts with different names`() = assertDeploymentName(
        inputFiles = listOf(
            "com/example/foo/1.0.0/foo-1.0.0.pom",
            "com/example/bar/1.0.0/bar-1.0.0.pom",
        ),
        expected = "com.example:*:1.0.0",
    )

    @Test
    fun `multiple artifacts with similar prefixes`() = assertDeploymentName(
        inputFiles = listOf(
            "com/example/foo-bar/1.0.0/foo-bar-1.0.0.pom",
            "com/example/foo-baz/1.0.0/foo-baz-1.0.0.pom",
        ),
        expected = "com.example:foo-ba*:1.0.0",
    )

    @Test
    fun nmcp() = assertDeploymentName(
        inputFiles = listOf(
            "com/gradleup/nmcp/aggregation/com.gradleup.nmcp.aggregation.gradle.plugin/1.0.0/com.gradleup.nmcp.aggregation.gradle.plugin-1.0.0.pom",
            "com/gradleup/nmcp/com.gradleup.nmcp.gradle.plugin/1.0.0/com.gradleup.nmcp.gradle.plugin-1.0.0.pom",
            "com/gradleup/nmcp/kdoc/1.0.0/kdoc-1.0.0.pom",
            "com/gradleup/nmcp/nmcp/1.0.0/nmcp-1.0.0.pom",
        ),
        expected = "com.gradleup.nmcp*:*:1.0.0",
    )

    @Test
    fun kilua() = assertDeploymentName(
        inputFiles = listOf(
            "dev/kilua/kilua-rpc-annotations/0.0.35/kilua-rpc-annotations-0.0.35.pom",
            "dev/kilua/kilua-rpc-annotations-js/0.0.35/kilua-rpc-annotations-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-annotations-jvm/0.0.35/kilua-rpc-annotations-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-annotations-wasm-js/0.0.35/kilua-rpc-annotations-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-core/0.0.35/kilua-rpc-core-0.0.35.pom",
            "dev/kilua/kilua-rpc-core-js/0.0.35/kilua-rpc-core-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-core-jvm/0.0.35/kilua-rpc-core-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-core-wasm-js/0.0.35/kilua-rpc-core-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-gradle-plugin/0.0.35/kilua-rpc-gradle-plugin-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin/0.0.35/kilua-rpc-javalin-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-common/0.0.35/kilua-rpc-javalin-common-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-common-js/0.0.35/kilua-rpc-javalin-common-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-common-jvm/0.0.35/kilua-rpc-javalin-common-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-common-wasm-js/0.0.35/kilua-rpc-javalin-common-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-js/0.0.35/kilua-rpc-javalin-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-jvm/0.0.35/kilua-rpc-javalin-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-koin/0.0.35/kilua-rpc-javalin-koin-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-koin-js/0.0.35/kilua-rpc-javalin-koin-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-koin-jvm/0.0.35/kilua-rpc-javalin-koin-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-koin-wasm-js/0.0.35/kilua-rpc-javalin-koin-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-javalin-wasm-js/0.0.35/kilua-rpc-javalin-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby/0.0.35/kilua-rpc-jooby-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-common/0.0.35/kilua-rpc-jooby-common-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-common-js/0.0.35/kilua-rpc-jooby-common-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-common-jvm/0.0.35/kilua-rpc-jooby-common-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-common-wasm-js/0.0.35/kilua-rpc-jooby-common-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-js/0.0.35/kilua-rpc-jooby-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-jvm/0.0.35/kilua-rpc-jooby-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-koin/0.0.35/kilua-rpc-jooby-koin-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-koin-js/0.0.35/kilua-rpc-jooby-koin-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-koin-jvm/0.0.35/kilua-rpc-jooby-koin-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-koin-wasm-js/0.0.35/kilua-rpc-jooby-koin-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-jooby-wasm-js/0.0.35/kilua-rpc-jooby-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-ksp-processor/0.0.35/kilua-rpc-ksp-processor-0.0.35.pom",
            "dev/kilua/kilua-rpc-ksp-processor-jvm/0.0.35/kilua-rpc-ksp-processor-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor/0.0.35/kilua-rpc-ktor-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-common/0.0.35/kilua-rpc-ktor-common-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-common-js/0.0.35/kilua-rpc-ktor-common-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-common-jvm/0.0.35/kilua-rpc-ktor-common-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-common-wasm-js/0.0.35/kilua-rpc-ktor-common-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-js/0.0.35/kilua-rpc-ktor-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-jvm/0.0.35/kilua-rpc-ktor-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-koin/0.0.35/kilua-rpc-ktor-koin-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-koin-js/0.0.35/kilua-rpc-ktor-koin-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-koin-jvm/0.0.35/kilua-rpc-ktor-koin-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-koin-wasm-js/0.0.35/kilua-rpc-ktor-koin-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-ktor-wasm-js/0.0.35/kilua-rpc-ktor-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-micronaut/0.0.35/kilua-rpc-micronaut-0.0.35.pom",
            "dev/kilua/kilua-rpc-micronaut-js/0.0.35/kilua-rpc-micronaut-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-micronaut-jvm/0.0.35/kilua-rpc-micronaut-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-micronaut-wasm-js/0.0.35/kilua-rpc-micronaut-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-spring-boot/0.0.35/kilua-rpc-spring-boot-0.0.35.pom",
            "dev/kilua/kilua-rpc-spring-boot-js/0.0.35/kilua-rpc-spring-boot-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-spring-boot-jvm/0.0.35/kilua-rpc-spring-boot-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-spring-boot-wasm-js/0.0.35/kilua-rpc-spring-boot-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-types/0.0.35/kilua-rpc-types-0.0.35.pom",
            "dev/kilua/kilua-rpc-types-js/0.0.35/kilua-rpc-types-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-types-jvm/0.0.35/kilua-rpc-types-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-types-wasm-js/0.0.35/kilua-rpc-types-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx/0.0.35/kilua-rpc-vertx-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-common/0.0.35/kilua-rpc-vertx-common-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-common-js/0.0.35/kilua-rpc-vertx-common-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-common-jvm/0.0.35/kilua-rpc-vertx-common-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-common-wasm-js/0.0.35/kilua-rpc-vertx-common-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-js/0.0.35/kilua-rpc-vertx-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-jvm/0.0.35/kilua-rpc-vertx-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-koin/0.0.35/kilua-rpc-vertx-koin-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-koin-js/0.0.35/kilua-rpc-vertx-koin-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-koin-jvm/0.0.35/kilua-rpc-vertx-koin-jvm-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-koin-wasm-js/0.0.35/kilua-rpc-vertx-koin-wasm-js-0.0.35.pom",
            "dev/kilua/kilua-rpc-vertx-wasm-js/0.0.35/kilua-rpc-vertx-wasm-js-0.0.35.pom",
            "dev/kilua/rpc/dev.kilua.rpc.gradle.plugin/0.0.35/dev.kilua.rpc.gradle.plugin-0.0.35.pom",
        ),
        expected = "dev.kilua*:*:0.0.35",
    )
}
