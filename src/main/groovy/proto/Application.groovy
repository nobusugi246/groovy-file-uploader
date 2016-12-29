package proto

import groovy.transform.CompileStatic
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@ComponentScan(['proto','asset.pipeline.springboot'])
@SpringBootApplication
@CompileStatic
class Application {
    static void main(String[] args) {
        SpringApplication.run(Application, args)
    }
}
